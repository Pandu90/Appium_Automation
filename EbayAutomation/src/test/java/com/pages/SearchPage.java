package com.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.DriverActions;
import com.utils.ExcelUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidKeyCode;

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
	//By selectOption = By.xpath(
	//		"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.TextView");

	By selectProduct=By.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]");
	
	//By productTitle = By.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]/[@class ='android.widget.RelativeLayout']/android.widget.TextView[@id='com.ebay.mobile:id/textview_item_title'][0]");
	By productTitle = By.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]/android.widget.RelativeLayout/android.widget.TextView");
	
	By productPrice = By.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item'][2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
	
	
	By dismissPopUp = By.id("com.ebay.mobile:id/textview_item_count");
	
	public static String productTitle_str;
	public static String productPrice_str;
	
	@SuppressWarnings("rawtypes")
	public SearchPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_product");
	}

	public void searchProduct() throws Exception {
		if(getOrientation()=="landscape") {
			rotateScreen("portrait");
		}
		waitForVisibilityOf(searchBox, 30);
		clickOnElement(searchBox);

		// Fetching the data from excel i.e product name to be searched in this case.
		waitForVisibilityOf(searchBoxTxt, 30);
		
		sendKeysToElement(searchBoxTxt, ExcelUtils.getCellData(1, 0)+"\n");
		
		tapOnElement(1267, 2438);
		
		Thread.sleep(2000);
		
		clickOnElement(dismissPopUp);
		
		swipeScreen(521, 1223, 4, -269);
		
		productTitle_str = getText(productTitle);
		productPrice_str = getText(productPrice).substring(1);
		
		System.out.println(productTitle_str +" and "+productPrice_str);
		
		clickOnElement(selectProduct);
		
		Thread.sleep(3000);
	
	}

}
