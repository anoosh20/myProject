package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.framework.BaseFramework;

public abstract class BasePage extends BaseFramework {
	
	public BasePage(WebDriver driver, Wait<WebDriver> wait) { 
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "search")
	public static WebElement searchInput;

	@FindBy(xpath = "//a[@class = 'fg-icon-search']")
	public static WebElement searchIcon;

	@FindBy(className = "product__brand")
	public static WebElement productBrand;

	@FindBy(xpath = "//p[@class='product__prop']/span")
	public static WebElement productId;

	@FindBy(xpath = "//*[@id=\"singleSkuForm\"]/div[2]/div[2]/input[9]")
	public static WebElement addToCart;

	@FindBy(xpath = "//img[@title=\"Matte Black\"]")
	public static WebElement selectDifferentFinish;

	@FindBy(xpath = "//div[@class=\"mega-nav-main-dropdown-toggle w-dropdown-toggle\"]")
	public static WebElement allProducts;

}
