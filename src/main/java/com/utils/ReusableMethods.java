package com.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ReusableMethods {
	
	public AndroidDriver driver;
	
	public ReusableMethods(AndroidDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement rdo_GenderSelection;
	
	public void ScrollIntoViewByText(String textToFind)
	{
       driver.findElement( AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+textToFind+"\"));" ));
	}
	
	
	public void wait_Until_AttributeContains(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 		wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
	}
	
	public void SwipeFunction(WebElement element , String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
	}
	
	public void longPress(WebElement longPressEle)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of( "elementId", ((RemoteWebElement) longPressEle).getId(),
						"duration", 2500
			));
	}
	public void scrollintoView(String textView)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+textView+"\"));"));	
	}
	
	

}
