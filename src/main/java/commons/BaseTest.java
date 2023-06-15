package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;


	protected WebDriver getBrowserDriver(String browserName, String baseUrl) {
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = WebDriverManager.chromedriver().create();
		} else if (browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		}		
		else if (browserName.equals("h_firefox")) {
//			Firefox 108 has not been supported yet
			driver = WebDriverManager.firefoxdriver().create();
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\browserDrivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		}
		else if (browserName.equals("firefox")) {
			//Firefox 108 has not been supported yet
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\browserDrivers\\geckodriver.exe");
			driver = WebDriverManager.firefoxdriver().create();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = WebDriverManager.edgedriver().create();
		} else {
			throw new RuntimeException("invalid browser name!");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(baseUrl);

		return driver;
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}

}
