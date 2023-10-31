package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import liveguru.frontend.PageGeneratorManager;
import pageUI.frontend.TVPageUI;

public class TVPageObject extends BasePage {
	private WebDriver driver;

	public TVPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public MyWishlistPO clickToAddWishListButton(String textValue, String classValue) {
		waitForElementClickable(driver, TVPageUI.ADD_LINKS, textValue, classValue);
		clickToElement(driver, TVPageUI.ADD_LINKS, textValue, classValue);
		return PageGeneratorManager.getPageGeneratorManager().getMyWishlistPage(driver);
	}

	public void clickToAddToCompareButton(String textValue, String classValue) {
		waitForElementClickable(driver, TVPageUI.ADD_LINKS, textValue, classValue);
		clickToElement(driver, TVPageUI.ADD_LINKS, textValue, classValue);
	}

	public ProductsPageObject clickProductsDetail(String text) {
		waitForElementClickable(driver, TVPageUI.PRODUCTS_DETAIL, text);
		clickToElement(driver, TVPageUI.PRODUCTS_DETAIL, text);
		return PageGeneratorManager.getPageGeneratorManager().getProductsPage(driver);
	}
}
