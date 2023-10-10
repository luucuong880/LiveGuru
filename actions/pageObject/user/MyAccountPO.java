package pageObject.user;

import org.openqa.selenium.WebDriver;

import liveguru.user.BasePage;
import pageUI.user.MyAccountPageUI;

public class MyAccountPO extends BasePage {
	private WebDriver driver;

	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
	}

	public void openPageBlockAccountColumn(String blockPages) {
		waitForElementClickable(driver, MyAccountPageUI.PAGES_COLUMN, blockPages);
		clickToElement(driver, MyAccountPageUI.PAGES_COLUMN, blockPages);
	}

	public String getFieldsCorrectly(String boxText, String attributeName) {
		waitForElementVisible(driver, MyAccountPageUI.BOX_TEXT, boxText);
		return getElementAttribute(driver, MyAccountPageUI.BOX_TEXT, attributeName, boxText);
	}

}
