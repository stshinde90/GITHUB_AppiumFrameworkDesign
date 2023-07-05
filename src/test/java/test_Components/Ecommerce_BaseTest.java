package test_Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;
import com.pages.LandingPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Ecommerce_BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public LandingPage landingPage;
	public Properties prop;
	public FileInputStream fis;  
	
	@BeforeTest
	public void initialSetUP() throws IOException
	{
		//=========== Global Property initialization ============
		prop = new Properties();
	    fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//globalData.properties");
		prop.load(fis);
		
		//========== Code to start Appium Server ==============
		String IPAdress = System.getProperty("IPAdress") != null ? prop.getProperty("IPAdress") : System.getProperty("IPAdress");
		
	    service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\jshinds4\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(IPAdress).usingPort(Integer.parseInt(prop.getProperty("PortNo"))).build();
		service.start();
		//==================
		
		//========== Configuring Emulator ===========
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("Santosh_Emulator");
		options.setChromedriverExecutable(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\chromedriver.exe");
		//options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\General-Store.apk");
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		landingPage = new  LandingPage(driver);
	}
	
	public String getScreenshot(String testCaseName, AndroidDriver driver) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File dstFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + "//.png");
		FileUtils.copyFile(srcFile, dstFile);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + "//.png";
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		service.stop(); // stop server
	}

}
