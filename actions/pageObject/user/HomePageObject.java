package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMessageTextUndisplayed() {
		return isElementDisplayed(driver, HomePageUI.TEXT_UNDEISPLAYED);
	}

}
