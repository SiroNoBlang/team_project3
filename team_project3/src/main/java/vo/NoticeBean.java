package vo;



/*CREATE TABLE admin_notice (
		admin_notice_num INT Auto_Increment PRIMARY KEY,
		admin_notice_nickname VARCHAR(16),
		admin_notice_write_date VARCHAR(17),
		admin_notice_title VARCHAR(30),
		admin_notice_content VARCHAR(2000),
		admin_notice_readcount INT
	);
*/

public class NoticeBean {
	private int admin_notice_num;
	private String admin_notice_nickname;
	private String admin_notice_write_date; 
	private String admin_notice_title;
	private String admin_notice_content;
	private int admin_notice_readcount;
	public int getAdmin_notice_num() {
		return admin_notice_num;
	}
	public void setAdmin_notice_num(int admin_notice_num) {
		this.admin_notice_num = admin_notice_num;
	}
	public String getAdmin_notice_nickname() {
		return admin_notice_nickname;
	}
	public void setAdmin_notice_nickname(String admin_notice_nickname) {
		this.admin_notice_nickname = admin_notice_nickname;
	}
	public String getAdmin_notice_write_date() {
		return admin_notice_write_date;
	}
	public void setAdmin_notice_write_date(String admin_notice_write_date) {
		this.admin_notice_write_date = admin_notice_write_date;
	}
	public String getAdmin_notice_title() {
		return admin_notice_title;
	}
	public void setAdmin_notice_title(String admin_notice_title) {
		this.admin_notice_title = admin_notice_title;
	}
	public String getAdmin_notice_content() {
		return admin_notice_content;
	}
	public void setAdmin_notice_content(String admin_notice_content) {
		this.admin_notice_content = admin_notice_content;
	}
	public int getAdmin_notice_readcount() {
		return admin_notice_readcount;
	}
	public void setAdmin_notice_readcount(int admin_notice_readcount) {
		this.admin_notice_readcount = admin_notice_readcount;
	}
	
	
	
	@Override
	public String toString() {
		return "NoticeBean [admin_notice_num=" + admin_notice_num + ", admin_notice_nickname=" + admin_notice_nickname
				+ ", admin_notice_write_date=" + admin_notice_write_date + ", admin_notice_title=" + admin_notice_title
				+ ", admin_notice_content=" + admin_notice_content + ", admin_notice_readcount="
				+ admin_notice_readcount + "]";
	}
	
	
	
}














