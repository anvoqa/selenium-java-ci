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
		
		log.info("Register_01 - Sep 01: Click Register link from Homepage");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		
		log.info("Register_01 - Sep 02: Click Register button without entering any information");
		registerPage.clickRegisterButton();
		
		log.info("Register_01 - Sep 03: Verify that error message \"<field name> is required.\" for each required field displays");
		Assert.assertEquals(registerPage.getFirstNameErrorMsg(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMsg(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMsg(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMsg(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		
		log.info("Register_02 - Sep 01: Click Register link from Homepage");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		
		log.info("Register_02 - Sep 02: input all required fields with invalid email format");
		acc.setEmail("123@456#%*");
		registerPage.registerAnAccount(acc);
		
		log.info("Register_02 - Sep 03: Verify that error message \"Wrong email\" displays");
		Assert.assertEquals(registerPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		
		log.info("Register_03 - Sep 01: Click Register link from Homepage");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		
		log.info("Register_03 - Sep 02: Register an account using valid information");
		acc.setEmail(Accounts.generateRandomEmail());
		log.info("Email: " + acc.getEmail());
		registerPage.registerAnAccount(acc);
		
		log.info("Register_03 - Sep 03: Verify that success message \"Your registration completed\" displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMsg(), "Your registration completed");
		homePage = (HomePageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);;
	}

	@Test
	public void Register_04_Email_Exist() {
		
		log.info("Register_04 - Sep 01: Click Register link from Homepage");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		
		log.info("Register_04 - Sep 02: Register an account using an email that already exist in the system");
		registerPage.registerAnAccount(acc);
		
		log.info("Register_04 - Sep 03: Verify that error message \"The specified email already exists\" displays");
		Assert.assertEquals(registerPage.getEmailAlreadyExistErrorMsg(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Then_6_Characters() {
		
		log.info("Register_05 - Sep 01: Click Register link from Homepage");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		
		log.info("Register_05 - Sep 02: Register an account using password that has less than 6 characters");
		acc.setPassword("12345");
		registerPage.registerAnAccount(acc);
		
		log.info("Register_05 - Sep 03: Verify that error message \"Password must meet the following rules:must have at least 6 characters\" displays");
		Assert.assertEquals(registerPage.getPasswordErrorMsg(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		
		log.info("Register_06 - Sep 01: Click Register link from Homepage");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		
		log.info("Register_06 - Sep 02: Register an account having confirm password doesn't match with password");
		acc.setPassword("123456");
		acc.setConfirmPassword("654123");
		registerPage.registerAnAccount(acc);
		
		log.info("Register_06 - Sep 03: Verify that error message \"The password and confirmation password do not match.\" displays");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(),
				"The password and confirmation password do not match.");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}


}
