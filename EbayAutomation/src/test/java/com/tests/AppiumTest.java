package com.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.drivers.DriverSetup;
import com.pages.EbayHomePage;
import com.pages.PurchasePage;
import com.pages.SearchPage;
import com.pages.SignInPage;

/**
 * TestNg class where actual test executes. "validateProductCheckout" method
 * executes the complete flow of the testing from sign,searching the product and
 * buying it.
 * 
 */

public class AppiumTest extends DriverSetup {

	/*
	 * TestNG Test method to execute the automation flow of eBay app verify
	 * product details scenario
	 */
	@Test
	public void validateProductCheckout() {
		try {

			Reporter.log("Application Lauched successfully |");
			new EbayHomePage(driver).navigateSignIn();
			Reporter.log("Navigated to Sign in successfully | ");
			new SignInPage(driver).loginIn();
			Reporter.log("Successfully logged in | ");
			new SignInPage(driver).denyGoogleLinkRequest();
			Reporter.log("Clicked on No Thanks button successfully | ");
			new SearchPage(driver).searchProduct();
			Reporter.log("Product searched successfully | ");
			new PurchasePage(driver).verifyProductDetails();
			Reporter.log("Product details verified successfully ");

		} catch (Exception e) {
			System.out.println(e);
			Reporter.log("Application Testing Failed with following error : " + e);
		}
	}

	

}
