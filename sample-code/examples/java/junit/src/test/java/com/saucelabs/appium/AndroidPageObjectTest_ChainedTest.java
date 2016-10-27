package com.saucelabs.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.saucelabs.appium.page_object.android.ApiDemosListViewScreenByAllPossible;
import com.saucelabs.appium.page_object.android.ApiDemosListViewScreenChaided;
import com.saucelabs.appium.page_object.android.ApiDemosListViewScreenSimple;
import com.saucelabs.appium.page_object.ios.TestAppScreenSimple;

import com.xamarin.testcloud.appium.Factory;
import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;


/**
 * Please read about Page Object design pattern here:
 *  https://code.google.com/p/selenium/wiki/PageObjects
 *  
 *  Please look at:
 *  {@link ApiDemosListViewScreenSimple}
 *  {@link ApiDemosListViewScreenChaided}
 *  {@link ApiDemosListViewScreenByAllPossible}
 *  {@link TestAppScreenSimple}
 *
 */
public class AndroidPageObjectTest_ChainedTest {
	@Rule
	public TestWatcher watcher = Factory.createWatcher();

	private EnhancedAndroidDriver driver;

	private ApiDemosListViewScreenChaided apiDemosPageObject;
	
	@Before
	public void setUp() throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
	    File app = new File(appDir, "ApiDemos-debug.apk");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    driver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
	    apiDemosPageObject = new ApiDemosListViewScreenChaided();
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), 
				apiDemosPageObject);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.label("Stopping app");
		driver.quit();
	}
	
	/**
	 * Page Object best practice is to describe interactions with target 
	 * elements by methods. These methods describe business logic of the page/screen.
	 * Here test interacts with lazy instantiated elements directly.
	 * It was done so just for obviousness
	 */
	
	@Test
	public void androidChainSearchElementsTest(){
		Assert.assertNotEquals(0, apiDemosPageObject.chainElementViews.size());
	}

	@Test
	public void androidChainSearchElementTest(){
		Assert.assertNotEquals(null, apiDemosPageObject.chainElementView.getAttribute("text"));
	}

	@Test
	public void androidOrIOSFindByElementsTest_ChainSearches(){
		Assert.assertNotEquals(0, apiDemosPageObject.chainAndroidOrIOSUIAutomatorViews.size());
	}

	@Test
	public void androidOrIOSFindByElementTest_ChainSearches(){
		Assert.assertNotEquals(null, apiDemosPageObject.chainAndroidOrIOSUIAutomatorView.getAttribute("text"));
	}
	
	@Test
	public void isAndroidElementTest(){
		Assert.assertNotEquals(null, apiDemosPageObject.androidElementView.getAttribute("text"));
	}
	
	@Test
	public void areAndroidElementsTest(){
		Assert.assertNotEquals(0, apiDemosPageObject.androidElementViews.size());
	}
}
