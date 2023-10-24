package pageUI.user;

public class MyCartPageUI {
	public static final String TEXT_DYNAMICS = "xpath=//td[contains(text(),'%s')]/parent::tr//span[text()='%s']/ancestor::tr";
	public static final String BUTTON_TITLE = "xpath=//button[@title='%s']";
	public static final String GRAND_TOTAL_TEXT = "xpath=//strong[text()='Grand Total']/ancestor::tr//span[@class='price']/ancestor::tr";
	public static final String QTY_TEXT_BOX = "xpath=//input[@class='input-text qty']";
	public static final String ITEM_ERROR_MESSAGE = "xpath=//p[@class='item-msg error']";
	public static final String EMPTY_CART_MESSAGE = "xpath=//div[@class='cart-empty']";

}
