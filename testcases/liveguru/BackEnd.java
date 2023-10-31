package liveguru;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.livguru.data.UserDataMapper;

import liveguru.backend.BaseTest;
import liveguru.backend.PageGeneratorManager;
import pageObject.frontend.HomePageObject;
import pageObject.frontend.ProductsPageObject;
import pageObject.frontend.RegisterPageObject;
import pageObject.frontend.TVPageObject;
import pageObject.frontend.YourReviewPageObject;
import pageObjects.backend.BackEndLoginPO;
import pageObjects.backend.CustomerBackEndPO;
import pageObjects.backend.PendingReviewPO;
import utilities.Environment;

public class BackEnd extends BaseTest {

	Environment environment;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("testing") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
			@Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
		ConfigFactory.setProperty("env", envName);
		environment = ConfigFactory.create(Environment.class);

		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);

		homePage = PageGeneratorManager.getPageGeneratorManager().getHomePage(driver);
		userData = UserDataMapper.getUserData();
		emailAddress = userData.getEmailAddress() + generateFakeNumber() + "@fakemail.com";
		userNameLogin = userData.getLoginUsername();
		passwordLogin = userData.getLoginPassword();
		homePage.clickToIconAtWrapper(driver, "Account");

		registerPage = (RegisterPageObject) homePage.openPageAtHeaderLinks(driver, "Register");

		verifyTrue(registerPage.isRequiredFieldDisplayed());

		registerPage.inputToBoxText(driver, "firstname", userData.getFirstName());
		registerPage.inputToBoxText(driver, "lastname", userData.getLastName());
		registerPage.inputToBoxText(driver, "email_address", emailAddress);
		registerPage.inputToBoxText(driver, "password", userData.getPassword());
		registerPage.inputToBoxText(driver, "confirmation", userData.getPassword());

		homePage = (HomePageObject) registerPage.clickToButtonByTitleDynamic(driver, "Register");

		verifyEquals(homePage.getTextMessages(driver), "Thank you for registering with Main Website Store.");

		tvPage = (TVPageObject) homePage.openProductsPage(driver, "TV");

		productsPage = tvPage.clickProductsDetail("Samsung LCD");

		yourReviewPage = (YourReviewPageObject) productsPage.clickToAddToLinksButton(driver, "Add Your Review");

		yourReviewPage.checkToRateProducts("Quality 1_4");
		yourReviewPage.inputToTextArea("review_field", "This product is goood");
		yourReviewPage.inputToBoxText(driver, "summary_field", "Good");
		yourReviewPage.inputToBoxText(driver, "nickname_field", "Ghost");
		yourReviewPage.clickToButtonTitle(driver, "Submit Review");

		verifyEquals(yourReviewPage.getTextMessages(driver), "Your review has been accepted for moderation.");

		backEndLoginPage = yourReviewPage.openBackEndLoginPage(driver);
	}

	@Test
	public void Admin_01_Login_To_Backend_Page() {
		log.info("Admin Step - 01: Enter Admin Info and login");
		customerBackEndPage = backEndLoginPage.loginWithBackEndInfo("username", userNameLogin, "login", passwordLogin);

		log.info("Admin Step - 02: Open Pending Reviews page");
		pendingReviewPage = customerBackEndPage.clickPageAtCatlogPages("Catalog", "Reviews and Ratings", "Customer Reviews", "Pending Reviews");

		log.info("Admin Step - 03: Verify User(FrontEnd) is displayed");
		verifyTrue(pendingReviewPage.isUserFrontEndDisplayed("title", "Good"));
		verifyTrue(pendingReviewPage.isUserFrontEndDisplayed("nickname", "Ghost"));
		verifyTrue(pendingReviewPage.isUserFrontEndDisplayed("detail", "This product is goood"));
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}

	@Parameters({ "browser" })
	@AfterClass
	public void afterClass() {

		driver.quit();
	}

	WebDriver driver;
	String emailAddress, userNameLogin, passwordLogin;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private ProductsPageObject productsPage;
	private TVPageObject tvPage;
	private YourReviewPageObject yourReviewPage;
	public BackEndLoginPO backEndLoginPage;
	private CustomerBackEndPO customerBackEndPage;
	private PendingReviewPO pendingReviewPage;
	UserDataMapper userData;
}
