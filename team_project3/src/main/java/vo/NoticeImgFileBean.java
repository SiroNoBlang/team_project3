package vo;


//CREATE TABLE notice_img_file (
//		notice_img_file_num INT REFERENCES admin_notice(notice_num),
//		notice_img_file_name VARCHAR(50) PRIMARY KEY,
//		notice_img_file_real_name VARCHAR(50)
//	);

public class NoticeImgFileBean {
	private int notice_img_file_num;
	private String notice_img_file_name;
	private String notice_img_file_real_name;
	public int getNotice_img_file_num() {
		return notice_img_file_num;
	}
	public void setNotice_img_file_num(int notice_img_file_num) {
		this.notice_img_file_num = notice_img_file_num;
	}
	public String getNotice_img_file_name() {
		return notice_img_file_name;
	}
	public void setNotice_img_file_name(String notice_img_file_name) {
		this.notice_img_file_name = notice_img_file_name;
	}
	public String getNotice_img_file_real_name() {
		return notice_img_file_real_name;
	}
	public void setNotice_img_file_real_name(String notice_img_file_real_name) {
		this.notice_img_file_real_name = notice_img_file_real_name;
	}
	
	
	@Override
	public String toString() {
		return "NoticeImgBean [notice_img_file_num=" + notice_img_file_num + ", notice_img_file_name="
				+ notice_img_file_name + ", notice_img_file_real_name=" + notice_img_file_real_name + "]";
	}
	
	
	
	
}














