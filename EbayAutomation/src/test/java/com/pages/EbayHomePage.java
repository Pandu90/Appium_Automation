package com.pages;

import org.openqa.selenium.By;

import com.utils.DriverActions;

import io.appium.java_client.AppiumDriver;

/**
 * Page object model Class for ebay home screen. Contains methods to navigate to
 * sign in screen in the app.
 * 
 */

public class EbayHomePage extends DriverActions {
	By sideMenu = By.id("com.ebay.mobile:id/home");
	By signIn = By.id("com.ebay.mobile:id/home");

	@SuppressWarnings("rawtypes")
	public EbayHomePage(AppiumDriver driver) {
		super(driver);
	}

	public void navigateSignIn() {
		if(getOrientation()=="landscape") {
			rotateScreen("portrait");
		}
		clickOnElement(sideMenu);
		clickOnElement(signIn);
	}
}
