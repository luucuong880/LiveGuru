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

import liveguru.user.BaseTest;
import liveguru.user.PageGeneratorManager;
import pageObject.user.HomePageObject;
import pageObject.user.MyAccountPO;
import pageObject.user.RegisterPageObject;
import utilities.Environment;

public class FrontEnd extends BaseTest {

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

	}

	@Test
	public void User_01_Register_Success() {
		log.info("User Step - 01: Click to 'Account' menu");
		homePage.clickToIconAtWrapper(driver, "Account");

		log.info("User Step - 02: Choose 'Register' link");
		registerPage = (RegisterPageObject) homePage.openPageAtHeaderLinks(driver, "Register");

		log.info("User Step - 03: Verify 'Required Fields' is Displayed");
		verifyTrue(registerPage.isRequiredFieldDisplayed());

		log.info("User Step - 04: Input all valid data to form");
		registerPage.inputToBoxText(driver, "firstname", userData.getFirstName());
		registerPage.inputToBoxText(driver, "lastname", userData.getLastName());
		registerPage.inputToBoxText(driver, "email_address", emailAddress);
		registerPage.inputToBoxText(driver, "password", userData.getPassword());
		registerPage.inputToBoxText(driver, "confirmation", userData.getPassword());

		log.info("User Step - 05: Click to 'Register' button");
		homePage = (HomePageObject) registerPage.clickToButtonByTitleDynamic(driver, "Register");

		log.info("User Step - 06: Verify Success message text after registered");
		verifyEquals(homePage.getSuccessMessageText(), "Thank you for registering with Main Website Store.");
	}

	@Test
	public void User_02_Veify_User_Information() {
		log.info("User Step - 07: Open 'My Account' page");
		homePage.clickToIconAtWrapper(driver, "Account");
		myAccountPage = (MyAccountPO) homePage.openPageAtHeaderLinks(driver, "My Account");

		log.info("User Step - 08: Open 'Account Information' page");
		myAccountPage.openPageBlockAccountColumn("Account Information");

		log.info("User Step - 09: Verify data in 3 fields are correctly");
		myAccountPage.getFieldsCorrectly("firstname", userData.getFirstName());
		myAccountPage.getFieldsCorrectly("lastname", userData.getLastName());
		myAccountPage.getFieldsCorrectly("email", emailAddress);

	}

	@Test
	public void User_03_Veify_User_Information() {

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
	String emailAddress;
	private HomePageObject homePage;
	private MyAccountPO myAccountPage;
	private RegisterPageObject registerPage;
	UserDataMapper userData;
}
