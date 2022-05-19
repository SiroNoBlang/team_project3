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
	
	private int admin_event_num;
	private String admin_event_nickname;
	private String admin_event_write_date; 
	private String admin_event_title;
	private String admin_event_content;
	private int admin_event_readcount;
	
	
	public int getAdmin_event_num() {
		return admin_event_num;
	}
	public void setAdmin_event_num(int admin_event_num) {
		this.admin_event_num = admin_event_num;
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
	public int getAdmin_event_readcount() {
		return admin_event_readcount;
	}
	public void setAdmin_event_readcount(int admin_event_readcount) {
		this.admin_event_readcount = admin_event_readcount;
	}
	
	
	@Override
	public String toString() {
		return "EventBean [admin_event_num=" + admin_event_num + ", admin_event_nickname=" + admin_event_nickname
				+ ", admin_event_write_date=" + admin_event_write_date + ", admin_event_title=" + admin_event_title
				+ ", admin_event_content=" + admin_event_content + ", admin_event_readcount=" + admin_event_readcount
				+ "]";
	}
	
	
	
}
