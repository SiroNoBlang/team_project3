package vo;

/*
 * CREATE TABLE admin_event (
		event_num INT Auto_Increment PRIMARY KEY,
		event_nickname VARCHAR(16),
		event_write_date VARCHAR(17),
		event_title VARCHAR(30),
		event_content VARCHAR(2000),
		event_readcount INT
	);
*/
public class EventBean {
	
	private int event_num;
	private String event_nickname;
	private String event_write_date; 
	private String event_title;
	private String event_content;
	private int event_readcount;
	
	
	
	
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public String getEvent_nickname() {
		return event_nickname;
	}
	public void setEvent_nickname(String event_nickname) {
		this.event_nickname = event_nickname;
	}
	public String getEvent_write_date() {
		return event_write_date;
	}
	public void setEvent_write_date(String event_write_date) {
		this.event_write_date = event_write_date;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public int getEvent_readcount() {
		return event_readcount;
	}
	public void setEvent_readcount(int event_readcount) {
		this.event_readcount = event_readcount;
	}
	
	
	
	@Override
	public String toString() {
		return "EventBean [event_num=" + event_num + ", event_nickname=" + event_nickname + ", event_write_date="
				+ event_write_date + ", event_title=" + event_title + ", event_content=" + event_content
				+ ", event_readcount=" + event_readcount + "]";
	}
	
	
	
}
