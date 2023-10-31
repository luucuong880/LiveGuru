package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import liveguru.frontend.PageGeneratorManager;
import pageUI.frontend.MyCartPageUI;

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

	public void selectCountryAndState(String idValue, String textItem) {
		waitForElementClickable(driver, MyCartPageUI.ITEM_SELECTED, idValue);
		selectItemInDefaultDropdown(driver, MyCartPageUI.ITEM_SELECTED, textItem, idValue);
	}

	public String getAttributeItemSelected(String idValue, String attributeName) {
		return getElementAttribute(driver, MyCartPageUI.ITEM_SELECTED, attributeName, idValue);
	}

	public String getShippingCostText() {
		waitForElementVisible(driver, MyCartPageUI.SHIPPING_COST);
		return getElementText(driver, MyCartPageUI.SHIPPING_COST);
	}

	public void checkToFlatRateBox() {
		waitForElementClickable(driver, MyCartPageUI.FLAT_RATE_BOX);
		checkToDefaultCheckboxOrRadio(driver, MyCartPageUI.FLAT_RATE_BOX);
	}

	public boolean isFlatRateChecked() {
		return isElementSelected(driver, MyCartPageUI.FLAT_RATE_BOX);
	}

	public String getTotalCostText() {
		waitForElementVisible(driver, MyCartPageUI.TOTAL_COST_TEXT);
		return getElementText(driver, MyCartPageUI.TOTAL_COST_TEXT);
	}

	public MyCartPageObject clickToButtonTitle(String string) {
		waitForElementClickable(driver, MyCartPageUI.BUTTON_TITLE, string);
		clickToElement(driver, MyCartPageUI.BUTTON_TITLE, string);
		sleepInSecond(2);
		return PageGeneratorManager.getPageGeneratorManager().getMyCartPage(driver);
	}
}
