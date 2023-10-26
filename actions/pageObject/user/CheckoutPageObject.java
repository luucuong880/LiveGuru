package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.CheckoutPageUI;

public class CheckoutPageObject extends BasePage {
	private WebDriver driver;

	public CheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectStateProvince(String idValue, String itemText) {
		waitForElementClickable(driver, CheckoutPageUI.STATE_PROVINCE_ITEM, idValue);
		selectItemInDefaultDropdown(driver, CheckoutPageUI.STATE_PROVINCE_ITEM, itemText, idValue);
	}

	public void clickToContinueButton(String idValue) {
		waitForElementClickable(driver, CheckoutPageUI.CONTINUE_BUTTON, idValue);
		clickToElement(driver, CheckoutPageUI.CONTINUE_BUTTON, idValue);
	}

	public void checkToPaymentRadionBox(String titleText) {
		waitForElementClickable(driver, CheckoutPageUI.PAYMENT_RADIO_BOX, titleText);
		checkToDefaultCheckboxOrRadio(driver, CheckoutPageUI.PAYMENT_RADIO_BOX, titleText);
	}
}
