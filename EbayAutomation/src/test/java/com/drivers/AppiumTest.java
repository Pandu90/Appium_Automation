package com.drivers;

import org.testng.annotations.Test;
import com.pages.EbayHomePage;
import com.pages.SearchPage;
import com.pages.SignInPage;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

/**
 * TestNg class where actual test executes. "validateProductCheckout" method
 * executes the complete flow of the testing from sign,searching the product and
 * buying it.
 * 
 */

public class AppiumTest extends AndroidDriverSetup {
	@Test
	public void validateProductCheckout() {
		try {
			Reporter.log("Application Lauched successfully |");
			new EbayHomePage(driver).navigateSignIn();
			Reporter.log("Navigated to Sign in successfully | ");
			
		} catch (Exception e) {
			System.out.println(e);
			Reporter.log("Application Testing Failed with following error : " + e);
		}
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		// Calling initializing driver method for android testing.
		prepareAndroidForAppium();
	}

	@AfterTest
	public void afterTest() {
		// Quitting the driver after everything is done.
		driver.quit();
	}

}
