package vo;

import java.sql.Date;

/*(판매자 DTO)
 * CREATE TABLE sell (
	sell_num INT Auto_Increment PRIMARY KEY,
	sell_member_code BINARY(16),
	sell_title VARCHAR(50),
	sell_category VARCHAR(10),
	sell_category_detail VARCHAR(10),
	sell_content VARCHAR(2000),
	sell_price INT,
	sell_color VARCHAR(20),
	sell_size VARCHAR(5),
	sell_brand VARCHAR(30),
	sell_write_date DATE,
	sell_readcount INT
);

CREATE TABLE sell_list (
	sell_list_num INT,
	sell_list_item_status VARCHAR(4) CHECK (sell_list_item_status  IN ('검수중', '판매중', '거래중', '판매완료')),
	sell_list_approve_date DATETIME,
	sell_list_approve_nickname VARCHAR(16),
	FOREIGN KEY(sell_list_num) REFERENCES sell(sell_num)
);

CREATE VIEW recent_sell_num AS (
	SELECT MAX(SELL_NUM) FROM sell
);

CREATE TABLE sell_img (
	sell_img_num INT  REFERENCES sell(sell_num),
	sell_img_num_detail INT,
	sell_img_name VARCHAR(100),
	sell_img_real_name VARCHAR(100),
	PRIMARY KEY(sell_img_num, sell_img_num_detail)
);


 * */
public class SellerDTO {
	private int sell_num;
	private String sell_member_code;
	private String sell_title;
	private String sell_category;
	private String sell_category_detail;
	private String sell_content;
	private int sell_price;
	private String sell_color;
	private String sell_size;
	private String sell_brand;
	private String sell_write_date;
	private int sell_readcount; 
	private int sell_list_num;
	private String sell_list_item_status;
	private String sell_list_approve_date;
	private String sell_list_approve_nickname;

	

	public SellerDTO() {}

	

	public SellerDTO(int sell_num, String sell_member_code, String sell_title, String sell_category,
			String sell_category_detail, String sell_content, int sell_price, String sell_color, String sell_size,
			String sell_brand, String sell_write_date, int sell_readcount, int sell_list_num,
			String sell_list_item_status, String sell_list_approve_date, String sell_list_approve_nickname) {
		super();
		this.sell_num = sell_num;
		this.sell_member_code = sell_member_code;
		this.sell_title = sell_title;
		this.sell_category = sell_category;
		this.sell_category_detail = sell_category_detail;
		this.sell_content = sell_content;
		this.sell_price = sell_price;
		this.sell_color = sell_color;
		this.sell_size = sell_size;
		this.sell_brand = sell_brand;
		this.sell_write_date = sell_write_date;
		this.sell_readcount = sell_readcount;
		this.sell_list_num = sell_list_num;
		this.sell_list_item_status = sell_list_item_status;
		this.sell_list_approve_date = sell_list_approve_date;
		this.sell_list_approve_nickname = sell_list_approve_nickname;
	}



	public int getSell_num() {
		return sell_num;
	}



	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}



	public String getSell_member_code() {
		return sell_member_code;
	}



	public void setSell_member_code(String sell_member_code) {
		this.sell_member_code = sell_member_code;
	}



	public String getSell_title() {
		return sell_title;
	}



	public void setSell_title(String sell_title) {
		this.sell_title = sell_title;
	}



	public String getSell_category() {
		return sell_category;
	}



	public void setSell_category(String sell_category) {
		this.sell_category = sell_category;
	}



	public String getSell_category_detail() {
		return sell_category_detail;
	}



	public void setSell_category_detail(String sell_category_detail) {
		this.sell_category_detail = sell_category_detail;
	}



	public String getSell_content() {
		return sell_content;
	}



	public void setSell_content(String sell_content) {
		this.sell_content = sell_content;
	}



	public int getSell_price() {
		return sell_price;
	}



	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}



	public String getSell_color() {
		return sell_color;
	}



	public void setSell_color(String sell_color) {
		this.sell_color = sell_color;
	}



	public String getSell_size() {
		return sell_size;
	}



	public void setSell_size(String sell_size) {
		this.sell_size = sell_size;
	}



	public String getSell_brand() {
		return sell_brand;
	}



	public void setSell_brand(String sell_brand) {
		this.sell_brand = sell_brand;
	}



	public String getSell_write_date() {
		return sell_write_date;
	}



	public void setSell_write_date(String sell_write_date) {
		this.sell_write_date = sell_write_date;
	}



	public int getSell_readcount() {
		return sell_readcount;
	}



	public void setSell_readcount(int sell_readcount) {
		this.sell_readcount = sell_readcount;
	}



	public int getSell_list_num() {
		return sell_list_num;
	}



	public void setSell_list_num(int sell_list_num) {
		this.sell_list_num = sell_list_num;
	}



	public String getSell_list_item_status() {
		return sell_list_item_status;
	}



	public void setSell_list_item_status(String sell_list_item_status) {
		this.sell_list_item_status = sell_list_item_status;
	}



	public String getSell_list_approve_date() {
		return sell_list_approve_date;
	}



	public void setSell_list_approve_date(String sell_list_approve_date) {
		this.sell_list_approve_date = sell_list_approve_date;
	}



	public String getSell_list_approve_nickname() {
		return sell_list_approve_nickname;
	}



	public void setSell_list_approve_nickname(String sell_list_approve_nickname) {
		this.sell_list_approve_nickname = sell_list_approve_nickname;
	}



	@Override
	public String toString() {
		return "SellerDTO [sell_num=" + sell_num + ", sell_member_code=" + sell_member_code + ", sell_title="
				+ sell_title + ", sell_category=" + sell_category + ", sell_category_detail=" + sell_category_detail
				+ ", sell_content=" + sell_content + ", sell_price=" + sell_price + ", sell_color=" + sell_color
				+ ", sell_size=" + sell_size + ", sell_brand=" + sell_brand + ", sell_write_date=" + sell_write_date
				+ ", sell_readcount=" + sell_readcount + ", sell_list_num=" + sell_list_num + ", sell_list_item_status="
				+ sell_list_item_status + ", sell_list_approve_date=" + sell_list_approve_date
				+ ", sell_list_approve_nickname=" + sell_list_approve_nickname + "]";
	}


	


	

	
	
	
	
}
