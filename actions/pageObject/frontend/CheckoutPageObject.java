package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import pageUI.frontend.CheckoutPageUI;

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

	public String getPageTitleText() {
		waitForElementVisible(driver, CheckoutPageUI.PAGE_TITLE);
		return getElementText(driver, CheckoutPageUI.PAGE_TITLE);
	}
}
