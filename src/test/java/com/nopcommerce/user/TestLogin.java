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
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(invalidEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Registered() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(notExistEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void Login_04_Empty_Password() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(acc.getEmail());
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(acc.getEmail());
		loginPage.inputPassword(invalidEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(acc.getEmail());
		loginPage.inputPassword(acc.getPassword());
		homePage = loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGOUT_LINK);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
