package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import liveguru.frontend.PageGeneratorManager;
import pageUI.frontend.MyAccountPageUI;

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

	public String getTextHeaderDasboard() {
		waitForElementVisible(driver, MyAccountPageUI.HEADER_TEXT);
		return getElementText(driver, MyAccountPageUI.HEADER_TEXT);
	}

	public ComparePageObject openComparePage() {
		waitForElementVisible(driver, MyAccountPageUI.COMPARE_LINK_PAGE);
		switchToFrameIframe(driver, MyAccountPageUI.COMPARE_LINK_PAGE);
		return PageGeneratorManager.getPageGeneratorManager().getComparePage(driver);
	}

}
