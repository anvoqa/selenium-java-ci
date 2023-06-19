package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.AdminCommonPageUI.Admin_Left_Menu_Xpaths;

public class AdminCommonPageObject extends BasePage{
	public AdminCommonPageObject(WebDriver driver) {
		super(driver);
	}
	
	public AdminCommonPageObject clickOnMenuSubmenu(Admin_Left_Menu_Xpaths menuName) {
		waitForElementClikable(menuName.getPageNameXpath(menuName.getPageName(), menuName.getHref()));
		clickElement(menuName.getPageNameXpath(menuName.getPageName(), menuName.getHref()));
		switch (menuName) {
		case PRODUCTS:
			return PageGeneratorManager.getAdminProductsPage(driver);
		case DASHBOARD:
			return PageGeneratorManager.getAdminDashboardPage(driver);
		default:
			return PageGeneratorManager.getAdminCommonPage(driver);
		}
	}
}
