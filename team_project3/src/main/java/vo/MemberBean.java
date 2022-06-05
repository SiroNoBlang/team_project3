package vo;


public class MemberBean {
	private String grade_name;
	private int lowest_acc_money;
	private int highest_acc_money;
	private String reason_num;
	private String reason_content;
	private String member_num;
	private String member_code;
	private String member_nickname;
	private String member_id;
	private String member_passwd;
	private String member_email;
	private String member_info_name;
	private String member_info_gender;
	private String member_info_phone;
	private String member_info_age;
	private String member_info_post_code;
	private String member_info_address;
	private String member_info_address_detail;
	private String member_info_ship_post_code;
	private String member_info_ship_address;
	private String member_info_ship_address_detail;
	private String member_info_mypage_img_name;
	private String member_info_mypage_real_img_name;
	private String member_info_detail_like_style;
	private String member_info_detail_like_brand;
	private String member_info_detail_like_category;
	private int member_info_detail_point;
	private int member_info_detail_acc_money;
	private String member_service_log_status;
	private String member_service_log_join_date;
	private String member_service_log_passwd_change_date;
	private String member_service_log_login_date;
	private int member_service_log_order_count;
	private int top_level;
	private int nomal;
	private int suspension;
	private int withdrawal;
	
	public MemberBean() {}

	public MemberBean(String grade_name, int lowest_acc_money, int highest_acc_money, String reason_num,
			String reason_content, String member_num, String member_code, String member_nickname, String member_id,
			String member_passwd, String member_email, String member_info_name, String member_info_gender,
			String member_info_phone, String member_info_age, String member_info_post_code, String member_info_address,
			String member_info_address_detail, String member_info_ship_post_code, String member_info_ship_address,
			String member_info_ship_address_detail, String member_info_mypage_img_name,
			String member_info_mypage_real_img_name, String member_info_detail_like_style,
			String member_info_detail_like_brand, String member_info_detail_like_category, int member_info_detail_point,
			int member_info_detail_acc_money, String member_service_log_status, String member_service_log_join_date,
			String member_service_log_passwd_change_date, String member_service_log_login_date,
			int member_service_log_order_count, int top_level, int nomal, int suspension, int withdrawal) {
		super();
		this.grade_name = grade_name;
		this.lowest_acc_money = lowest_acc_money;
		this.highest_acc_money = highest_acc_money;
		this.reason_num = reason_num;
		this.reason_content = reason_content;
		this.member_num = member_num;
		this.member_code = member_code;
		this.member_nickname = member_nickname;
		this.member_id = member_id;
		this.member_passwd = member_passwd;
		this.member_email = member_email;
		this.member_info_name = member_info_name;
		this.member_info_gender = member_info_gender;
		this.member_info_phone = member_info_phone;
		this.member_info_age = member_info_age;
		this.member_info_post_code = member_info_post_code;
		this.member_info_address = member_info_address;
		this.member_info_address_detail = member_info_address_detail;
		this.member_info_ship_post_code = member_info_ship_post_code;
		this.member_info_ship_address = member_info_ship_address;
		this.member_info_ship_address_detail = member_info_ship_address_detail;
		this.member_info_mypage_img_name = member_info_mypage_img_name;
		this.member_info_mypage_real_img_name = member_info_mypage_real_img_name;
		this.member_info_detail_like_style = member_info_detail_like_style;
		this.member_info_detail_like_brand = member_info_detail_like_brand;
		this.member_info_detail_like_category = member_info_detail_like_category;
		this.member_info_detail_point = member_info_detail_point;
		this.member_info_detail_acc_money = member_info_detail_acc_money;
		this.member_service_log_status = member_service_log_status;
		this.member_service_log_join_date = member_service_log_join_date;
		this.member_service_log_passwd_change_date = member_service_log_passwd_change_date;
		this.member_service_log_login_date = member_service_log_login_date;
		this.member_service_log_order_count = member_service_log_order_count;
		this.top_level = top_level;
		this.nomal = nomal;
		this.suspension = suspension;
		this.withdrawal = withdrawal;
	}

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public int getLowest_acc_money() {
		return lowest_acc_money;
	}

