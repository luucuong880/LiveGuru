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
import pageObject.user.ComparePageObject;
import pageObject.user.HomePageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.MobilePageObject;
import pageObject.user.MyAccountPO;
import pageObject.user.MyWishlistPO;
import pageObject.user.ProductsPageObject;
import pageObject.user.RegisterPageObject;
import pageObject.user.ShoppingCartPageObject;
import pageObject.user.TVPageObject;
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
		shareEmail = userData.getEmailAddress() + +generateFakeNumber() + "@sharemail.com";
		fullName = userData.getFirstName() + " " + userData.getLastName();
		couponCode = "GURU50";

		String productsItem = "";
		if (productsItem.contains("Xperia")) {
			verifyEquals(mobilePage.getCostOfProducts(productsItem), "$100.00");
		} else if (productsItem.contains("Samsung Galaxy")) {
			verifyEquals(mobilePage.getCostOfProducts(productsItem), "$130.00");
		} else if (productsItem.contains("IPhone")) {
			verifyEquals(mobilePage.getCostOfProducts(productsItem), "$500.00");
		}
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
		verifyEquals(homePage.getTextMessages(driver), "Thank you for registering with Main Website Store.");
	}

	@Test
	public void User_02_User_Information() {
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
	public void User_03_Login_To_System() {
		log.info("User Step - 10: Log out Account");
		myAccountPage.clickToIconAtWrapper(driver, "Account");
		homePage = (HomePageObject) myAccountPage.openPageAtHeaderLinks(driver, "Log Out");
		homePage.sleepInSecond(5);
		verifyFalse(homePage.isMessageTextUndisplayed());

		log.info("User Step - 11: Open Login page and Login Account");
		homePage.clickToIconAtWrapper(driver, "Account");
		loginPage = (LoginPageObject) homePage.openPageAtHeaderLinks(driver, "Log In");
		loginPage.inputToBoxText(driver, "email", emailAddress);
		loginPage.inputToBoxText(driver, "pass", userData.getPassword());
		myAccountPage = (MyAccountPO) loginPage.clickToButtonByTitleDynamic(driver, "Login");

		log.info("User Step - 12: Verify message is displayed");
		verifyTrue(myAccountPage.isTextHeaderDasboardDisplayed(driver, "page-title"));
		verifyEquals(myAccountPage.getTextHeaderDasboard(), "Hello, " + fullName + "!");
	}

	@Test
	public void User_04_Cost_Of_Product() {
		log.info("User Step - 13: Open Mobile page");
		mobilePage = (MobilePageObject) myAccountPage.openProductsPage(driver, "Mobile");

		log.info("User Step - 14: Get cost of Sony Xperia");
		verifyEquals(mobilePage.getCostOfProducts("Xperia"), "$100.00");

		log.info("User Step - 15: Click on Sony Xperia detail");
		productsPage = (ProductsPageObject) mobilePage.openProductsPageByText(driver, "Sony Xperia");

		log.info("User Step - 16: Get cost of Sony Xperia");
		verifyEquals(productsPage.getCostOfProducts(), "$100.00");

	}

	@Test
	public void User_05_Discount_Coupon() {
		log.info("User Step - 17: Click to Add Cart button");
		shoppingCartPage = productsPage.clickToAddToCartButton();
		verifyEquals(shoppingCartPage.getTextMessages(driver), "Sony Xperia was added to your shopping cart.");

		log.info("User Step - 18: Enter Coupon code");
		shoppingCartPage.inputToBoxText(driver, "coupon_code", couponCode);
		shoppingCartPage.clickToButtonByTitleDynamic("Apply");
		verifyEquals(shoppingCartPage.getTextMessages(driver), "Coupon code \"GURU50\" was applied.");

		log.info("User Step - 19: Verify the discount generated");
		verifyTrue(shoppingCartPage.isTextDisplayed("Discount (GURU50)", "-$5.00"));
		verifyTrue(shoppingCartPage.isGrandTotalTextDisplayed());
	}

	@Test
	public void User_06_Can_Not_Add_More_500_Items_Of_Product() {
		log.info("User Step - 20: Enter text qty 501");
		shoppingCartPage.inputToFieldQTY("501");

		log.info("User Step - 21: Click Update button");
		shoppingCartPage.clickToButtonByTitleDynamic("Update");

		log.info("User Step - 22: Verify Error message");
		verifyEquals(shoppingCartPage.getTextMessages(driver), "Some of the products cannot be ordered in requested quantity.");
		verifyEquals(shoppingCartPage.getItemErrorMessage(), "* The maximum quantity allowed for purchase is 500.");

		log.info("User Step - 23: Click to Empty Cart button");
		shoppingCartPage.clickToButtonByTitleDynamic("Empty Cart");

		log.info("User Step - 24: Verify Shopping Cart is empty");
		verifyEquals(shoppingCartPage.getEmptyCartText(), "You have no items in your shopping cart." + "\n" + "Click here to continue shopping.");

	}

	@Test
	public void User_07_Compare_Two_Product() {
		log.info("User Step - 25: Open Mobile page");
		mobilePage = (MobilePageObject) shoppingCartPage.openProductsPage(driver, "Mobile");

		log.info("User Step - 26: Add 2 product to Compare list");
		mobilePage = mobilePage.clickAddLinks("IPhone", "link-compare");
		verifyEquals(mobilePage.getTextMessages(driver), "The product IPhone has been added to comparison list.");

		mobilePage = mobilePage.clickAddLinks("Sony Xperia", "link-compare");
		verifyEquals(mobilePage.getTextMessages(driver), "The product Sony Xperia has been added to comparison list.");

		log.info("User Step - 27: Click to Compare button");
		comparePage = mobilePage.clickToCompareButton();

		log.info("User Step - 28: Verify 2 selected products are present in Compare page");
		verifyTrue(comparePage.isProductsDisplayed("IPhone"));
		verifyTrue(comparePage.isProductsDisplayed("Sony Xperia"));

		log.info("User Step - 29: Close window Compare page");
		comparePage.clickToCloseWindowButton();
	}

	@Test
	public void User_08_Share_Wishlist_To_Other_People_Using_Email() {
		log.info("User Step - 30: Open TV page");
		tvPage = (TVPageObject) comparePage.openProductsPage(driver, "TV");

		log.info("User Step - 31: Click to Add wish list 'LG LCD' product");
		myWishlistPage = tvPage.clickToAddWishListButton("LG LCD", "link-wishlist");
		verifyEquals(myWishlistPage.getTextMessages(driver), "LG LCD has been added to your wishlist. Click here to continue shopping.");

		log.info("User Step - 32: Click to Share Wishlist button");
		myWishlistPage.clickToButtonTitle(driver, "Share Wishlist");

		log.info("User Step - 33: Enter Email and message then click Share Wishlist button");
		myWishlistPage.inputToTextAreaBox("email_address", shareEmail);
		myWishlistPage.inputToTextAreaBox("message", "Share for you");
		myWishlistPage.clickToButtonTitle(driver, "Share Wishlist");
		verifyEquals(myWishlistPage.getTextMessages(driver), "Your Wishlist has been shared.");

		log.info("User Step - 34: Verify 1 item Displayed");
		verifyTrue(myWishlistPage.isProductDiplayed());
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
	String emailAddress, fullName, couponCode, shareEmail;
	private HomePageObject homePage;
	private MyAccountPO myAccountPage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MobilePageObject mobilePage;
	private ProductsPageObject productsPage;
	private ShoppingCartPageObject shoppingCartPage;
	private ComparePageObject comparePage;
	private TVPageObject tvPage;
	private MyWishlistPO myWishlistPage;
	UserDataMapper userData;
}
