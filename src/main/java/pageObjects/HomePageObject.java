package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI.Top_Menu_Xpaths;

public class HomePageObject extends BasePage{
	
	public  HomePageObject(WebDriver driver) {
		super(driver);
	}
	
	public HomePageObject clickTopMenuLink(Top_Menu_Xpaths link) {
		waitForElementClikable(link.getXpath());
		clickElement(link.getXpath());
		switch(link) {
		case LOGIN_LINK:
			return PageGeneratorManager.getLoginPage(driver);
		case LOGOUT_LINK:
			return PageGeneratorManager.getHomepage(driver);
		case REGISTER_LINK:
			return PageGeneratorManager.getRegisterPage(driver);
			default:
				throw new RuntimeException("Invalid link");
		}
		
	}

	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(Top_Menu_Xpaths.MY_ACCOUNT_LINK.getXpath());
	}

	
	

}
