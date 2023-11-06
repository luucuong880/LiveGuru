package pageUI.backend;

public class PendingReviewPageUI {

	public static final String USER_FRONT_END = "xpath=//a[@name='%s']/ancestor::thead/following-sibling::tbody/tr[1]/td[contains(text(),'%s')]";
	public static final String EDIT_BUTTON = "xpath=//span[text()='%s']/ancestor::thead/following-sibling::tbody//td[contains(text(),'%s')]/parent::tr//a[text()='Edit']";

}
