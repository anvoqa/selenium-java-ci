package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	private WebDriverWait explicitWait;
	private Select select;
	private JavascriptExecutor js;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * ************************************************************************
	 * ************************************************************************ 
	 * This section contains common methods that driver interacts with Browsers
	 * ************************************************************************
	 * ************************************************************************
	 */

	/**
	 * Go to a specified Url
	 * 
	 * @param driver
	 * @param url
	 */
	public void openUrl(String url) {
		driver.get(url);
	}

	/**
	 * Get current window title
	 * 
	 * @param driver
	 * @return String page title
	 */
	public String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * Get current url
	 * 
	 * @param driver
	 * @return String
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Go back to previous page
	 * 
	 * @param driver
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * Refresh the page
	 * 
	 * @param driver
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * Go forward
	 * 
	 * @param driver
	 */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * Accept browser alert
	 * 
	 * @param driver
	 */
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss the browser alert
	 * 
	 * @param driver
	 */
	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * Type a string to browser alert
	 * 
	 * @param driver
	 * @param value
	 */
	public void sendKeysToAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	/**
	 * Get a string from browser alert
	 * 
	 * @param driver
	 * @return String
	 */
	public String getTextInAlert() {
		return driver.switchTo().alert().getText();
	}

	/**
	 * Wait for browser alert presence
	 * 
	 * @param driver
	 */
	public void waitForAlertPresence() {
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Switch to another window by ID
	 * 
	 * @param driver
	 * @param parentID
	 */
	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
			}
		}
	}

	/**
	 * Switch to another window by window title
	 * 
	 * @param driver
	 * @param title
	 */
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	/**
	 * This method will close all windows except the parents. Then, it check if
	 * parent window still opens at the end
	 * 
	 * @param driver
	 * @param parentID The ID of parent window you want to keep open
	 */
	public boolean areAllWindowsClosedButParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}

		else {
			return false;
		}
	}

	/*
	 * ************************************************************************
	 * ************************************************************************ 
	 * This section contains common methods that driver interacts with web elements
	 * ************************************************************************
	 * ************************************************************************
	 */

	/**
	 * Capture element by xpath and return By object
	 * 
	 * @param xpath
	 * @return By
	 */
	public By byXpath(String xpath) {
		return By.xpath(xpath);
	}
	
	/**
	 * Capture element by xpath and return By object.
	 * xpath is dynamic depending on the dynamic value
	 * 
	 * @param xpath
	 * @return By
	 */
	public By byDynamicXpath(String xpath, String... dynamicValue) {
		return By.xpath(String.format(xpath, (Object[])dynamicValue));
	}
	
	/**
	 * Depends on the prefix of the element
	 * which is the locatorType, this method will return respective By locator
	 * This method is only used when there are many locator types than xpath
	 * The other functions need to be refactored as well if this method is being used
	 * 
	 * @param locatorType
	 * @return By
	 */
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		}  else if (locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		}  else if (locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		}  else if (locatorType.startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		}  else {
			throw new RuntimeException("Locator is not supported!");
		} 
		return by;
	}

	/**
	 * Capture element by xpath and return WebElement object
	 * 
	 * @param driver
	 * @param xpath
	 * @return WebElement
	 */
	public WebElement findElementByXpath(String xpath) {
		try {
			return driver.findElement(byXpath(xpath));
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found!");
			return null;
		}
	}
	/**
	 * Capture element by xpath and return WebElement object
	 * 
	 * @param driver
	 * @param xpath
	 * @return WebElement
	 */
	public WebElement findDynamicElementByXpath(String xpath, String... dynamicValue) {
		try {
			return driver.findElement(byDynamicXpath((xpath), dynamicValue));
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found!");
			return null;
		}
	}

	/**
	 * Capture web elements by xpath and return list of WebElement objects
	 * 
	 * @param driver
	 * @param xpath
	 * @return List<WebElement> A list of web elements having the same xpath
	 */
	public List<WebElement> findElementsByXpath(String xpath) {
		return driver.findElements(byXpath(xpath));
	}
	
	/**
	 * Click on a web element after waiting for it visible
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickElement(String xpath) {
		waitForDynamicElementVisible(xpath);
		findElementByXpath(xpath).click();
	}

	/**
	 * Click on a dynamic web element after waiting for it visible
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickDynamicElement(String xpath, String... dynamicValue ) {
		waitForDynamicElementVisible(xpath, dynamicValue);
		findDynamicElementByXpath(xpath, dynamicValue).click();
	}

	/**
	 * Input value to a textbox after clearing the old value
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysToElement(String xpath, String value) {
		waitForElementVisible(xpath);
		element = findElementByXpath(xpath);
		element.clear();
		element.sendKeys(value);
	}
	
	/**
	 * Input value to a dynamic textbox after clearing the old value
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysToDynamicElement(String xpath, String value, String... dynamicValue) {
		waitForDynamicElementVisible(xpath, dynamicValue);
		element = findDynamicElementByXpath(xpath, dynamicValue);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Get text from a web element
	 * 
	 * @param driver
	 * @param xpath
	 * @return String
	 */
	public String getText(String xpath) {
		return findElementByXpath(xpath).getText().trim();
	}
	
	/**
	 * Get text from a dynamic web element
	 * 
	 * @param driver
	 * @param xpath
	 * @return String
	 */
	public String getTextOfDynamicElement(String xpath, String... dynamicValue) {
		return findDynamicElementByXpath(xpath, dynamicValue).getText().trim();
	}

	/**
	 * Get attribute value of a web element
	 * 
	 * @param driver
	 * @param xpath
	 * @return String
	 */
	public String getAttribute(String xpath, String attribute) {
		return findElementByXpath(xpath).getAttribute(attribute);
	}

	/**
	 * Select an item from drop-down using Select library
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void selectValueInDropDown(String xpath, String value) {
		select = new Select(findElementByXpath(xpath));
		select.selectByVisibleText(value);
	}

	/**
	 * Get text of 1st selected item in a drop-down
	 * 
	 * @param driver
	 * @param xpath  xPath of the item in drop-down
	 * @return String Text of 1st selected item in dropdown
	 */
	public String getSelectItemInDropDown(String xpath) {
		select = new Select(findElementByXpath(xpath));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Select an item from a drop-down that the Select method does not support It
	 * will click on the drop-down, wait until items visible, scroll to the item and
	 * click on the item using js click
	 * 
	 * @param driver
	 * @param parentsXpath  xPath of the drop-down
	 * @param childrenXpath xPath of the items in the drop-down
	 */
	public void selectItemFromCustomDropdown(String parentsXpath, String childrenXpath,
			String selectedItem) {
		element = findElementByXpath(parentsXpath);
		js = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		js.executeScript("arguments[0].click();", element);
		waitInSeconds(1);
		elements = findElementsByXpath(childrenXpath);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childrenXpath)));
		for (WebElement childElement : elements) {
			if (childElement.getText().equals(selectedItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					waitInSeconds(1);
					js.executeScript("arguments[0].click();", element);
				}
				waitInSeconds(1);
				break;
			}
		}

	}

	/**
	 * Counts number of elements having the same xPath
	 * 
	 * @param driver
	 * @param xpath
	 * @return int Number of elements
	 */
	public int countElements(String xpath) {
		return findElementsByXpath(xpath).size();
	}

	/**
	 * Change status of a checkbox, check the current status before setting
	 * 
	 * @param driver
	 * @param xpath
	 * @param status true or false
	 */
	public void setCheckbox(String xpath, boolean status) {
		element = findElementByXpath(xpath);
		if (element.isSelected() != status)// status is expected status, if current != expected -> click to change
											// status
		{
			element.click();
		}
	}

	/**
	 * Check if a web element is displayed in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementDisplayed(String xpath) {
		try {
			return findElementByXpath(xpath).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed!");
			return false;
		}
	}

	/**
	 * Check if a web element is displayed in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementEnabled(String xpath) {
		return findElementByXpath(xpath).isEnabled();
	}

	/**
	 * Check if a web element is displayed & enabled in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed & enabled in DOM & UI
	 */
	public boolean isElementSelected(String xpath) {
		return findElementByXpath(xpath).isSelected();
	}

	/**
	 * Switch to iFrame by xpath
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void switchToFrameOrIframe(String xpath) {
		driver.switchTo().frame(findElementByXpath(xpath));
	}

	/**
	 * Switch to the main window from iFrame
	 * 
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * Move mouse to element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void hoverMouseToElement(String xpath) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(xpath)).perform();
	}

	/**
	 * Double click on element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void doubleClickOnElement(String xpath) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(xpath)).perform();
	}

	/**
	 * Right click on element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void rightClickOnElement(String xpath) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(xpath)).perform();
	}

	/**
	 * Send specific key from keyboard to element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeyboardToElement(String xpath, String value) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(xpath), value).perform();
	}

	/**
	 * Execute javascript using Javascript Executor
	 * 
	 * @param driver
	 * @param javaScript
	 * @return Object depends on the returned value from javascript
	 */
	public Object executeJavaScript(String javaScript) {
		js = (JavascriptExecutor) driver;
		return js.executeScript(javaScript);
	}

	/**
	 * Click on element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickElementByJS(String xpath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", findElementByXpath(xpath));
	}

	/**
	 * Scroll to element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void ScrollToElement(String xpath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(xpath));
	}

	/**
	 * Send keys to element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysByJS(String xpath, String value) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(xpath));
	}

	/**
	 * Remove an attribute of web element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param attributeName
	 */
	public void removeAttributeByJS(String xpath, String attributeName) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", findElementByXpath(xpath));
	}

	/**
	 * Verify text in inner text using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param expectedText
	 * @return boolean return true if text matches with expectedText
	 */
	public boolean verifyTextInInnerText(String xpath, String expectedText) {
		js = (JavascriptExecutor) driver;
		String actualText = (String) js
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return actualText.equals(expectedText);
	}

	/**
	 * Scroll to bottom page using Javascript Executor
	 * 
	 * @param driver
	 */
	public void scrollToBottomPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * Wait until element visible in UI, if not throw exception
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementVisible(String xpath) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpath)));
		} catch (ElementNotInteractableException e) {
			System.out.println("Element is not visible!");
		}

	}
	
	/**
	 * Wait until element visible in UI, if not throw exception
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForDynamicElementVisible(String xpath, String... dynamicValue) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byDynamicXpath(xpath, dynamicValue)));
		} catch (ElementNotInteractableException e) {
			System.out.println("Element is not visible!");
		}

	}

	/**
	 * Wait until element invisible in UI but still present in DOM
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementInvisible(String xpath) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpath)));
	}

	/**
	 * Wait until element clickable in the UI
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementClikable(String xpath) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpath)));
	}

	/**
	 * Wait until element present in DOM
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementPresence(String xpath) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(xpath)));
	}

	/**
	 * Hard wait in seconds
	 * 
	 * @param seconds
	 */
	public void waitInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/******************************************************************************************
	 * Functions using rest parameter
	 ******************************************************************************************/

	/**
	 * Parse rest parameters to a string
	 * 
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 */
	public String castToObject(String xpath, String... restParams) {
		return String.format(xpath, (Object[]) restParams);
	}

	/**
	 * Click on an element identified by rest parameters
	 * 
	 * @param driver
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 */
	public void clickElement(String xpath, String... restParams) {
		waitForElementVisible(castToObject(xpath, restParams));
		findElementByXpath(castToObject(xpath, restParams)).click();
	}

	/**
	 * Input value to a textbox after clearing the old value Use Rest Parameters in
	 * the element
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 * @param restParams parameters used to identify the element
	 */
	public void sendKeysToElement(String xpath, String value, String... restParams) {
		waitForElementVisible(castToObject(xpath, restParams));
		element = findElementByXpath(castToObject(xpath, restParams));
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Check if a web element identified by rest paramters is displayed in the DOM &
	 * UI
	 * 
	 * @param driver
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementDisplayed(String xpath, String... restParams) {
		try {
			return findElementByXpath(castToObject(xpath, restParams)).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed!");
			return false;
		}
	}
	
	/**
	 * Wait until element visible identified by rest parameters in UI, if not throw exception
	 * 
	 * @param driver
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 */
	public void waitForElementVisible(String xpath, String... restParams) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MAX_TIMEOUT));
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(xpath, restParams))));
		} catch (ElementNotInteractableException e) {
			System.out.println("Element is not visible!");
		}

	}
}
