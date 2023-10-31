package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import pageUI.frontend.RegisterPageUI;

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
