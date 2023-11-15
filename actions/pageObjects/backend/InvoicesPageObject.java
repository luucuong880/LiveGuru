package pageObjects.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import liveguru.backend.BasePage;
import pageUI.backend.InvoicesPageUI;

public class InvoicesPageObject extends BasePage {
	private WebDriver driver;

	public InvoicesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSortByAscending(String sortPosition) {
		ArrayList<String> productUIList = new ArrayList<String>();

		List<WebElement> productNameText = getListWebElement(driver, InvoicesPageUI.SORT_WITH_CRITERIA, sortPosition);

		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}

		ArrayList<String> productSortList = new ArrayList<String>();

		for (String product : productUIList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);

		return productSortList.equals(productUIList);
	}

	public boolean isSortByDescending(String sortPosition) {
		ArrayList<String> productUIList = new ArrayList<String>();

		List<WebElement> productNameText = getListWebElement(driver, InvoicesPageUI.SORT_WITH_CRITERIA, sortPosition);

		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}

		ArrayList<String> productSortList = new ArrayList<String>();

		for (String product : productUIList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);

		Collections.reverse(productSortList);

		return productSortList.equals(productUIList);
	}

	public void clickToSortButtonByDynamics(String text) {
		waitForElementClickable(driver, InvoicesPageUI.SORT_TEXT_DYNAMICS, text);
		clickToElement(driver, InvoicesPageUI.SORT_TEXT_DYNAMICS, text);
		waitForElementInVisible(driver, InvoicesPageUI.LOADING_MASK_ORDER);
	}

	public boolean isSortPriceByAscending(String sortPosition) {
		ArrayList<Float> productUIList = new ArrayList<Float>();

		List<WebElement> productPriceText = getListWebElement(driver, InvoicesPageUI.SORT_WITH_CRITERIA, sortPosition);

		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "$")));
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();

		for (Float product : productUIList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);

		return productSortList.equals(productUIList);
	}

	public boolean isSortPriceByDescending(String sortPosition) {
		ArrayList<Float> productUIList = new ArrayList<Float>();

		List<WebElement> productPriceText = getListWebElement(driver, InvoicesPageUI.SORT_WITH_CRITERIA, sortPosition);

		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "$")));
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();

		for (Float product : productUIList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);

		Collections.reverse(productSortList);

		return productSortList.equals(productUIList);
	}

}
