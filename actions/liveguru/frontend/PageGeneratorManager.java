package liveguru.frontend;

import org.openqa.selenium.WebDriver;

import pageObject.frontend.AdvencedSearchPO;
import pageObject.frontend.CheckoutPageObject;
import pageObject.frontend.ComparePageObject;
import pageObject.frontend.HomePageObject;
import pageObject.frontend.LoginPageObject;
import pageObject.frontend.MobilePageObject;
import pageObject.frontend.MyAccountPO;
import pageObject.frontend.MyCartPageObject;
import pageObject.frontend.MyWishlistPO;
import pageObject.frontend.ProductsPageObject;
import pageObject.frontend.RegisterPageObject;
import pageObject.frontend.TVPageObject;
import pageObject.frontend.YourReviewPageObject;
import pageObjects.backend.BackEndLoginPO;

public class PageGeneratorManager {

	public static PageGeneratorManager getPageGeneratorManager() {
		return new PageGeneratorManager();
	}

	public HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public MyAccountPO getMyAccountPage(WebDriver driver) {
		return new MyAccountPO(driver);
	}

	public MyCartPageObject getMyCartPage(WebDriver driver) {
		return new MyCartPageObject(driver);
	}

	public MyWishlistPO getMyWishlistPage(WebDriver driver) {
		return new MyWishlistPO(driver);
	}

	public LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public MobilePageObject getMobilePage(WebDriver driver) {
		return new MobilePageObject(driver);
	}

	public TVPageObject getTVPage(WebDriver driver) {
		return new TVPageObject(driver);
	}

	public ProductsPageObject getProductsPage(WebDriver driver) {
		return new ProductsPageObject(driver);
	}

	public ComparePageObject getComparePage(WebDriver driver) {
		return new ComparePageObject(driver);
	}

	public YourReviewPageObject getYourReviewePage(WebDriver driver) {
		return new YourReviewPageObject(driver);
	}

	public CheckoutPageObject getCheckoutPage(WebDriver driver) {
		return new CheckoutPageObject(driver);
	}

	public AdvencedSearchPO getAdvencedSearchPage(WebDriver driver) {
		return new AdvencedSearchPO(driver);
	}

	public BackEndLoginPO getBackEndLoginPage(WebDriver driver) {
		return new BackEndLoginPO(driver);
	}

}
