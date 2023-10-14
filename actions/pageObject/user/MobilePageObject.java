package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.MobilePageUI;

public class MobilePageObject extends BasePage {
	private WebDriver driver;

	public MobilePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCostOfProducts(String productsItem) {
		waitForElementVisible(driver, MobilePageUI.PRODUCTS_NAME, productsItem);
		return getElementText(driver, MobilePageUI.PRODUCTS_NAME, productsItem);
	}

}
