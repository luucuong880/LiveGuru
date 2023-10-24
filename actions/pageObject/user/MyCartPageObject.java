package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.MyCartPageUI;

public class MyCartPageObject extends BasePage {
	private WebDriver driver;

	public MyCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isTextDisplayed(String textValue, String textValue1) {
		waitForElementVisible(driver, MyCartPageUI.TEXT_DYNAMICS, textValue, textValue1);
		return isElementDisplayed(driver, MyCartPageUI.TEXT_DYNAMICS, textValue, textValue1);
	}

	public void clickToButtonByTitleDynamic(String titleValue) {
		waitForElementClickable(driver, MyCartPageUI.BUTTON_TITLE, titleValue);
		clickToElement(driver, MyCartPageUI.BUTTON_TITLE, titleValue);
	}

	public boolean isGrandTotalTextDisplayed() {
		waitForElementVisible(driver, MyCartPageUI.GRAND_TOTAL_TEXT);
		return isElementDisplayed(driver, MyCartPageUI.GRAND_TOTAL_TEXT);
	}

	public void inputToFieldQTY(String string) {
		waitForElementVisible(driver, MyCartPageUI.QTY_TEXT_BOX);
		sendkeyToElement(driver, MyCartPageUI.QTY_TEXT_BOX, string);
	}

	public String getItemErrorMessage() {
		waitForElementVisible(driver, MyCartPageUI.ITEM_ERROR_MESSAGE);
		return getElementText(driver, MyCartPageUI.ITEM_ERROR_MESSAGE);
	}

	public String getEmptyCartText() {
		waitForElementVisible(driver, MyCartPageUI.EMPTY_CART_MESSAGE);
		return getElementText(driver, MyCartPageUI.EMPTY_CART_MESSAGE);
	}
}
