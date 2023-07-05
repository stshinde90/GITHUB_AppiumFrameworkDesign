package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.utils.ReusableMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends ReusableMethods {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement txt_titleBar;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement total_value_ele;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> ind_product_value;

	public void validate_totalAmount_Cart() {

		wait_Until_AttributeContains(txt_titleBar);

		// Total value validation
		String finalValue = total_value_ele.getText().substring(1);
		double finalValue_inDouble = Double.parseDouble(finalValue);
		System.out.println(finalValue_inDouble);
		double total_sum = 0;
		int count = ind_product_value.size();
		for (int i = 0; i < count; i++) {
			String Value = ind_product_value.get(i).getText().substring(1);
			double total_sum_value = Double.parseDouble(Value);
			total_sum = total_sum + total_sum_value;
		}
		Assert.assertEquals(total_sum, finalValue_inDouble);

	}

}
