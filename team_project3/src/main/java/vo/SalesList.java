package vo;

public class SalesList {
	private String month;
	private int sell_total_money;
	private int sell_fee;
	private int buy_total_money;
	private int buy_fee;
	private int net_income;
	
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
	public int getNet_income() {
		return net_income;
	}
	public void setNet_income(int net_income) {
		this.net_income = net_income;
	}
	@Override
	public String toString() {
		return "SalesList [month=" + month + ", sell_total_money=" + sell_total_money + ", sell_fee=" + sell_fee
				+ ", buy_total_money=" + buy_total_money + ", buy_fee=" + buy_fee + ", net_income=" + net_income + "]";
	}
	
	
	
}
