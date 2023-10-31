package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import liveguru.backend.PageGeneratorManager;
import pageUI.backend.CustomerBackEndPageUI;

public class CustomerBackEndPO extends BasePage {
	private WebDriver driver;

	public CustomerBackEndPO(WebDriver driver) {
		this.driver = driver;
	}

	public PendingReviewPO clickPageAtCatlogPages(String text, String text1, String text2, String text3) {
		hoverMouseToElement(driver, CustomerBackEndPageUI.TEXT_DYNAMICS, text);
		hoverMouseToElement(driver, CustomerBackEndPageUI.TEXT_DYNAMICS, text1);
		hoverMouseToElement(driver, CustomerBackEndPageUI.TEXT_DYNAMICS, text2);
		waitForElementClickable(driver, CustomerBackEndPageUI.TEXT_DYNAMICS, text3);
		clickToElement(driver, CustomerBackEndPageUI.TEXT_DYNAMICS, text3);
		return PageGeneratorManager.getPageGeneratorManager().getPendingReviewPage(driver);
	}
}
