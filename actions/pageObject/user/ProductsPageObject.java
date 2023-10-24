package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import liveguru.user.PageGeneratorManager;
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

	public String getSuccessText() {
		waitForElementVisible(driver, ProductsPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, ProductsPageUI.SUCCESS_MESSAGE);
	}

	public ShoppingCartPageObject clickToAddToCartButton() {
		waitForElementClickable(driver, ProductsPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, ProductsPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.getPageGeneratorManager().getCheckoutPage(driver);
	}
}
