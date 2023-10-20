package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.CheckoutPageUI;

public class ShoppingCartPageObject extends BasePage {
	private WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isTextDisplayed(String textValue, String textValue1) {
		waitForElementVisible(driver, CheckoutPageUI.TEXT_DYNAMICS, textValue, textValue1);
		return isElementDisplayed(driver, CheckoutPageUI.TEXT_DYNAMICS, textValue, textValue1);
	}

	public void clickToButtonByTitleDynamic(String titleValue) {
		waitForElementClickable(driver, CheckoutPageUI.BUTTON_TITLE, titleValue);
		clickToElement(driver, CheckoutPageUI.BUTTON_TITLE, titleValue);
	}

	public boolean isGrandTotalTextDisplayed() {
		waitForElementVisible(driver, CheckoutPageUI.GRAND_TOTAL_TEXT);
		return isElementDisplayed(driver, CheckoutPageUI.GRAND_TOTAL_TEXT);
	}

	public void inputToFieldQTY(String string) {
		waitForElementVisible(driver, CheckoutPageUI.QTY_TEXT_BOX);
		sendkeyToElement(driver, CheckoutPageUI.QTY_TEXT_BOX, string);
	}

	public String getItemErrorMessage() {
		waitForElementVisible(driver, CheckoutPageUI.ITEM_ERROR_MESSAGE);
		return getElementText(driver, CheckoutPageUI.ITEM_ERROR_MESSAGE);
	}

	public String getEmptyCartText() {
		waitForElementVisible(driver, CheckoutPageUI.EMPTY_CART_MESSAGE);
		return getElementText(driver, CheckoutPageUI.EMPTY_CART_MESSAGE);
	}

}
