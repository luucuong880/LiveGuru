package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.YourReviewPageUI;

public class YourReviewPageObject extends BasePage {
	private WebDriver driver;

	public YourReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToTextArea(String idValue, String text) {
		waitForElementVisible(driver, YourReviewPageUI.TEXT_AREA, idValue);
		sendkeyToElement(driver, YourReviewPageUI.TEXT_AREA, text, idValue);
	}

	public String getErrorMessage(String idValue) {
		waitForElementVisible(driver, YourReviewPageUI.ERROR_MESSAGE, idValue);
		return getElementText(driver, YourReviewPageUI.ERROR_MESSAGE, idValue);
	}

	public void checkToRateProducts(String idValue) {
		waitForElementVisible(driver, YourReviewPageUI.RATE_RADIO_BOX, idValue);
		checkToDefaultCheckboxOrRadio(driver, YourReviewPageUI.RATE_RADIO_BOX, idValue);
	}
}
