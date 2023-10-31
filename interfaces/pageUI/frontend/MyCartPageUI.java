package pageUI.frontend;

public class MyCartPageUI {
	public static final String TEXT_DYNAMICS = "xpath=//td[contains(text(),'%s')]/parent::tr//span[text()='%s']/ancestor::tr";
	public static final String BUTTON_TITLE = "xpath=//button[@title='%s']";
	public static final String GRAND_TOTAL_TEXT = "xpath=//strong[text()='Grand Total']/ancestor::tr//span[@class='price']/ancestor::tr";
	public static final String QTY_TEXT_BOX = "xpath=//input[@class='input-text qty']";
	public static final String ITEM_ERROR_MESSAGE = "xpath=//p[@class='item-msg error']";
	public static final String EMPTY_CART_MESSAGE = "xpath=//div[@class='cart-empty']";
	public static final String ITEM_SELECTED = "xpath=//select[@id='%s']";
	public static final String SHIPPING_COST = "xpath=//form[@id='co-shipping-method-form']//span[@class='price']";
	public static final String FLAT_RATE_BOX = "xpath=//input[@name='estimate_method']";
	public static final String TOTAL_COST_TEXT = "xpath=//strong[text()='Grand Total']/parent::td/following-sibling::td//span";

}
