package pageUI;

public class RegisterPageUI {
	public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	
	public static final String FIRST_NAME_ERROR_MSG = "//span[@id='FirstName-error']";
	public static final String LAST_NAME_ERROR_MSG = "//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MSG = "//span[@id='Email-error']";
	public static final String PASSWORD_ERROR_MSG = "//span[@id='Password-error']";
	public static final String CONFIRM_PASSWORD_ERROR_MSG = "//span[@id='ConfirmPassword-error']";
	public static final String EMAIL_ALREADY_EXIST_ERROR_MSG = "//div[contains(@class,'message-error')]//li";
	
	public static final String REGISTER_SUCCESS_MSG = "//div[@class='result']";
	
	public static final String LOG_OUT_LINK = "//a[@class='ico-logout']";
}
