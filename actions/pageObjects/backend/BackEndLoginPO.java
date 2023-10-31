package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import liveguru.backend.BasePage;
import liveguru.backend.PageGeneratorManager;
import pageUI.backend.LoginPageUI;

public class BackEndLoginPO extends BasePage {
	private WebDriver driver;

	public BackEndLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToFieldBackEndbox(String idValue, String textValue) {
		waitForElementVisible(driver, LoginPageUI.TEXT_BOX_FIELD, idValue);
		sendkeyToElement(driver, LoginPageUI.TEXT_BOX_FIELD, textValue, idValue);
	}

	public CustomerBackEndPO loginWithBackEndInfo(String idValue, String textValue, String idValue1, String textValue1) {
		inputToFieldBackEndbox(idValue, textValue);
		inputToFieldBackEndbox(idValue1, textValue1);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		waitForElementClickable(driver, LoginPageUI.CLOSE_MESSAGE_BUTTON);
		clickToElement(driver, LoginPageUI.CLOSE_MESSAGE_BUTTON);
		return PageGeneratorManager.getPageGeneratorManager().getCustomerBackEndPage(driver);
	}
}
