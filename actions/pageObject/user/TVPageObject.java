package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import liveguru.user.PageGeneratorManager;
import pageUI.user.TVPageUI;

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
}
