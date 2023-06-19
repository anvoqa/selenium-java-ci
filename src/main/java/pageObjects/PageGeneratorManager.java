package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomepage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static AdminHomePageObject getAdminHomePage(WebDriver driver) {
		return new AdminHomePageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminCommonPageObject getAdminCommonPage(WebDriver driver) {
		return new AdminCommonPageObject(driver);
	}
	
	public static AdminProductsPageObject getAdminProductsPage(WebDriver driver) {
		return new AdminProductsPageObject(driver);
	}
	

}
