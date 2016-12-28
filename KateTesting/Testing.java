import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.Assert;

public class KateTesting{
	
 private static File kateApk = new File("kate.apk");
 private static AndroidDriver<WebElement> driver;
 
 @BeforeClass
 public static void createDriver()
 {
	 DesiredCapabilities capability = new DesiredCapabilities();
	  capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	  capability.setCapability(MobileCapabilityType.APP, kateApk.getAbsolutePath());
	  capability.setCapability(MobileCapabilityType.DEVICE_NAME, "GalaxyS6");
	  capability.setCapability("platformVersion", 23);
	  capability.setCapability("noReset", false);
	  capability.setCapability("appWaitActivity", ".dbl.activity.FacebookLoginActivity");
	  try {
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:5555/wd/hub"), capability);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
 
 @Before
 public void goToMainActivity()
 {
	 driver.startActivity("com.perm.kate", "com.perm.kate.MainActivity");
	 WebDriverWait switcherWait = new WebDriverWait(driver, 30);
	 switcherWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.perm.kate:id/first_language_option")));
	 WebElement switcher = driver.findElementById("com.facebook.katana:id/first_language_option");
	 if(switcher.getText().equals("")) {
		 switcher.click();
	 }
 }	
 
 @Test
 public void falsePasswordTest()
 {
	 WebElement login = driver.findElementById("com.facebook.katana:id/login_username");
	 WebElement passwod = driver.findElementById("com.facebook.katana:id/login_password");
	 WebElement loginButton = driver.findElementById("com.facebook.katana:id/login_login");
	 WebDriverWait errorViewWait = new WebDriverWait(driver, 30);
	 login.clear();
	 login.sendKeys("lisicky@inbox.ru");
	 passwod.clear();
	 passwod.sendKeys("Q2asdasdasd33r23f12f2ff2f323");
	 loginButton.click();
	 errorViewWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.facebook.katana:id/alertTitle")));
	 WebElement errorView = driver.findElementById("com.facebook.katana:id/alertTitle");
	 Assert.assertEquals("", errorView.getText());
 }
 
 @Test
 public void invaligEmailLoginTest()
 {
	 WebElement login = driver.findElementById("com.facebook.katana:id/login_username");
	 WebElement passwod = driver.findElementById("com.facebook.katana:id/login_password");
	 WebElement loginButton = driver.findElementById("com.facebook.katana:id/login_login");
	 WebDriverWait errorViewWait = new WebDriverWait(driver, 30);
	 login.clear();
	 login.sendKeys("123123123SATAN666123123LLL@SATAN.HELL");
	 passwod.clear();
	 passwod.sendKeys("Q2asdasdasd33r23f12f2ff2f323");
	 loginButton.click();
	 errorViewWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.facebook.katana:id/alertTitle")));
	 WebElement errorView = driver.findElementById("com.facebook.katana:id/alertTitle");
	 Assert.assertEquals("", errorView.getText());
 }
 
 @Test
 public void InvalidPhoneNumberLoginTest()
 {
	 WebElement login = driver.findElementById("com.facebook.katana:id/login_username");
	 WebElement passwod = driver.findElementById("com.facebook.katana:id/login_password");
	 WebElement loginButton = driver.findElementById("com.facebook.katana:id/login_login");
	 WebDriverWait errorViewWait = new WebDriverWait(driver, 30);
	 login.clear();
	 login.sendKeys("--3333");
	 passwod.clear();
	 passwod.sendKeys("Q2asdasdasd33r23f12f2ff2f323");
	 loginButton.click();
	 errorViewWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.facebook.katana:id/alertTitle")));
	 WebElement errorView = driver.findElementById("com.facebook.katana:id/alertTitle");
	 Assert.assertEquals("", errorView.getText());
 }
 
 @Test
 public void swichFirstLanguage()
 {
	 WebElement switcher = driver.findElementById("com.facebook.katana:id/first_language_option");
	 WebElement forgetButton = driver.findElementById("com.facebook.katana:id/login_forgot_password");
	 String expected = "";
	 switch(switcher.getText())
	 {
		 case "English" : expected = "Forgot Password?";
			 break;
		 case "" : expected = "?";
			 break;
	 }
	 switcher.click();
	 Assert.assertEquals(expected, forgetButton.getText());
 }
 
 @Test
 public void addWidgetTest()
 {
	 driver.pressKeyCode(AndroidKeyCode.HOME);
	 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 TouchAction action = new TouchAction(driver);
	 action.longPress(500,500, 2000).release().perform();
	 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	WebElement widgetsButton = driver.findElementById("com.android.launcher3:id/widget_button");
	widgetsButton.click();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	action = new TouchAction(driver);
	action.longPress(600,1200, 2000).release().perform();
	WebDriverWait createButtonWait = new WebDriverWait(driver, 30);
	createButtonWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
	WebElement createButton = driver.findElementById("android:id/button1");
	createButton.click();
	
 }
}