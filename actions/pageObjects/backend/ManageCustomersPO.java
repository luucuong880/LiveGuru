package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import pageUI.backend.ManageCustomersPageUI;

public class ManageCustomersPO extends BasePage {
	public WebDriver driver;

	public ManageCustomersPO(WebDriver driver) {
		this.driver = driver;
	}

	public void selectCountryID(String idValue, String textItem) {
		waitForElementClickable(driver, ManageCustomersPageUI.COUNTRY_ID, idValue, textItem);
		selectItemInDefaultDropdown(driver, ManageCustomersPageUI.COUNTRY_ID, textItem, idValue);
	}

}
