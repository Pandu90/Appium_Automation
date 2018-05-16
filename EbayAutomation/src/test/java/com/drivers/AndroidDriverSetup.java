package com.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.utils.PropertiesClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Class to setup and initialize android driver for appium.
 * Reading and adding the desired capabilities from the properties file.
 * 
 */

@SuppressWarnings("rawtypes")
public class AndroidDriverSetup {
	protected AppiumDriver driver;

	protected void prepareAndroidForAppium() throws MalformedURLException {
		PropertiesClass appiumProperties = new PropertiesClass();
		System.out.println("Entering into automation");
		File app = null;

		// Fetching the application (.apk) path if InstallApplicationInDevice property
		// is true.
		if (Boolean.parseBoolean(appiumProperties.getInstance().getProperty("InstallApplicationInDevice"))) {
			System.out.println(
					Boolean.parseBoolean(appiumProperties.getInstance().getProperty("InstallApplicationInDevice")));
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/apps/");
			app = new File(appDir, appiumProperties.getInstance().getProperty("Android_APK_Name"));
		}

		// Desired capabilities initialization for test execution.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", appiumProperties.getInstance().getProperty("Device_Name"));
		capabilities.setCapability("platformVersion", appiumProperties.getInstance().getProperty("Platform_Version"));
		capabilities.setCapability("platformName", appiumProperties.getInstance().getProperty("platformVersion"));

		// Enabling application (.apk) install if InstallApplicationInDevice property is true.
		if (Boolean.parseBoolean(appiumProperties.getInstance().getProperty("InstallApplicationInDevice"))) {
			capabilities.setCapability("app", app.getAbsolutePath());
		}

		capabilities.setCapability("appPackage",
				appiumProperties.getInstance().getProperty("Application_Package_Name"));
		capabilities.setCapability("appActivity",
				appiumProperties.getInstance().getProperty("Application_MainActivity_Name"));
		capabilities.setCapability("automationName", "UiAutomator2");

		try {
		// Appium server url.
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Thread.sleep(50000);
		/*System.out.println(driver.getContextHandles().size());
		Set<String> name = driver.getContextHandles();
		
		for(String con : name) {
			System.out.println(con);
		}
		driver.context("NATIVE_APP");*/
		//driver.findElementById("com.ebay.mobile:id/button_sign_in").click();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
