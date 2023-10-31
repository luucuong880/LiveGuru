package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import pageUI.backend.PendingReviewPageUI;

public class PendingReviewPO extends BasePage {
	private WebDriver driver;

	public PendingReviewPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isUserFrontEndDisplayed(String nameValue, String textValue) {
		waitForElementVisible(driver, PendingReviewPageUI.USER_FRONT_END, nameValue, textValue);
		return isElementDisplayed(driver, PendingReviewPageUI.USER_FRONT_END, nameValue, textValue);
	}
}
