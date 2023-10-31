package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import pageUI.frontend.AdvencedSearchPageUI;

public class AdvencedSearchPO extends BasePage {
	private WebDriver driver;

	public AdvencedSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		waitForElementVisible(driver, AdvencedSearchPageUI.PAGE_TITLE);
		return getElementText(driver, AdvencedSearchPageUI.PAGE_TITLE);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdvencedSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdvencedSearchPageUI.SEARCH_BUTTON);
	}

	public String getSearchAmountText() {
		waitForElementVisible(driver, AdvencedSearchPageUI.AMOUNT_TEXT);
		return getElementText(driver, AdvencedSearchPageUI.AMOUNT_TEXT);
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisible(driver, AdvencedSearchPageUI.PRODUCT_NAME, productName);
		return isElementDisplayed(driver, AdvencedSearchPageUI.PRODUCT_NAME, productName);
	}

	public String getPriceOfProduct(String text, String idValue) {
		waitForElementVisible(driver, AdvencedSearchPageUI.PRICE_TEXT, text, idValue);
		return getElementText(driver, AdvencedSearchPageUI.PRICE_TEXT, text, idValue);
	}
}
