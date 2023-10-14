package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.ProductsPageUI;

public class ProductsPageObject extends BasePage {
	private WebDriver driver;

	public ProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCostOfProducts() {
		waitForElementVisible(driver, ProductsPageUI.PRICE_OF_PRODUCTS);
		return getElementText(driver, ProductsPageUI.PRICE_OF_PRODUCTS);
	}
}
