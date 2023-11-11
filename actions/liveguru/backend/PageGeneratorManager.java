package liveguru.backend;

import org.openqa.selenium.WebDriver;

import pageObjects.backend.BackEndLoginPO;
import pageObjects.backend.CustomerBackEndPO;
import pageObjects.backend.EditReviewPageObject;
import pageObjects.backend.OrdersPageObject;
import pageObjects.backend.PendingReviewPO;

public class PageGeneratorManager {

	public static PageGeneratorManager getPageGeneratorManager() {
		return new PageGeneratorManager();
	}

	public BackEndLoginPO getBackEndLoginPage(WebDriver driver) {
		return new BackEndLoginPO(driver);
	}

	public CustomerBackEndPO getCustomerBackEndPage(WebDriver driver) {
		return new CustomerBackEndPO(driver);
	}

	public PendingReviewPO getPendingReviewPage(WebDriver driver) {
		return new PendingReviewPO(driver);
	}

	public EditReviewPageObject getEditReviewPage(WebDriver driver) {
		return new EditReviewPageObject(driver);
	}

	public OrdersPageObject getOrdersPage(WebDriver driver) {
		return new OrdersPageObject(driver);
	}

}
