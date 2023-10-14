package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;

public class TVPageObject extends BasePage {
	private WebDriver driver;

	public TVPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
