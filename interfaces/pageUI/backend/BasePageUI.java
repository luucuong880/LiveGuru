package pageUI.backend;

public class BasePageUI {
	public static final String SORT_WITH_CRITERIA = "xpath=(//span[text()='%s']/ancestor::thead/following-sibling::tbody)//td[%s]";
	public static final String HEADER_LINKS = "xpath=//div[@id='header-account']//a[contains(text(),'%s')]";
	public static final String TEXT_DYNAMICS = "xpath=//span[text()='%s']";
	public static final String BOX_TEXT = "xpath=//input[@id='%s']";
	public static final String TITLE_DYNAMIC = "xpath=//button[@title='%s']";
	public static final String HEADER_DASBOARD_TEXT = "xpath=//div[@class='%s']";
	public static final String LINKS_BUTTON = "xpath=//a[text()='%s']";
	public static final String TEXT_MESSAGE = "xpath=//ul[@class='messages']";
	public static final String MAGENTO_LOGO = "xpath=//a[@class='logo']";
	public static final String LOGOUT_BUTTON = "xpath=//a[@class='link-logout']";
	public static final String EMPTY_TEXT = "xpath=//td[@class='empty-text a-center']";

}
