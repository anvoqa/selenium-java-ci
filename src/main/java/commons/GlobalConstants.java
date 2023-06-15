package commons;

import java.io.File;

public class GlobalConstants {
	
	public static final int MAX_TIMEOUT = 30;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILES = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILES = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;
	public static final String BROWSER_LOGS_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String TESTNG_SCREENSHOT_PATH = PROJECT_PATH + File.separator + "reportNGScreenshots" + File.separator;
	public static final String EXTENT_REPORT_V2_PATH = PROJECT_PATH + File.separator + "extentReportV2/ExtentReport.html";
	
	public static final String USER_HOME_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_HOME_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	 

}