	public void setLowest_acc_money(int lowest_acc_money) {
		this.lowest_acc_money = lowest_acc_money;
	}

	public int getHighest_acc_money() {
		return highest_acc_money;
	}

	public void setHighest_acc_money(int highest_acc_money) {
		this.highest_acc_money = highest_acc_money;
	}

	public String getReason_num() {
		return reason_num;
	}

	public void setReason_num(String reason_num) {
		this.reason_num = reason_num;
	}

	public String getReason_content() {
		return reason_content;
	}

	public void setReason_content(String reason_content) {
		this.reason_content = reason_content;
	}

	public String getMember_num() {
		return member_num;
	}

	public void setMember_num(String member_num) {
		this.member_num = member_num;
	}

	public String getMember_code() {
		return member_code;
	}

	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_passwd() {
		return member_passwd;
	}

	public void setMember_passwd(String member_passwd) {
		this.member_passwd = member_passwd;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_info_name() {
		return member_info_name;
	}

	public void setMember_info_name(String member_info_name) {
		this.member_info_name = member_info_name;
	}

	public String getMember_info_gender() {
		return member_info_gender;
	}

	public void setMember_info_gender(String member_info_gender) {
		this.member_info_gender = member_info_gender;
	}

	public String getMember_info_phone() {
		return member_info_phone;
	}

	public void setMember_info_phone(String member_info_phone) {
		this.member_info_phone = member_info_phone;
	}

	public String getMember_info_age() {
		return member_info_age;
	}

	public void setMember_info_age(String member_info_age) {
		this.member_info_age = member_info_age;
	}

	public String getMember_info_post_code() {
		return member_info_post_code;
	}

	public void setMember_info_post_code(String member_info_post_code) {
		this.member_info_post_code = member_info_post_code;
	}

	public String getMember_info_address() {
		return member_info_address;
	}

	public void setMember_info_address(String member_info_address) {
		this.member_info_address = member_info_address;
	}

	public String getMember_info_address_detail() {
		return member_info_address_detail;
	}

	public void setMember_info_address_detail(String member_info_address_detail) {
		this.member_info_address_detail = member_info_address_detail;
	}

	public String getMember_info_ship_post_code() {
		return member_info_ship_post_code;
	}

	public void setMember_info_ship_post_code(String member_info_ship_post_code) {
		this.member_info_ship_post_code = member_info_ship_post_code;
	}

	public String getMember_info_ship_address() {
		return member_info_ship_address;
	}

	public void setMember_info_ship_address(String member_info_ship_address) {
		this.member_info_ship_address = member_info_ship_address;
	}

	public String getMember_info_ship_address_detail() {
		return member_info_ship_address_detail;
	}

	public void setMember_info_ship_address_detail(String member_info_ship_address_detail) {
		this.member_info_ship_address_detail = member_info_ship_address_detail;
	}

	public String getMember_info_mypage_img_name() {
		return member_info_mypage_img_name;
	}

	public void setMember_info_mypage_img_name(String member_info_mypage_img_name) {
		this.member_info_mypage_img_name = member_info_mypage_img_name;
	}

	public String getMember_info_mypage_real_img_name() {
		return member_info_mypage_real_img_name;
	}

	public void setMember_info_mypage_real_img_name(String member_info_mypage_real_img_name) {
		this.member_info_mypage_real_img_name = member_info_mypage_real_img_name;
	}

	public String getMember_info_detail_like_style() {
		return member_info_detail_like_style;
	}

	public void setMember_info_detail_like_style(String member_info_detail_like_style) {
		this.member_info_detail_like_style = member_info_detail_like_style;
	}

	public String getMember_info_detail_like_brand() {
		return member_info_detail_like_brand;
	}

	public void setMember_info_detail_like_brand(String member_info_detail_like_brand) {
		this.member_info_detail_like_brand = member_info_detail_like_brand;
	}

	public String getMember_info_detail_like_category() {
		return member_info_detail_like_category;
	}

	public void setMember_info_detail_like_category(String member_info_detail_like_category) {
		this.member_info_detail_like_category = member_info_detail_like_category;
	}

	public int getMember_info_detail_point() {
		return member_info_detail_point;
	}

	public void setMember_info_detail_point(int member_info_detail_point) {
		this.member_info_detail_point = member_info_detail_point;
	}

	public int getMember_info_detail_acc_money() {
		return member_info_detail_acc_money;
	}

	public void setMember_info_detail_acc_money(int member_info_detail_acc_money) {
		this.member_info_detail_acc_money = member_info_detail_acc_money;
	}

	public String getMember_service_log_status() {
		return member_service_log_status;
	}

	public void setMember_service_log_status(String member_service_log_status) {
		this.member_service_log_status = member_service_log_status;
	}

	public String getMember_service_log_join_date() {
		return member_service_log_join_date;
	}

	public void setMember_service_log_join_date(String member_service_log_join_date) {
		this.member_service_log_join_date = member_service_log_join_date;
	}

	public String getMember_service_log_passwd_change_date() {
		return member_service_log_passwd_change_date;
	}

	public void setMember_service_log_passwd_change_date(String member_service_log_passwd_change_date) {
		this.member_service_log_passwd_change_date = member_service_log_passwd_change_date;
	}

	public String getMember_service_log_login_date() {
		return member_service_log_login_date;
	}

	public void setMember_service_log_login_date(String member_service_log_login_date) {
		this.member_service_log_login_date = member_service_log_login_date;
	}

	public int getMember_service_log_order_count() {
		return member_service_log_order_count;
	}

	public void setMember_service_log_order_count(int member_service_log_order_count) {
		this.member_service_log_order_count = member_service_log_order_count;
	}

	public int getTop_level() {
		return top_level;
	}

	public void setTop_level(int top_level) {
		this.top_level = top_level;
	}

	public int getNomal() {
		return nomal;
	}

	public void setNomal(int nomal) {
		this.nomal = nomal;
	}

	public int getSuspension() {
		return suspension;
	}

	public void setSuspension(int suspension) {
		this.suspension = suspension;
	}

	public int getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}

