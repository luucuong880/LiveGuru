package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import pageUI.backend.OrdersPageUI;

public class OrdersPageObject extends BasePage {
	private WebDriver driver;

	public OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSaleOrders(String idValue, String itemValue) {
		waitForElementClickable(driver, OrdersPageUI.SALE_ORDERS_ITEM, idValue);
		selectItemInDefaultDropdown(driver, OrdersPageUI.SALE_ORDERS_ITEM, itemValue, idValue);
		waitForElementInVisible(driver, OrdersPageUI.LOADING_MASK_ORDER);
	}

	public void checkToOrdersCheckbox(String textValue) {
		waitForElementClickable(driver, OrdersPageUI.ORDERS_CHECKBOX, textValue);
		checkToDefaultCheckboxOrRadio(driver, OrdersPageUI.ORDERS_CHECKBOX, textValue);
	}

	public void downloadFileAndDeleteFile(String fullFileName, String fileName) throws Exception {
		waitForDownloadFileFullnameCompleted(fullFileName);
		deleteContainName(fileName);
	}

}