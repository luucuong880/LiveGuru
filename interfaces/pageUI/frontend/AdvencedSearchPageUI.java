package pageUI.frontend;

public class AdvencedSearchPageUI {

	public static final String PAGE_TITLE = "xpath=//div[@class='page-title']";
	public static final String SEARCH_BUTTON = "xpath=//div[@class='buttons-set']/button";
	public static final String AMOUNT_TEXT = "xpath=//p[@class='advanced-search-amount']";
	public static final String PRODUCT_NAME = "xpath=//a[text()='%s']";
	public static final String PRICE_TEXT = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='price-box']//span[@id='%s']";

}
