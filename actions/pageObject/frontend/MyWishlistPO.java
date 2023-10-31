package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import pageUI.frontend.MyWishlistPageUI;

public class MyWishlistPO extends BasePage {
	private WebDriver driver;

	public MyWishlistPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToTextAreaBox(String idValue, String textValue) {
		waitForElementVisible(driver, MyWishlistPageUI.TEXT_AREA, idValue);
		sendkeyToElement(driver, MyWishlistPageUI.TEXT_AREA, textValue, idValue);
	}

	public boolean isProductDiplayed() {
		waitForElementVisible(driver, MyWishlistPageUI.PRODUCT_TITLE);
		return isElementDisplayed(driver, MyWishlistPageUI.PRODUCT_TITLE);
	}

}
