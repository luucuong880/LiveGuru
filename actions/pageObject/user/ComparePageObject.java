package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.ComparePageUI;

public class ComparePageObject extends BasePage {
	private WebDriver driver;

	public ComparePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCostOfProduct(String idValue) {
		waitForElementVisible(driver, ComparePageUI.PRODUCT_COST, idValue);
		return getElementText(driver, ComparePageUI.PRODUCT_COST, idValue);
	}

	public boolean isProductsDisplayed(String textValue) {
		waitForElementVisible(driver, ComparePageUI.PRODUCTS_TEXT, textValue);
		return isElementDisplayed(driver, ComparePageUI.PRODUCTS_TEXT, textValue);
	}

	public void clickToCloseWindowButton() {
		waitForElementClickable(driver, ComparePageUI.CLOSE_WINDOW_BUTTON);
		clickToElement(driver, ComparePageUI.CLOSE_WINDOW_BUTTON);
		switchToWindowByTitle(driver, "Mobile");
	}
}
