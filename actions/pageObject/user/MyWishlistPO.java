package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;

public class MyWishlistPO extends BasePage {
	private WebDriver driver;

	public MyWishlistPO(WebDriver driver) {
		this.driver = driver;
	}
}
