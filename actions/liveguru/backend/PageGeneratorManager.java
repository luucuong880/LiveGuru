package liveguru.backend;

import org.openqa.selenium.WebDriver;

import pageObject.frontend.HomePageObject;
import pageObjects.backend.CustomerBackEndPO;

public class PageGeneratorManager {

	public static PageGeneratorManager getPageGeneratorManager() {
		return new PageGeneratorManager();
	}

	public CustomerBackEndPO getCustomerBackEndPage(WebDriver driver) {
		return new CustomerBackEndPO(driver);
	}

	public HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

}
