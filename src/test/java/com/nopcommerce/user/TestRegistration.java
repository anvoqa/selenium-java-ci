package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageUI.HomePageUI.Top_Menu_Xpaths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestRegistration extends BaseTest {
	private WebDriver driver;
	private Accounts acc;

	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters({"browser", "baseUrl"})
	@BeforeClass
	public void beforeClass(@Optional ("firefox") String browserName, @Optional ("https://demo.nopcommerce.com/") String baseUrl) {
		acc = Accounts.NEW_ACCOUNT;
		driver = getBrowserDriver(browserName, baseUrl);
		homePage = PageGeneratorManager.getHomepage(driver);

	}

	@Test
	public void Register_01_Empty_Data() {
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		registerPage.clickRegisterButton();
		Assert.assertEquals(registerPage.getFirstNameErrorMsg(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMsg(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMsg(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMsg(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		acc.setEmail("123@456#%*");
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		acc.setEmail(Accounts.generateRandomEmail());
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getRegisterSuccessMsg(), "Your registration completed");
		homePage = (HomePageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);;
	}

	@Test
	public void Register_04_Email_Exist() {
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getEmailAlreadyExistErrorMsg(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Then_6_Characters() {
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		acc.setPassword("12345");
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getPasswordErrorMsg(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		acc.setPassword("123456");
		acc.setConfirmPassword("654123");
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
