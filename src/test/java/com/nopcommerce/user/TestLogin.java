package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageUI.HomePageUI.Top_Menu_Xpaths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestLogin extends BaseTest {
	private WebDriver driver;
	private Accounts acc;
	private String invalidEmail, notExistEmail;

	private HomePageObject homePage;
	private LoginPageObject loginPage;

	@Parameters({"browser", "baseUrl"})
	@BeforeClass
	public void beforeClass(@Optional ("chrome") String browserName, @Optional ("https://demo.nopcommerce.com/") String baseUrl) {
		acc = Accounts.USER_ACCOUNT;
		invalidEmail = "afc@afc#$%";
		notExistEmail = "afc@mail.com";
		
		driver = getBrowserDriver(browserName, baseUrl);
		homePage = PageGeneratorManager.getHomepage(driver);

	}

	@Test
	public void Login_01_Empty_Data() {
		
		log.info("Login_01 - Step 01: Click Login link from Homepage");
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		
		log.info("Login_01 - Step 02: Click Login button without entering any information");
		loginPage.clickLoginButton();
		
		log.info("Login_01 - Step 03: Verify error \"Please enter your email\" displays");
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		
		log.info("Login_02 - Step 01: Click Login link from Homepage");
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		
		log.info("Login_02 - Step 02: Input email with invalid format");
		loginPage.inputEmail(invalidEmail);
		
		log.info("Login_02 - Step 03: Click Login button");
		loginPage.clickLoginButton();
		
		log.info("Login_02 - Step 04: Verify error \"Wrong email\" displays");
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Registered() {
		
		log.info("Login_03 - Step 01: Click Login link from Homepage");
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		
		log.info("Login_03 - Step 02: Input a valid email that doesn't exist in the system");
		loginPage.inputEmail(notExistEmail);
		
		log.info("Login_03 - Step 03: Click Login button");
		loginPage.clickLoginButton();
		
		log.info("Login_03 - Step 04: Verify error \"Login was unsuccessful. Please correct the errors and try again. No customer account found\" displays");
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void Login_04_Empty_Password() {
		
		log.info("Login_04 - Step 01: Click Login link from Homepage");
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		
		log.info("Login_04 - Step 02: Input a valid email and leave the password empty");
		loginPage.inputEmail(acc.getEmail());
		
		log.info("Login_04 - Step 03: Click Login button");
		loginPage.clickLoginButton();
		
		log.info("Login_04 - Step 04: Verify error \"Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect\" displays");
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		
		log.info("Login_05 - Step 01: Click Login link from Homepage");
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		
		log.info("Login_05 - Step 02: Input a valid email");
		loginPage.inputEmail(acc.getEmail());
		
		log.info("Login_05 - Step 03: Input wrong password");
		loginPage.inputPassword(invalidEmail);
		
		log.info("Login_05 - Step 04: Click Login button");
		loginPage.clickLoginButton();
		
		log.info("Login_05 - Step 05: Verify error \"Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect\" displays");
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		
		log.info("Login_06 - Step 01: Click Login link from Homepage");
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		
		log.info("Login_06 - Step 02: Input a valid email");
		loginPage.inputEmail(acc.getEmail());
		
		log.info("Login_06 - Step 03: Input valid password");
		loginPage.inputPassword(acc.getPassword());
		
		log.info("Login_06 - Step 04: Click Login button");
		homePage = loginPage.clickLoginButton();
		
		log.info("Login_06 - Step 05: Verify My Account page displays");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Login_06 - Step 06: Log out");
		homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGOUT_LINK);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
