package objects;


public enum AdminProducts {
	LENOVO600("", "Lenovo IdeaCentre 600 All-in-One PC", "LE_IC_600", "500", "10000", "true");
	
	private String pictureUrl;
	private String productName;
	private String sku;
	private String price;
	private String stockQuantity;
	private String publishedStatus;
	
	AdminProducts(String pictureUrl, String productName, String sku, String price, String stockQuantity, String publishedStatus){
		this.pictureUrl = pictureUrl;
		this.productName = productName;
		this.sku = sku;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.publishedStatus = publishedStatus;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(String stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getpublishedStatus() {
		return publishedStatus;
	}

	public void setPublished(String publishedStatus) {
		this.publishedStatus = publishedStatus;
	}
}
