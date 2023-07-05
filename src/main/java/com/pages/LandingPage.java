package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.utils.ReusableMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LandingPage extends ReusableMethods {
	
    AndroidDriver driver;
	
	public LandingPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement txtbx_nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement rdo_GenderSelection;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement drpdn_btn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement btn_LetsShop;
	
	public void enterNameField(String userName)
	{
		txtbx_nameField.sendKeys(userName);
		driver.hideKeyboard();
	}
	
	public void selectRadioButton()
	{
		rdo_GenderSelection.click();
	}
	
	public void selectCountry(String CountryName)
	{
		drpdn_btn.click();
		ScrollIntoViewByText(CountryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+CountryName+"']")).click();	
	}
	
	public ProductPage click_btnShop()
	{
		btn_LetsShop.click();
		return new ProductPage(driver);
	}

}
