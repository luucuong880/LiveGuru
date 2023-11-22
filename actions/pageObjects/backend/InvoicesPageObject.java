package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import pageUI.backend.InvoicesPageUI;

public class InvoicesPageObject extends BasePage {
	private WebDriver driver;

	public InvoicesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSortButtonByDynamics(String text) {
		waitForElementClickable(driver, InvoicesPageUI.SORT_TEXT_DYNAMICS, text);
		clickToElement(driver, InvoicesPageUI.SORT_TEXT_DYNAMICS, text);
		waitForElementInVisible(driver, InvoicesPageUI.LOADING_MASK_ORDER);
	}

}
