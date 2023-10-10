package liveguru.user;

import org.openqa.selenium.WebDriver;

import pageObject.user.CheckoutPageObject;
import pageObject.user.HomePageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.MyAccountPO;
import pageObject.user.MyCartPageObject;
import pageObject.user.MyWishlistPO;
import pageObject.user.RegisterPageObject;

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

	public CheckoutPageObject getCheckoutPage(WebDriver driver) {
		return new CheckoutPageObject(driver);
	}

	public LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

}
