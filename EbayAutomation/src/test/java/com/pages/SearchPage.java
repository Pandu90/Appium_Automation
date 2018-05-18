package com.pages;

import org.openqa.selenium.By;

import com.utils.DriverActions;
import com.utils.ExcelUtils;

import io.appium.java_client.AppiumDriver;

/**
 * Page object model Class for search screen. Contains methods for searching the
 * product in the app.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.k@cognizant.com
 * 
 */
public class SearchPage extends DriverActions {

	By searchBox = By.id("com.ebay.mobile:id/search_box");
	By searchBoxTxt = By.id("com.ebay.mobile:id/search_src_text");
	By searchIcon = By.id("com.ebay.mobile:id/menu_search");
	By selectProduct = By.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]");
	By productTitle = By.xpath(
			"//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]/android.widget.RelativeLayout/android.widget.TextView");
	By productPrice = By.xpath(
			"//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
	By dismissPopUp = By.id("com.ebay.mobile:id/textview_item_count");

	public static String productTitle_str;
	public static String productPrice_str;

	@SuppressWarnings("rawtypes")
	public SearchPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_product");
	}
/*
 * Java method to automate the search product flow in ebay app
 */
	public void searchProduct() throws Exception {
		if (getOrientation() == "landscape") {
			rotateScreen("portrait");
		}
		waitForVisibilityOf(searchBox, 30);
		clickOnElement(searchBox);

		// Fetching the data from excel i.e product name to be searched in this case
		waitForVisibilityOf(searchBoxTxt, 30);

		sendKeysToElement(searchBoxTxt, ExcelUtils.getCellData(1, 0) + "\n");

		//Since Enter search option is able to inspect, tapping on using co-ordinates
		tapOnElement(1267, 2438);

		implicitWait(1000);

		clickOnElement(dismissPopUp);

		swipeScreen(521, 1223, 4, -269);

		productTitle_str = getText(productTitle);
		productPrice_str = getText(productPrice).substring(1);

		System.out.println(productTitle_str + " and " + productPrice_str);

		clickOnElement(selectProduct);

		implicitWait(2000);

	}

}
