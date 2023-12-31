package pageUI.frontend;

public class MobilePageUI {

	public static final String PRODUCTS_NAME = "xpath=//a[@title='%s']/following-sibling::div/descendant::span[@class='price']";
	public static final String ADD_LINKS = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']//a[@class='%s']";
	public static final String COMPARE_BUTTON = "xpath=//button[@title='Compare']";
	public static final String IMAGE_PRODUCT = "xpath=//a[@title='Xperia']";
	public static final String REVIEWS_LINK = "xpath=//a[contains(text(),'Review(s)')]";
}
