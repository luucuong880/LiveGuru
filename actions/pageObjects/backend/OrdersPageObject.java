package pageObjects.backend;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public void selectViewPerPage(String textItem) {
		waitForElementClickable(driver, OrdersPageUI.VIEW_PER_PAGE);
		selectItemInDefaultDropdown(driver, OrdersPageUI.VIEW_PER_PAGE, textItem);
		waitForElementInVisible(driver, OrdersPageUI.LOADING_MASK_ORDER);
	}

	public Object getViewPerPageSize() {
		waitForElementVisible(driver, OrdersPageUI.VIEW_PER_PAGE_SIZE);
		return getElementSize(driver, OrdersPageUI.VIEW_PER_PAGE_SIZE);
	}

	public boolean isOrdersChecked(String text) {

		List<WebElement> elements = getListWebElement(driver, OrdersPageUI.ORDERS_CHECKED, text);
		for (WebElement element : elements) {
			element.getSize();
		}
		return isElementSelected(driver, OrdersPageUI.ORDERS_CHECKED, text);
	}

	public Object getMessageDisplayed(String position) {
		waitForElementVisible(driver, OrdersPageUI.MESSAGE_TEXT, position);
		return getElementText(driver, OrdersPageUI.MESSAGE_TEXT, position);
	}

}
