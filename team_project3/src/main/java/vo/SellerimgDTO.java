package vo;

public class SellerimgDTO {
	private int sell_img_num;
//	private int sell_img_num_detail;
	private String sell_img_name;
	private String sell_img_real_name;
	
	public SellerimgDTO() {}
	
	
	public SellerimgDTO(int sell_img_num, String sell_img_name, String sell_img_real_name) {
		super();
		this.sell_img_num = sell_img_num;
//		this.sell_img_num_detail = sell_img_num_detail;
		this.sell_img_name = sell_img_name;
		this.sell_img_real_name = sell_img_real_name;
	}




	public int getSell_img_num() {
		return sell_img_num;
	}
	public void setSell_img_num(int sell_img_num) {
		this.sell_img_num = sell_img_num;
	}
//	public int getSell_img_num_detail() {
//		return sell_img_num_detail;
//	}
//	public void setSell_img_num_detail(int sell_img_num_detail) {
//		this.sell_img_num_detail = sell_img_num_detail;
//	}
	public String getSell_img_name() {
		return sell_img_name;
	}
	public void setSell_img_name(String sell_img_name) {
		this.sell_img_name = sell_img_name;
	}
	public String getSell_img_real_name() {
		return sell_img_real_name;
	}
	public void setSell_img_real_name(String sell_img_real_name) {
		this.sell_img_real_name = sell_img_real_name;
	}
	@Override
	public String toString() {
		return "SellerimgDTO [sell_img_num=" + sell_img_num + //", sell_img_num_detail=" + sell_img_num_detail
				 ", sell_img_name=" + sell_img_name + ", sell_img_real_name=" + sell_img_real_name + "]";
	}
	
	

}
