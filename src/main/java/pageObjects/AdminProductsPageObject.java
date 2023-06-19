package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUI.AdminCommonPageUI;
import pageUI.AdminProductsPageUI;
import pageUI.AdminProductsPageUI.ProductColumnHXpath;

public class AdminProductsPageObject extends AdminCommonPageObject{
	public AdminProductsPageObject(WebDriver driver) {
		super(driver);
	}

	public void expandSearchSection() {
		waitForElementClikable(AdminProductsPageUI.SEARCH_SECTION);
		if(!getAttribute(AdminProductsPageUI.SEARCH_SECTION, "class").contains("opened")) {
			clickElement(AdminProductsPageUI.SEARCH_SECTION);
		}
	}

	public void inputProductName(String productName) {
		waitForElementVisible(AdminProductsPageUI.PRODUCT_NAME_TEXTBOX);
		sendKeysToElement(AdminProductsPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickSearchButton() {
		waitForElementClikable(AdminProductsPageUI.SEARCH_BUTTON);
		clickElement(AdminProductsPageUI.SEARCH_BUTTON);
		waitForElementInvisible(AdminCommonPageUI.LOADING_ICON);
		
	}
	
	public int getNumberOfSearchResults() {
		return countElements(AdminProductsPageUI.SEARCH_RESUlT_ROWS);
	}
	
	public String getProductName() {
		return getText(ProductColumnHXpath.PRODUCT_NAME.getColumnXpath());
	}
	
	public String getSKU() {
		return getText(ProductColumnHXpath.SKU.getColumnXpath());
	}
	
	public String getPrice() {
		return getText(ProductColumnHXpath.PRICE.getColumnXpath());
	}

	public String getStockQuantity() {
		return getText(ProductColumnHXpath.STOCK_QUANTITY.getColumnXpath());
	}
	
	public String getPublishedStatus() {
		return getAttribute(ProductColumnHXpath.PUBLISHED.getColumnXpath() + "/i", "nop-value");
	}

}
