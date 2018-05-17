package com.tests;

import java.net.MalformedURLException;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
	@Test
	public void validateProductCheckout() {
		try {
			
			Reporter.log("Application Lauched successfully |");
			new EbayHomePage(driver).navigateSignIn();
			Reporter.log("Navigated to Sign in successfully | ");
			new SignInPage(driver).loginIn();
			Reporter.log("Successfully logged in | ");
			new SignInPage(driver).denyGoogleLinkRequest();
			new SearchPage(driver).searchProduct();
			Reporter.log("Product searched successfully | ");
			new PurchasePage(driver).verifyProductDetails();
			Reporter.log("Product bought successfully ");
		} catch (Exception e) {
			System.out.println(e);
			Reporter.log("Application Testing Failed with following error : " + e);
		}
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		// Calling initializing driver method for android testing.
		initializeAppiumDriver();
	}

	@AfterTest
	public void afterTest() {
		// Quitting the Android driver and stopping appium server after everything is done.
		driver.quit();
		stopAppium();
	}

}
