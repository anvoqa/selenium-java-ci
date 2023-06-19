package pageUI;

public class AdminProductsPageUI {
	public static final String SEARCH_SECTION = "//div[contains(@class,'search-row')]";
	public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
	public static final String SEARCH_BUTTON = "//button[@id='search-products']";
	public static final String SEARCH_RESUlT_ROWS = "//div[@class='dataTables_scrollBody']//tbody/tr";
	
	public enum ProductColumnHXpath{
		CHECKBOX("", "1"),
		PICTURE("", "2"),
		PRODUCT_NAME("Product name", "3"),
		SKU("SKU", "4"),
		PRICE("Price", "5"),
		STOCK_QUANTITY("Stock quantify", "6"),
		PUBLISHED("Published", "7"),
		EDIT("Edit", "8");
		
		private String columnHeader;
		private String columnIndex;
		
		ProductColumnHXpath(String columnHeader, String columnIndex){
			this.columnHeader = columnHeader;
			this.columnIndex = columnIndex;
		}
		
		public String getColumnHeader() {
			return this.columnHeader;
		}
		
		public String getColumnIndex() {
			return this.columnIndex;
		}
		
		public String getColumnHeaderXpath() {
			return String.format("//div[@class='dataTables_scrollHead']//th[text()='%s']", this.columnHeader);
		}
		
		public String getColumnXpath() {
			return String.format("//div[@class='dataTables_scrollBody']//tr/td[%s]", this.columnIndex);
		}
	}

}
