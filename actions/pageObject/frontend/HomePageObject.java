package pageObject.frontend;

import org.openqa.selenium.WebDriver;

import liveguru.frontend.BasePage;
import liveguru.frontend.PageGeneratorManager;
import pageUI.frontend.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMessageTextUndisplayed() {
		return isElementDisplayed(driver, HomePageUI.TEXT_UNDEISPLAYED);
	}

	public YourReviewPageObject openLink() {
		openPageUrl(driver, "http://live.techpanda.org/index.php/review/product/list/id/1/");
		return PageGeneratorManager.getPageGeneratorManager().getYourReviewePage(driver);
	}

}
