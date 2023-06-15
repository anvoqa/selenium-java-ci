package pageObjects;

import org.openqa.selenium.WebDriver;

import objects.Accounts;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends HomePageObject{
	
	public RegisterPageObject(WebDriver driver) {
		super(driver);
	}

	public void clickRegisterButton() {
		waitForElementClikable(RegisterPageUI.REGISTER_BUTTON);
		clickElement(RegisterPageUI.REGISTER_BUTTON);
		
	}

	public String getFirstNameErrorMsg() {
		waitForElementVisible(RegisterPageUI.FIRST_NAME_ERROR_MSG);
		return getText(RegisterPageUI.FIRST_NAME_ERROR_MSG);
	}

	public String getLastNameErrorMsg() {
		waitForElementVisible(RegisterPageUI.LAST_NAME_ERROR_MSG);
		return getText(RegisterPageUI.LAST_NAME_ERROR_MSG);
	}

	public String getEmailErrorMsg() {
		waitForElementVisible(RegisterPageUI.EMAIL_ERROR_MSG);
		return getText(RegisterPageUI.EMAIL_ERROR_MSG);
	}

	public String getPasswordErrorMsg() {
		waitForElementVisible(RegisterPageUI.PASSWORD_ERROR_MSG);
		return getText(RegisterPageUI.PASSWORD_ERROR_MSG);
	}

	public String getConfirmPasswordErrorMsg() {
		waitForElementVisible(RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
		return getText(RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
	}

	public void inputFirstName(String firstName) {
		waitForElementVisible(RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeysToElement(RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
		
	}

	public void inputLastName(String lastName) {
		waitForElementVisible(RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeysToElement(RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
		
	}

	public void inputEmail(String email) {
		waitForElementVisible(RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(RegisterPageUI.EMAIL_TEXTBOX, email);
		
	}

	public void inputPassword(String password) {
		waitForElementVisible(RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(RegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputConfirmPassword(String confirmPassword) {
		waitForElementVisible(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
		
	}

	public String getRegisterSuccessMsg() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MSG);
		return getText(RegisterPageUI.REGISTER_SUCCESS_MSG);
	}

	public String getEmailAlreadyExistErrorMsg() {
		waitForElementVisible(RegisterPageUI.EMAIL_ALREADY_EXIST_ERROR_MSG);
		return getText(RegisterPageUI.EMAIL_ALREADY_EXIST_ERROR_MSG);
	}
	
	public void registerAnAccount(Accounts acc) {
		inputFirstName(acc.getFirstName());
		inputLastName(acc.getLastName());
		inputEmail(acc.getEmail());
		inputPassword(acc.getPassword());
		inputConfirmPassword(acc.getConfirmPassword());  
		clickRegisterButton();
		
	}

}
