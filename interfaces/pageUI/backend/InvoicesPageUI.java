package pageUI.backend;

public class InvoicesPageUI {

	public static final String SORT_WITH_CRITERIA = "xpath=(//span[text()='Invoice Date']/ancestor::thead/following-sibling::tbody)//td[%s]";
	public static final String SORT_TEXT_DYNAMICS = "xpath=//span[text()='%s']";
	public static final String LOADING_MASK_ORDER = "xpath=//div[@id='loading-mask']";

}
