package liveguru.user;

import org.openqa.selenium.WebDriver;

import pageObject.user.ComparePageObject;
import pageObject.user.HomePageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.MobilePageObject;
import pageObject.user.MyAccountPO;
import pageObject.user.MyCartPageObject;
import pageObject.user.MyWishlistPO;
import pageObject.user.ProductsPageObject;
import pageObject.user.RegisterPageObject;
import pageObject.user.ShoppingCartPageObject;
import pageObject.user.TVPageObject;
import pageObject.user.YourReviewPageObject;

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

	public ShoppingCartPageObject getCheckoutPage(WebDriver driver) {
		return new ShoppingCartPageObject(driver);
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

}
