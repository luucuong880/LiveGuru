package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;

public class MyCartPageObject extends BasePage {
	private WebDriver driver;

	public MyCartPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
