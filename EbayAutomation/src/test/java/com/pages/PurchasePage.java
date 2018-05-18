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
public class PurchasePage extends DriverActions {

	By itemTitle = By.id("com.ebay.mobile:id/textview_item_name");
	By itemSubtitle = By.id("com.ebay.mobile:id/item_subtitle_textview");
	By itemPrice = By.id("com.ebay.mobile:id/textview_item_price");
	
	public static String itemTitle_str;
	public static String itemPrice_str;
	
	@SuppressWarnings("rawtypes")
	public PurchasePage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_product");
	}

	/*
	 * Java method to automate the searched product details verification flow
	 */
	public void verifyProductDetails() throws Exception {
		
		if(getOrientation()=="landscape") {
			rotateScreen("portrait");
		}
		waitForVisibilityOf(itemTitle, 30);
		
		itemTitle_str = getText(itemTitle);
		itemPrice_str = getText(itemPrice).substring(1);
		
		System.out.println(itemTitle_str +" and "+itemPrice_str);
		
		validateResult(itemTitle_str,SearchPage.productTitle_str);
		validateResult(itemPrice_str,SearchPage.productPrice_str);
	}

}
