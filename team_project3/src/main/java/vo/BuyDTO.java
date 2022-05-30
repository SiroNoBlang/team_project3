package vo;
/*
 
  CREATE TABLE buy (
	buy_member_code VARCHAR(32),
	buy_item_num INT,
	buy_price INT,
	buy_point INT,
	buy_sell_item_date VARCHAR(17),
	buy_item_status VARCHAR(4)
);
 
 * */


public class BuyDTO {

	private String buy_member_code;
	private int buy_item_num;
	private int buy_price;
	private int buy_point;
	private String buy_sell_item_date;
	private String buy_item_status;
	public String getBuy_member_code() {
		return buy_member_code;
	}
	public void setBuy_member_code(String buy_member_code) {
		this.buy_member_code = buy_member_code;
	}
	public int getBuy_item_num() {
		return buy_item_num;
	}
	public void setBuy_item_num(int buy_item_num) {
		this.buy_item_num = buy_item_num;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public int getBuy_point() {
		return buy_point;
	}
	public void setBuy_point(int buy_point) {
		this.buy_point = buy_point;
	}
	public String getBuy_sell_item_date() {
		return buy_sell_item_date;
	}
	public void setBuy_sell_item_date(String buy_sell_item_date) {
		this.buy_sell_item_date = buy_sell_item_date;
	}
	public String getBuy_item_status() {
		return buy_item_status;
	}
	public void setBuy_item_status(String buy_item_status) {
		this.buy_item_status = buy_item_status;
	}
	@Override
	public String toString() {
		return "BuyDTO [buy_member_code=" + buy_member_code + ", buy_item_num=" + buy_item_num + ", buy_price="
				+ buy_price + ", buy_point=" + buy_point + ", buy_sell_item_date=" + buy_sell_item_date
				+ ", buy_item_status=" + buy_item_status + "]";
	}

	
	
	
}
