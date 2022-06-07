package vo;
//CREATE TABLE address (
//	   	 member_code VARCHAR(32),
//	    	 post_code VARCHAR(20),
//	   	 address_code VARCHAR(150),
//	   	 address_detail VARCHAR(100),
//		address_name VARCHAR(30),
//		address_phone VARCHAR(11)
//	);

public class SellerAddress {
	private String member_code;
	private String post_code;
	private String address_code;
	private String address_detail;
	private String address_name;
	private String address_phone;
	private String address_date;
	
	public SellerAddress() {}

	public SellerAddress(String member_code, String post_code, String address_code, String address_detail,
			String address_name, String address_phone, String address_date) {
		super();
		this.member_code = member_code;
		this.post_code = post_code;
		this.address_code = address_code;
		this.address_detail = address_detail;
		this.address_name = address_name;
		this.address_phone = address_phone;
		this.address_date = address_date;
	}

	public String getMember_code() {
		return member_code;
	}

	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getAddress_code() {
		return address_code;
	}

	public void setAddress_code(String address_code) {
		this.address_code = address_code;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getAddress_phone() {
		return address_phone;
	}

	public void setAddress_phone(String address_phone) {
		this.address_phone = address_phone;
	}

	public String getAddress_date() {
		return address_date;
	}

	public void setAddress_date(String address_date) {
		this.address_date = address_date;
	}

	@Override
	public String toString() {
		return "SellerAddress [member_code=" + member_code + ", post_code=" + post_code + ", address_code="
				+ address_code + ", address_detail=" + address_detail + ", address_name=" + address_name
				+ ", address_phone=" + address_phone + ", address_date=" + address_date + "]";
	}
	
	
	
	
}
