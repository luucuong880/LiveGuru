package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRequiredFieldDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.REQUIRED_FIELD);
		return isElementDisplayed(driver, RegisterPageUI.REQUIRED_FIELD);
	}
}
