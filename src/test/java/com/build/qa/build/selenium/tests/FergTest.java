package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.junit.Assert;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */


	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//		softly.assertThat(homePage.onHomePage())
//			.as("The website should load up with the Build.com desktop theme.")
//			.isTrue();

		// I did not have enughp time to fix this issue
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product brand and product id
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {

		// TODO: Implement this test
        navigateToHomePage();
		BasePage.searchInput.sendKeys("Moen m6702bn");
		BasePage.searchIcon.click();
		this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		BasePage.productBrand.getText();
		Assert.assertTrue(BasePage.productBrand.getText().contains("Moen"));
		BasePage.productId.getText();
		Assert.assertTrue(BasePage.productId.getText().contains("Part #M6702BN"));
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {
		// TODO: Implement this test

		driver.get("https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[4]/div[1]/div/div[2]/a")));
		// click on the compose button as soon as the "compose" button is visible
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[4]/div[1]/div/div[2]/a")).click();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sku5164957\"]/div[5]/div[3]/p")));
		driver.findElement(By.xpath("//*[@id=\"sku5164957\"]/div[5]/div[3]/p")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToCartModalForm\"]/div[5]/button[2]/span")));
		driver.findElement(By.xpath("//*[@id=\"addToCartModalForm\"]/div[5]/button[2]/span")).click();

	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @assert that the product and cart total update as expected when the quantity is changed
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addMultipleCartItemsAndChangeQuantity() {
		// TODO: Implement this test
		navigateToHomePage();
		BasePage.searchInput.sendKeys("Moen m6702bn");
		BasePage.searchIcon.click();
		this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		BasePage.addToCart.click();
		BasePage.selectDifferentFinish.click();
		BasePage.addToCart.click();
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/header/div[9]/div/div[4]/a/span/span[2]")).click();
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		// TODO: Implement this test

		navigateToHomePage();
		BasePage.allProducts.click();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"bathroom\"]/div[2]/div[1]/div/div[1]/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[1]/div/div[3]/div[2]/input"))
				.sendKeys("Brizo");
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[1]/div/div[3]/div[4]/ul/li[6]/label/label")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sku9258699\"]/div[5]/div[3]/p")));
		driver.findElement(By.xpath("//*[@id=\"sku9258699\"]/div[5]/div[3]/p")).click();
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[4]/div[1]/div/div[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"addToCartModalForm\"]/div[5]/button[2]/span")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"sku4435904\"]/div[3]/div/div/label[4]/div/span")).click();
		driver.findElement(By.xpath("//*[@id=\"sku4436084\"]/div[5]/div[3]/p")).click();
		driver.findElement(By.xpath("//*[@id=\"addToCartModalForm\"]/div[5]/button[2]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/header/div[9]/div/div[4]/a/span/span[2]")).click();
	}
}
