package vo;


//CREATE TABLE notice_img_file (
//		notice_img_file_real_num INT,
//		notice_img_file_num INT,
//		notice_img_file_name VARCHAR(400),
//		notice_img_file_real_name VARCHAR(400),
//		foreign key(notice_img_file_num ) REFERENCES notice(notice_num) ON DELETE CASCADE
//	);


public class NoticeImgFileBean {
	
	private int notice_img_file_real_num;
	private int notice_img_file_num;
	private String notice_img_file_name;
	private String notice_img_file_real_name;
	
	
	
	public int getNotice_img_file_real_num() {
		return notice_img_file_real_num;
	}
	public void setNotice_img_file_real_num(int notice_img_file_real_num) {
		this.notice_img_file_real_num = notice_img_file_real_num;
	}
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
		return "NoticeImgFileBean [notice_img_file_real_num=" + notice_img_file_real_num + ", notice_img_file_num="
				+ notice_img_file_num + ", notice_img_file_name=" + notice_img_file_name
				+ ", notice_img_file_real_name=" + notice_img_file_real_name + "]";
	}
	
	
	
	
	
}














