package vo;

public class SalesList {
	private String month;
	private int sell_total_money;
	private int sell_fee;
	private int buy_total_money;
	private int buy_fee;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getSell_total_money() {
		return sell_total_money;
	}
	public void setSell_total_money(int sell_total_money) {
		this.sell_total_money = sell_total_money;
	}
	public int getSell_fee() {
		return sell_fee;
	}
	public void setSell_fee(int sell_fee) {
		this.sell_fee = sell_fee;
	}
	public int getBuy_total_money() {
		return buy_total_money;
	}
	public void setBuy_total_money(int buy_total_money) {
		this.buy_total_money = buy_total_money;
	}
	public int getBuy_fee() {
		return buy_fee;
	}
	public void setBuy_fee(int buy_fee) {
		this.buy_fee = buy_fee;
	}
	
}
