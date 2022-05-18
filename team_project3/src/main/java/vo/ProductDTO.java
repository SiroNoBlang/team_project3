package vo;
/*
CREATE TABLE product(
product_title VARCHAR(20) ,
product_item_brand VARCHAR(10),
product_item_category_detail VARCHAR(20),
product_item_want_money INT 
);

* */
public class ProductDTO {
	private String product_title;
	private String product_item_brand;
	private String product_item_category_detail;
	private int product_item_want_money ;
	
	
	
	public ProductDTO() {}
	
	public ProductDTO(String product_title, String product_item_brand, String product_item_category_detail,
			int product_item_want_money) {
		this.product_title = product_title;
		this.product_item_brand = product_item_brand;
		this.product_item_category_detail = product_item_category_detail;
		this.product_item_want_money = product_item_want_money;
	}





	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getProduct_item_brand() {
		return product_item_brand;
	}

	public void setProduct_item_brand(String product_item_brand) {
		this.product_item_brand = product_item_brand;
	}

	public String getProduct_item_category_detail() {
		return product_item_category_detail;
	}

	public void setProduct_item_category_detail(String product_item_category_detail) {
		this.product_item_category_detail = product_item_category_detail;
	}

	public int getProduct_item_want_money() {
		return product_item_want_money;
	}

	public void setProduct_item_want_money(int product_item_want_money) {
		this.product_item_want_money = product_item_want_money;
	}

	@Override
	public String toString() {
		return "ProductDTO [product_title=" + product_title + ", product_item_brand=" + product_item_brand
				+ ", product_item_category_detail=" + product_item_category_detail + ", product_item_want_money="
				+ product_item_want_money + "]";
	}
	
	
	
	
}
