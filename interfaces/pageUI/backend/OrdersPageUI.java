package pageUI.backend;

public class OrdersPageUI {

	public static final String SALE_ORDERS_ITEM = "xpath=//select[@id='%s']";
	public static final String LOADING_MASK_ORDER = "xpath=//div[@id='loading-mask']";
	public static final String ORDERS_CHECKBOX = "xpath=//span[text()='Bill to Name']/ancestor::thead/following-sibling::tbody//td[contains(text(),'%s')] /preceding-sibling::td/input";

}
