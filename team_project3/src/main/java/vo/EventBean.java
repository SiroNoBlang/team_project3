package vo;

/*
 * CREATE TABLE admin_event (
		admin_event_num INT Auto_Increment PRIMARY KEY,
		admin_event_nickname VARCHAR(16),
		admin_event_write_date VARCHAR(17),
		admin_event_title VARCHAR(30),
		admin_event_content VARCHAR(2000),
		admin_event_readcount INT
	);
*/
public class EventBean {
	
	private int aadmin_event_num;
	private String admin_event_nickname;
	private String admin_event_write_date; 
	private String admin_event_title;
	private String admin_event_content;
	private int aadmin_event_readcount;
	public int getAadmin_event_num() {
		return aadmin_event_num;
	}
	public void setAadmin_event_num(int aadmin_event_num) {
		this.aadmin_event_num = aadmin_event_num;
	}
	public String getAdmin_event_nickname() {
		return admin_event_nickname;
	}
	public void setAdmin_event_nickname(String admin_event_nickname) {
		this.admin_event_nickname = admin_event_nickname;
	}
	public String getAdmin_event_write_date() {
		return admin_event_write_date;
	}
	public void setAdmin_event_write_date(String admin_event_write_date) {
		this.admin_event_write_date = admin_event_write_date;
	}
	public String getAdmin_event_title() {
		return admin_event_title;
	}
	public void setAdmin_event_title(String admin_event_title) {
		this.admin_event_title = admin_event_title;
	}
	public String getAdmin_event_content() {
		return admin_event_content;
	}
	public void setAdmin_event_content(String admin_event_content) {
		this.admin_event_content = admin_event_content;
	}
	public int getAadmin_event_readcount() {
		return aadmin_event_readcount;
	}
	public void setAadmin_event_readcount(int aadmin_event_readcount) {
		this.aadmin_event_readcount = aadmin_event_readcount;
	}
	
	
	@Override
	public String toString() {
		return "EventBean [aadmin_event_num=" + aadmin_event_num + ", admin_event_nickname=" + admin_event_nickname
				+ ", admin_event_write_date=" + admin_event_write_date + ", admin_event_title=" + admin_event_title
				+ ", admin_event_content=" + admin_event_content + ", aadmin_event_readcount=" + aadmin_event_readcount
				+ "]";
	}
	
	
	
}
