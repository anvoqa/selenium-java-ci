package pageUI;

public class HomePageUI {
	
//	public static final String REGISTER_LINK = "//a[@class='ico-register']";
//	public static final String LOGIN_LINK = "//a[@class='ico-login']";
//	public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
//	public static final String MY_ACCOUNT_LINK = "//a[@class='ico-account']";
	
	public enum Top_Menu_Xpaths{
		REGISTER_LINK("ico-register"), 
		LOGIN_LINK("ico-login"), 
		LOGOUT_LINK("ico-logout"), 
		MY_ACCOUNT_LINK("ico-account");
		
		private final String className;
		
		Top_Menu_Xpaths(String className){
			this.className = className;
		}
		
		public String getClassName() {
			return this.className;
		}

		public String getXpath() {
			return String.format("//a[@class='%s']", className);
		}
		
		
	}
}
