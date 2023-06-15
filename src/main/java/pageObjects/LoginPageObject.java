package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUI.LoginPageUI;

public class LoginPageObject extends HomePageObject{
	
	public LoginPageObject(WebDriver driver) {
		super(driver);
	}

	public HomePageObject clickLoginButton() {
		waitForElementClikable(LoginPageUI.LOGIN_BUTTON);
		clickElement(LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomepage(driver);
		
	}

	public String getEmailErrorMsg() {
		waitForElementVisible(LoginPageUI.EMAIL_ERROR_MSG);
		return getText(LoginPageUI.EMAIL_ERROR_MSG);
	}

	public void inputEmail(String email) {
		waitForElementVisible(LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(LoginPageUI.EMAIL_TEXTBOX, email);
		
	}

	public String getUnsuccessfulLoginErrorMsg() {
		waitForElementVisible(LoginPageUI.UNSUCCESSFUL_LOGIN_ERROR_MSG);
		return getText(LoginPageUI.UNSUCCESSFUL_LOGIN_ERROR_MSG);
	}

	public void inputPassword(String password) {
		waitForElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	
	public HomePageObject login(String email, String password) {
		inputEmail(email);
		inputPassword(password);
		return clickLoginButton();
	}



}
