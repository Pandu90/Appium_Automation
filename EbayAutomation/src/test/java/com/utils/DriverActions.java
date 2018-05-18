package com.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

/**
 * Class for all the performing actions in the device. Contains methods for
 * performing actions like sendkeys, click, wait, swiping, assert, rotation etc.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.k@cognizant.com
 */

@SuppressWarnings("rawtypes")
public class DriverActions {
	
	protected AppiumDriver driver;

	public DriverActions(AppiumDriver driver) {
		this.driver = driver;
	}

	protected void clickOnElement(By elementSelector) {
		driver.findElement(elementSelector).click();
	}

	protected void tapOnElement(int x, int y) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(x, y).perform();
	}

	protected void sendKeysToElement(By elementSelector, String value) {
		driver.findElement(elementSelector).sendKeys(value);
	}

	protected void clickOnEnter() {
		// driver.press_keycode(66);
	}

	@SuppressWarnings("unchecked")
	protected List<WebElement> getAllElements(By elementSelector) {
		List<WebElement> childElements = driver.findElements(By.className("android.view.View"));
		return childElements;
	}

	protected void rotateScreen(String orientation) {
		ScreenOrientation position;
		switch (orientation) {
		case "portrait":
			position = ScreenOrientation.PORTRAIT;
			break;
		case "landscape":
			position = ScreenOrientation.LANDSCAPE;
			break;
		default:
			position = ScreenOrientation.PORTRAIT;

		}
		driver.rotate(position);
	}

	protected String getOrientation() {
		return driver.getOrientation().value();
	}

	protected void swipeScreen(int xStart, int yStart, int xEnd, int yEnd) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(xStart, yStart).moveTo(xEnd, xEnd).release().perform();
	}

	protected void waitForVisibilityOf(By locator, long timeOutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void waitForClickabilityOf(By locator, long timeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	protected String getText(By elementSelector) {
		try {
			String text = driver.findElement(elementSelector).getText();
			return text;
		} catch (Exception e) {
			return null;
		}

	}

	protected void validateResult(String textOne, String textTwo) {
		try {
			Assert.assertEquals(textOne, textTwo);
		} catch (Exception e) {
			System.out.println("Exception occured");
		}

	}

	protected void implicitWait(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

}
