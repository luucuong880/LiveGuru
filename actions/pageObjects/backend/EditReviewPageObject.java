package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import liveguru.backend.PageGeneratorManager;
import pageUI.backend.EditReviewPageUI;

public class EditReviewPageObject extends BasePage {
	WebDriver driver;

	public EditReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void checkToDetailRating(String idValue) {
		waitForElementClickable(driver, EditReviewPageUI.FIELD_TEXT_BOX, idValue);
		checkToDefaultCheckboxOrRadio(driver, EditReviewPageUI.FIELD_TEXT_BOX, idValue);
	}

	public void selectStatusItem(String item, String textItem) {
		waitForElementClickable(driver, EditReviewPageUI.ITEM_SELECTED, item);
		selectItemInDefaultDropdown(driver, EditReviewPageUI.ITEM_SELECTED, textItem, item);
	}

	public void inputToFieldBox(String idValue, String text) {
		waitForElementVisible(driver, EditReviewPageUI.FIELD_TEXT_BOX, idValue);
		sendkeyToElement(driver, EditReviewPageUI.FIELD_TEXT_BOX, text, idValue);
	}

	public void inputToTextAreaBox(String textValue) {
		waitForElementVisible(driver, EditReviewPageUI.TEXT_AREA_BOX);
		sendkeyToElement(driver, EditReviewPageUI.TEXT_AREA_BOX, textValue);
	}

	public PendingReviewPO clickToButtonByTitleDynamics(String title) {
		waitForElementClickable(driver, EditReviewPageUI.BUTTON_BY_TITLE, title);
		clickToElement(driver, EditReviewPageUI.BUTTON_BY_TITLE, title);
		return PageGeneratorManager.getPageGeneratorManager().getPendingReviewPage(driver);
	}

	public PendingReviewPO clickAcceptAlert() {
		acceptAlert(driver);
		return PageGeneratorManager.getPageGeneratorManager().getPendingReviewPage(driver);
	}

}
