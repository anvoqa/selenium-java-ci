package pageUI;


public class AdminCommonPageUI {
	public enum Admin_Left_Menu_Xpaths{
		DASHBOARD("Dashboard","/Admin"),
		CATALOG("Catalog", "#"), 
		PRODUCTS("Products", "/Admin/Product/List");
		
		private final String pageName;
		private final String href;
		
		Admin_Left_Menu_Xpaths(String pageName, String href){
			this.pageName = pageName;
			this.href = href;
		}
		
		public String getPageName() {
			return this.pageName;
		}
		
		public String getHref() {
			return this.href;
		}

		public String getPageNameXpath(String pageName, String href) {
			return String.format("//p[contains(text(), '%s')]//parent::a[@href='%s']", pageName, href);
		}
	}
	
	public static final String LOADING_ICON = "//div[@id='ajaxBusy']";
}
