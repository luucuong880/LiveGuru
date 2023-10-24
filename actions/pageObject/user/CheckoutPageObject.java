package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;

public class CheckoutPageObject extends BasePage {
	private WebDriver driver;

	public CheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
