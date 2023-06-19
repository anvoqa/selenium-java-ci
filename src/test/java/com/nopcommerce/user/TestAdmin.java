package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import objects.AdminProducts;
import pageObjects.AdminProductsPageObject;
import pageObjects.AdminDashboardPageObject;
import pageObjects.AdminHomePageObject;
import pageObjects.PageGeneratorManager;
import pageUI.AdminCommonPageUI.Admin_Left_Menu_Xpaths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestAdmin extends BaseTest {
	private WebDriver driver;
	private Accounts admin;
	private AdminProducts product;
	private AdminHomePageObject adminHomePage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminProductsPageObject adminProductsPage;


	@Parameters({"browser", "baseUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String baseUrl) {
		admin = Accounts.ADMIN;
		product = AdminProducts.LENOVO600;
		driver = getBrowserDriver(browserName, baseUrl);
		adminHomePage = PageGeneratorManager.getAdminHomePage(driver);
		
		log.info("Pre-condition: Admin logged in successfully");
		adminDashboardPage = adminHomePage.login(admin);
	}

	@Test
	public void Admin_01_Search_By_Product_Name() {
		
		log.info("Admin_01 - Step 01: Click Catalog link from left menu");
		adminDashboardPage.clickOnMenuSubmenu(Admin_Left_Menu_Xpaths.CATALOG);
		
		log.info("Admin_01 - Step 02: Click Products sub-menu");
		adminProductsPage = (AdminProductsPageObject) adminDashboardPage.clickOnMenuSubmenu(Admin_Left_Menu_Xpaths.PRODUCTS);
		
		log.info("Admin_01 - Step 03: Search product name: " + product.getProductName());
		adminProductsPage.expandSearchSection();
		adminProductsPage.inputProductName(product.getProductName());
		adminProductsPage.clickSearchButton();
		
		log.info("Admin_01 - Step 04: Verify the search result");
		Assert.assertEquals(adminProductsPage.getNumberOfSearchResults(), 1);
		Assert.assertEquals(adminProductsPage.getProductName(), product.getProductName());
		Assert.assertEquals(adminProductsPage.getSKU(), product.getSku());
		Assert.assertEquals(adminProductsPage.getPrice(), product.getPrice());
		Assert.assertEquals(adminProductsPage.getStockQuantity(), product.getStockQuantity());
		Assert.assertEquals(adminProductsPage.getPublishedStatus(), product.getpublishedStatus());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
