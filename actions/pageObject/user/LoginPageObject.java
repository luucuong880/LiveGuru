package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
