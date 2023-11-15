package pageUI.backend;

public class OrdersPageUI {

	public static final String SALE_ORDERS_ITEM = "xpath=//select[@id='%s']";
	public static final String LOADING_MASK_ORDER = "xpath=//div[@id='loading-mask']";
	public static final String ORDERS_CHECKBOX = "xpath=(//input[@type='checkbox'])[%s]";
	public static final String VIEW_PER_PAGE = "xpath=//select[@name='limit']";
	public static final String VIEW_PER_PAGE_SIZE = "xpath=(//span[text()='Order #']/ancestor::thead/following-sibling::tbody)//td[2]";
}