	@Override
	public String toString() {
		return "MemberBean [grade_name=" + grade_name + ", lowest_acc_money=" + lowest_acc_money
				+ ", highest_acc_money=" + highest_acc_money + ", reason_num=" + reason_num + ", reason_content="
				+ reason_content + ", member_num=" + member_num + ", member_code=" + member_code + ", member_nickname="
				+ member_nickname + ", member_id=" + member_id + ", member_passwd=" + member_passwd + ", member_email="
				+ member_email + ", member_info_name=" + member_info_name + ", member_info_gender=" + member_info_gender
				+ ", member_info_phone=" + member_info_phone + ", member_info_age=" + member_info_age
				+ ", member_info_post_code=" + member_info_post_code + ", member_info_address=" + member_info_address
				+ ", member_info_address_detail=" + member_info_address_detail + ", member_info_ship_post_code="
				+ member_info_ship_post_code + ", member_info_ship_address=" + member_info_ship_address
				+ ", member_info_ship_address_detail=" + member_info_ship_address_detail
				+ ", member_info_mypage_img_name=" + member_info_mypage_img_name + ", member_info_mypage_real_img_name="
				+ member_info_mypage_real_img_name + ", member_info_detail_like_style=" + member_info_detail_like_style
				+ ", member_info_detail_like_brand=" + member_info_detail_like_brand
				+ ", member_info_detail_like_category=" + member_info_detail_like_category
				+ ", member_info_detail_point=" + member_info_detail_point + ", member_info_detail_acc_money="
				+ member_info_detail_acc_money + ", member_service_log_status=" + member_service_log_status
				+ ", member_service_log_join_date=" + member_service_log_join_date
				+ ", member_service_log_passwd_change_date=" + member_service_log_passwd_change_date
				+ ", member_service_log_login_date=" + member_service_log_login_date
				+ ", member_service_log_order_count=" + member_service_log_order_count + ", top_level=" + top_level
				+ ", nomal=" + nomal + ", suspension=" + suspension + ", withdrawal=" + withdrawal + "]";
	};
	
	
	
}