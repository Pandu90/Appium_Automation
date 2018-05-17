package com.pages;



import org.openqa.selenium.By;

import com.utils.DriverActions;
import com.utils.ExcelUtils;

import io.appium.java_client.AppiumDriver;
/**
 * Page object model Class for sign in screen. Contains methods to navigate to
 * login in the app.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.k@cognizant.com
 */

public class SignInPage extends DriverActions {
	By username = By.id("com.ebay.mobile:id/edit_text_username");
	By password = By.id("com.ebay.mobile:id/edit_text_password");
	By signInButton = By.id("com.ebay.mobile:id/button_sign_in");
	
	By noThanksButton = By.id("com.ebay.mobile:id/button_google_deny");
	
	@SuppressWarnings("rawtypes")
	public SignInPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "sign_in");
	}

	/*
	 * Java method to login to eBay application
	 */
	public void loginIn() throws Exception {
		
		if(getOrientation()=="landscape") {
			rotateScreen("portrait");
		}
		waitForVisibilityOf(username, 30);
		
		// Fetching the data from excel i.e username & password in this case.
		sendKeysToElement(username, ExcelUtils.getCellData(1, 1));
		sendKeysToElement(password, ExcelUtils.getCellData(1, 2));
		waitForClickabilityOf(signInButton,30);
		clickOnElement(signInButton);
	}
	/*
	 * java method to click on No Thanks button in Link account page
	 */
	public void denyGoogleLinkRequest() {
		
		waitForVisibilityOf(noThanksButton, 30);
		clickOnElement(noThanksButton);
	}
	
	
}