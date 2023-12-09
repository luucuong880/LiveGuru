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

import liveguru.frontend.BaseTest;
import liveguru.frontend.PageGeneratorManager;
import pageObject.frontend.CheckoutPageObject;
import pageObject.frontend.HomePageObject;
import pageObject.frontend.MobilePageObject;
import pageObject.frontend.MyCartPageObject;
import pageObject.frontend.ProductsPageObject;
import pageObject.frontend.RegisterPageObject;
import pageObject.frontend.TVPageObject;
import pageObject.frontend.YourReviewPageObject;
import pageObjects.backend.BackEndLoginPO;
import pageObjects.backend.CustomerBackEndPO;
import pageObjects.backend.EditReviewPageObject;
import pageObjects.backend.InvoicesPageObject;
import pageObjects.backend.ManageCustomersPO;
import pageObjects.backend.OrdersPageObject;
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
		yourReview = "This product is goood";
		yourThoughts = "Good";
		nickName = "Ghost" + generateFakeNumber();
		yourReview1 = "This product is goood" + generateFakeNumber();
		yourThoughts1 = "Good" + generateFakeNumber();
		editReview = "This product is very good" + generateFakeNumber();
		editThoughts = "Very good" + generateFakeNumber();

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
		yourReviewPage.inputToTextArea("review_field", yourReview);
		yourReviewPage.inputToBoxText(driver, "summary_field", yourThoughts);
		yourReviewPage.inputToBoxText(driver, "nickname_field", "Ghost");
		yourReviewPage.clickToButtonTitle(driver, "Submit Review");

		verifyEquals(yourReviewPage.getTextMessages(driver), "Your review has been accepted for moderation.");

		yourReviewPage.checkToRateProducts("Quality 1_4");
		yourReviewPage.inputToTextArea("review_field", yourReview1);
		yourReviewPage.inputToBoxText(driver, "summary_field", yourThoughts1);
		yourReviewPage.inputToBoxText(driver, "nickname_field", "Ghost");
		yourReviewPage.clickToButtonTitle(driver, "Submit Review");

		verifyEquals(yourReviewPage.getTextMessages(driver), "Your review has been accepted for moderation.");

		myCartPage = (MyCartPageObject) yourReviewPage.clickToButtonByTitleDynamic(driver, "Add to Cart");

		myCartPage.selectCountryAndState("country", userData.getCountry());
		myCartPage.getAttributeItemSelected("country", userData.getCountry());
		myCartPage.selectCountryAndState("region_id", userData.getState());
		myCartPage.getAttributeItemSelected("region_id", userData.getState());
		myCartPage.inputToBoxText(driver, "postcode", userData.getZipcode());

		myCartPage.clickToButtonTitle(driver, "Estimate");

		myCartPage.checkToFlatRateBox();

		myCartPage = myCartPage.clickToButtonTitle("Update Total");

		checkoutPage = (CheckoutPageObject) myCartPage.clickToButtonByTitleDynamic(driver, "Proceed to Checkout");

		checkoutPage.inputToBoxText(driver, "billing:firstname", userData.getFirstName());
		checkoutPage.inputToBoxText(driver, "billing:lastname", userData.getLastName());
		checkoutPage.inputToBoxText(driver, "billing:street1", userData.getAddress());
		checkoutPage.inputToBoxText(driver, "billing:city", userData.getCity());
		checkoutPage.selectStateProvince("billing:region_id", userData.getState());
		checkoutPage.inputToBoxText(driver, "billing:postcode", userData.getZipcode());
		checkoutPage.selectStateProvince("billing:country_id", userData.getCountry());
		checkoutPage.inputToBoxText(driver, "billing:telephone", userData.getPhone());

		checkoutPage.clickToContinueButton("billing-buttons-container");

		checkoutPage.clickToContinueButton("shipping-method-buttons-container");

		checkoutPage.checkToPaymentRadionBox("Check / Money order");
		checkoutPage.clickToContinueButton("payment-buttons-container");

		checkoutPage.clickToButtonTitle(driver, "Place Order");

		log.info("User Step - 55: Verify Order is gemerated");
		verifyEquals(checkoutPage.getPageTitleText(), "YOUR ORDER HAS BEEN RECEIVED.");

		backEndLoginPage = yourReviewPage.openBackEndLoginPage(driver);

	}

	@Test
	public void Admin_01_Login_To_Backend_Page() {
		log.info("Admin Step - 01: Enter Admin Info and login");
		customerBackEndPage = backEndLoginPage.loginWithBackEndInfo("username", userData.getLoginUsername(), "login", userData.getLoginPassword());

		log.info("Admin Step - 02: Open Pending Reviews page");
		pendingReviewPage = (PendingReviewPO) customerBackEndPage.clickPageAtCatlogPages(driver, "Catalog", "Reviews and Ratings", "Customer Reviews", "Pending Reviews");

		log.info("Admin Step - 03: Verify User(FrontEnd) is displayed");
		verifyTrue(pendingReviewPage.isUserFrontEndDisplayed("title", yourThoughts));
		verifyTrue(pendingReviewPage.isUserFrontEndDisplayed("nickname", "Ghost"));
		verifyTrue(pendingReviewPage.isUserFrontEndDisplayed("detail", yourReview));

		log.info("Admin Step - 04: Click to Edit button");
		editReviewPage = pendingReviewPage.clickToEditButton("1");

		log.info("Admin Step - 05: Edit Review at Edit page");
		editReviewPage.checkToDetailRating("Quality 1_5");
		editReviewPage.selectStatusItem("status_id", "Approved");
		editReviewPage.inputToFieldBox("nickname", "Ghost");
		editReviewPage.inputToFieldBox("title", editThoughts);
		editReviewPage.inputToTextAreaBox(editReview);
		pendingReviewPage = editReviewPage.clickToButtonByTitleDynamics("Save Review");
		verifyEquals(pendingReviewPage.getTextMessages(driver), "The review has been saved.");

		log.info("Admin Step - 06: Click 'Reset Filter' button");
		pendingReviewPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 07: Delete Review at Edit page");
		editReviewPage = pendingReviewPage.clickToEditButton("1");
		editReviewPage.clickToButtonByTitleDynamics("Delete Review");
		verifyEquals(editReviewPage.getAlertText(driver), "Are you sure you want to do this?");
		pendingReviewPage = editReviewPage.clickAcceptAlert();
		verifyEquals(pendingReviewPage.getTextMessages(driver), "The review has been deleted");
	}

	@Test
	public void Admin_02_Invoice_Can_Be_Printed() throws Exception {
		log.info("Admin Step - 08: Open 'Orders' page");
		ordersPage = (OrdersPageObject) pendingReviewPage.openPageAtMenuStart(driver, "Sales", "Orders");

		log.info("Admin Step - 09: Select 'Cancel' field and click 'Search' button");
		ordersPage.selectSaleOrders("sales_order_grid_massaction-select", "Cancel");
		ordersPage.clickToButtonTitle(driver, "Search");

		log.info("Admin Step - 10: Check to first checkbox order");
		ordersPage.checkToOrdersCheckbox("1");
		ordersPage.selectSaleOrders("sales_order_grid_massaction-select", "Print Invoices");
		ordersPage.clickToButtonTitle(driver, "Submit");

		log.info("Admin Step - 11: Verify Error message text");
		verifyEquals(ordersPage.getTextMessages(driver), "There are no printable documents related to selected orders.");

		log.info("Admin Step - 12: Select 'Complete' field and click 'Search' button");
		ordersPage.selectSaleOrders("sales_order_grid_filter_status", "Complete");
		ordersPage.clickToButtonTitle(driver, "Search");

		log.info("Admin Step - 13: Check to first checkbox order");
		ordersPage.checkToOrdersCheckbox("1");
		ordersPage.selectSaleOrders("sales_order_grid_massaction-select", "Print Invoices");
		ordersPage.clickToButtonTitle(driver, "Submit");

		log.info("Admin Step - 14: Verify invoice is downloaded");
		ordersPage.downloadFileAndDeleteFile("invoice2023-11-10_22-36-05", "invoice");
	}

	@Test
	public void Admin_03_Product_Review_Mechanism() {
		log.info("Admin Step - 15: Open Front End page");
		homePage = ordersPage.openHomePageFrontEnd(driver);

		log.info("Admin Step - 16: Go to link review");
		yourReviewPage = homePage.openLink();

		log.info("Admin Step - 17: Enter Field at Review page");
		yourReviewPage.checkToRateProducts("Quality 1_4");
		yourReviewPage.inputToTextArea("review_field", yourReview1);
		yourReviewPage.inputToBoxText(driver, "summary_field", yourThoughts1);
		yourReviewPage.inputToBoxText(driver, "nickname_field", nickName);
		yourReviewPage.clickToButtonTitle(driver, "Submit Review");

		log.info("Admin Step - 18: Open Admin page and login");
		backEndLoginPage = yourReviewPage.openBackEndLoginPage(driver);
		customerBackEndPage = liveguru.backend.PageGeneratorManager.getPageGeneratorManager().getCustomerBackEndPage(driver);

		log.info("Admin Step - 19: Open Pending review page");
		pendingReviewPage = (PendingReviewPO) customerBackEndPage.clickPageAtCatlogPages(driver, "Catalog", "Reviews and Ratings", "Customer Reviews", "Pending Reviews");

		log.info("Admin Step - 20: Click to Edit button");
		editReviewPage = pendingReviewPage.clickToEditButton("1");

		log.info("Admin Step - 21: Edit Status review");
		editReviewPage.selectStatusItem("status_id", "Approved");
		pendingReviewPage = editReviewPage.clickToButtonByTitleDynamics("Save Review");
		verifyEquals(pendingReviewPage.getTextMessages(driver), "The review has been saved.");

		log.info("Admin Step - 22: Open Front End page");
		homePage = pendingReviewPage.openHomePageFrontEnd(driver);

		log.info("Admin Step - 23: Click on Sony Xperia image");
		mobilePage = (MobilePageObject) homePage.openProductsPage(driver, "Mobile");
		mobilePage.clickToProductImage("Xperia");

		log.info("Admin Step - 24: Click to Reviews button");
		yourReviewPage = mobilePage.clickToReviewsLink();

		log.info("Admin Step - 25: Verify Review comment is shown");
		verifyTrue(yourReviewPage.isCommnetReviewDisplayed(nickName));
	}

	@Test
	public void Admin_04_Verify_Sort_Is_Working() {
		log.info("Admin Step - 26: Go to Admin page");
		backEndLoginPage = yourReviewPage.openBackEndLoginPage(driver);
		customerBackEndPage = liveguru.backend.PageGeneratorManager.getPageGeneratorManager().getCustomerBackEndPage(driver);

		log.info("Admin Step - 27: Open Invoices page");
		invoicesPage = (InvoicesPageObject) customerBackEndPage.openPageAtMenuStart(driver, "Sales", "Invoices");

		log.info("Admin Step - 28: Click Sort Title button and verify sort Ascending is working");
		invoicesPage.clickToSortButtonByDynamics("Invoice #");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Invoice #", "2"));

		invoicesPage.clickToSortButtonByDynamics("Invoice Date");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Invoice Date", "3"));

		invoicesPage.clickToSortButtonByDynamics("Order #");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Order #", "4"));

		invoicesPage.clickToSortButtonByDynamics("Order Date");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Order Date", "5"));

		invoicesPage.clickToSortButtonByDynamics("Bill to Name");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Bill to Name", "6"));

		invoicesPage.clickToSortButtonByDynamics("Status");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Status", "7"));

		invoicesPage.clickToSortButtonByDynamics("Amount");
		verifyTrue(invoicesPage.isSortByAscending(driver, "Amount", "8"));

		log.info("Admin Step - 29: Click Sort Title button and verify sort Descending is working");
		invoicesPage.clickToSortButtonByDynamics("Invoice #");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Invoice #", "2"));

		invoicesPage.clickToSortButtonByDynamics("Invoice Date");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Invoice Date", "3"));

		invoicesPage.clickToSortButtonByDynamics("Order #");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Order #", "4"));

		invoicesPage.clickToSortButtonByDynamics("Order Date");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Order Date", "5"));

		invoicesPage.clickToSortButtonByDynamics("Bill to Name");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Bill to Name", "6"));

		invoicesPage.clickToSortButtonByDynamics("Status");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Status", "7"));

		invoicesPage.clickToSortButtonByDynamics("Amount");
		verifyTrue(invoicesPage.isSortByDescending(driver, "Amount", "8"));
	}

	@Test
	public void Admin_05_Verify_Pagination_Functionality() {
		log.info("Admin Step - 30: Click Log out button and login");
		backEndLoginPage = invoicesPage.clickToLogoutLinkButton(driver);
		customerBackEndPage = backEndLoginPage.loginWithBackEndInfo("username", userData.getLoginUsername(), "login", userData.getLoginPassword());

		log.info("Admin Step - 31: Open Orders page");
		ordersPage = (OrdersPageObject) customerBackEndPage.openPageAtMenuStart(driver, "Sales", "Orders");

		log.info("Admin Step - 32: Select view per page");
		ordersPage.selectViewPerPage("20");
		verifyEquals(ordersPage.getViewPerPageSize(), 20);

		ordersPage.selectViewPerPage("30");
		verifyEquals(ordersPage.getViewPerPageSize(), 30);

		ordersPage.selectViewPerPage("50");
		verifyEquals(ordersPage.getViewPerPageSize(), 50);

		ordersPage.selectViewPerPage("100");
		verifyEquals(ordersPage.getViewPerPageSize(), 100);

		ordersPage.selectViewPerPage("200");
		verifyEquals(ordersPage.getViewPerPageSize(), 200);
	}

	@Test
	public void Admin_06_Verify_Search_Functionality() {
		log.info("Admin Step - 33: Click Log out button and login");
		backEndLoginPage = invoicesPage.clickToLogoutLinkButton(driver);
		customerBackEndPage = backEndLoginPage.loginWithBackEndInfo("username", userData.getLoginUsername(), "login", userData.getLoginPassword());

		log.info("Admin Step - 34: Open Manage Customer Page");
		manageCustomersPage = (ManageCustomersPO) customerBackEndPage.openPageAtMenuStart(driver, "Customers", "Manage Customers");

		log.info("Admin Step - 35: Search data with ID criteria");
		manageCustomersPage.inputToBoxText(driver, "customerGrid_filter_entity_id_to", "31389");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getInformationSize(driver, "ID", "2"), 3);
		manageCustomersPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 36: Search data with Name criteria");
		manageCustomersPage.inputToBoxText(driver, "customerGrid_filter_name", "Automation FC");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getInformationSize(driver, "Name", "3"), 2);
		manageCustomersPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 37: Search data with Email criteria");
		manageCustomersPage.inputToBoxText(driver, "customerGrid_filter_email", "automationfc.vn@gmail.com");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getInformationSize(driver, "Email", "4"), 1);
		manageCustomersPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 38: Search data with Telephone criteria");
		manageCustomersPage.inputToBoxText(driver, "customerGrid_filter_Telephone", "0123456789");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getInformationSize(driver, "Telephone", "5"), 16);
		manageCustomersPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 39: Search data with Zip criteria");
		manageCustomersPage.inputToBoxText(driver, "customerGrid_filter_billing_postcode", "550000");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getInformationSize(driver, "ZIP", "6"), 1);
		manageCustomersPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 40: Search data with Country criteria");
		manageCustomersPage.selectCountryID("customerGrid_filter_billing_country_id", "Vietnam");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getInformationSize(driver, "Country", "7"), 20);
		manageCustomersPage.clickToButtonTitle(driver, "Reset Filter");

		log.info("Admin Step - 41: Search data with State/Province criteria");
		manageCustomersPage.inputToBoxText(driver, "customerGrid_filter_billing_region", "Cam Le");
		manageCustomersPage.clickToButtonTitle(driver, "Search");
		verifyEquals(manageCustomersPage.getEmptyText(driver), "No records found.");

	}

	@Test
	public void Admin_07_Verify_Select_Checkbox_Functionality() {
		log.info("Admin Step - 42: Click Log out button and login");
		backEndLoginPage = manageCustomersPage.clickToLogoutLinkButton(driver);
		customerBackEndPage = backEndLoginPage.loginWithBackEndInfo("username", userData.getLoginUsername(), "login", userData.getLoginPassword());

		log.info("Admin Step - 43: Open Orders page");
		ordersPage = (OrdersPageObject) customerBackEndPage.openPageAtMenuStart(driver, "Sales", "Orders");
		ordersPage.selectViewPerPage("30");
		verifyEquals(ordersPage.getViewPerPageSize(), 30);

		log.info("Admin Step - 44: Click 'Select Visible' link");
		ordersPage.clickToLinksButton(driver, "Select Visible");

		log.info("Admin Step - 45: Verify 30 item is selected and get text");
		verifyTrue(ordersPage.isOrdersChecked("Order #"));
		verifyEquals(ordersPage.getMessageDisplayed("6"), "30 items selected");

		log.info("Admin Step - 46: Click 'Unselect Visible' link");
		ordersPage.clickToLinksButton(driver, "Unselect Visible");
		log.info("Admin Step - 47: Verify 0 item is selected and get text");
		verifyTrue(ordersPage.isOrdersChecked("Order #"));
		verifyEquals(ordersPage.getMessageDisplayed("6"), "0 items selected");
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
	String userNameLogin, emailAddress, passwordLogin, yourReview, yourThoughts, editReview, editThoughts, yourReview1, yourThoughts1, nickName;
	public HomePageObject homePage;
	public RegisterPageObject registerPage;
	public ProductsPageObject productsPage;
	public TVPageObject tvPage;
	public YourReviewPageObject yourReviewPage;
	public CheckoutPageObject checkoutPage;
	public MyCartPageObject myCartPage;
	public BackEndLoginPO backEndLoginPage;
	private CustomerBackEndPO customerBackEndPage;
	private PendingReviewPO pendingReviewPage;
	private EditReviewPageObject editReviewPage;
	private OrdersPageObject ordersPage;
	public MobilePageObject mobilePage;
	public InvoicesPageObject invoicesPage;
	public ManageCustomersPO manageCustomersPage;
	UserDataMapper userData;
}
