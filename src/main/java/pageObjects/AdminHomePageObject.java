package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import objects.Accounts;
import pageUI.AdminHomePageUI;

public class AdminHomePageObject extends BasePage{
	public AdminHomePageObject(WebDriver driver) {
		super(driver);
	}

	public AdminDashboardPageObject login(Accounts adminAcc) {
		waitForElementVisible(AdminHomePageUI.EMAIL_TEXTBOX);
		sendKeysToElement(AdminHomePageUI.EMAIL_TEXTBOX, adminAcc.getEmail());
		sendKeysToElement(AdminHomePageUI.PASSWORD_TEXTBOX, adminAcc.getPassword());
		clickElement(AdminHomePageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
}
