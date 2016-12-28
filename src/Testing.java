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

public class Testing{
	
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
	  capability.setCapability("appWaitActivity", "com.perm.kate.LoginActivity2");
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
	 switcherWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.perm.kate:id/fl_button_menu")));
 }	
 
 @Test
 public void falsePasswordTest()
 {
	 WebElement login = driver.findElementById("com.perm.kate:id/username");
	 WebElement passwod = driver.findElementById("com.perm.kate:id/password");
	 WebElement loginButton = driver.findElementById("com.perm.kate:id/login_btn");
	 WebDriverWait errorViewWait = new WebDriverWait(driver, 30);
	 login.clear();
	 login.sendKeys("aleksey.chepurko@gmail.com");
	 passwod.clear();
	 passwod.sendKeys("jsgb ghioas");
	 loginButton.click();
	 WebElement errorView = driver.findElementById("com.perm.kate:id/login_btn");
	 Assert.assertEquals("Sign in", errorView.getText());
 }
 
 @Test
 public void invaligEmailLoginTest()
 {
	 WebElement login = driver.findElementById("com.perm.kate:id/username");
	 WebElement passwod = driver.findElementById("com.perm.kate:id/password");
	 WebElement loginButton = driver.findElementById("com.perm.kate:id/login_btn");
	 WebDriverWait errorViewWait = new WebDriverWait(driver, 30);
	 login.clear();
	 login.sendKeys("lolololololololo");
	 passwod.clear();
	 passwod.sendKeys("asdad");
	 loginButton.click();
	 WebElement errorView = driver.findElementById("com.perm.kate:id/header_text");
	 Assert.assertEquals("Sign in", errorView.getText());
 }
 
 @Test
 public void InvalidPhoneNumberLoginTest()
 {
	 WebElement login = driver.findElementById("com.perm.kate:id/username");
	 WebElement passwod = driver.findElementById("com.perm.kate:id/password");
	 WebElement loginButton = driver.findElementById("com.perm.kate:id/login_btn");
	 WebDriverWait errorViewWait = new WebDriverWait(driver, 30);
	 login.clear();
	 login.sendKeys("----");
	 passwod.clear();
	 passwod.sendKeys("ljkgb zc");
	 loginButton.click();
	 WebElement errorView = driver.findElementById("com.perm.kate:id/header_text");
	 Assert.assertEquals("Sign in", errorView.getText());
 }
 
 @Test
 public void addWidgetTest()
 {
	 driver.pressKeyCode(AndroidKeyCode.HOME);
	 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	 TouchAction action = new TouchAction(driver);
	 action.longPress(700,2450, 200).release().perform();
	 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	action = new TouchAction(driver);
		
	action.longPress(150,2050, 2000).release().perform();
	WebDriverWait createButtonWait = new WebDriverWait(driver, 30);
	WebElement kateIcon = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Kate Mobile']"));
	
	Assert.assertEquals("Kate Mobile", kateIcon.getText());	
	
	
 }
}