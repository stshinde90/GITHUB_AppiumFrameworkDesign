package com.RegressionSuite;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.LandingPage;
import com.pages.ProductPage;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import test_Components.Ecommerce_BaseTest;

public class TC_01_Validate_Total_Amount extends Ecommerce_BaseTest {

	@Test
	public void validateHybridTest() throws MalformedURLException, InterruptedException {
		
		landingPage.enterNameField("genefer_Test");
		landingPage.selectRadioButton();
		landingPage.selectCountry("Argentina");
		ProductPage productPage = landingPage.click_btnShop();
		Thread.sleep(2000);
		productPage.click_addToCart();
		productPage.click_addToCart();
		CartPage cartPage = productPage.click_btnCart();
		Thread.sleep(2000);
		cartPage.validate_totalAmount_Cart();

	}
}
