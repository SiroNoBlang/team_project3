package vo;



/*CREATE TABLE notice (
		notice_num INT Auto_Increment PRIMARY KEY,
		notice_nickname VARCHAR(16),
		notice_write_date VARCHAR(17),
		notice_title VARCHAR(30),
		notice_content VARCHAR(2000),
		notice_readcount INT
	);
*/


public class NoticeBean {
	private int notice_num;
	private String notice_nickname;
	private String notice_write_date; 
	private String notice_title;
	private String notice_content;
	private int notice_readcount;
	
	
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_nickname() {
		return notice_nickname;
	}
	public void setNotice_nickname(String notice_nickname) {
		this.notice_nickname = notice_nickname;
	}
	public String getNotice_write_date() {
		return notice_write_date;
	}
	public void setNotice_write_date(String notice_write_date) {
		this.notice_write_date = notice_write_date;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	
	
	@Override
	public String toString() {
		return "NoticeBean [notice_num=" + notice_num + ", notice_nickname=" + notice_nickname + ", notice_write_date="
				+ notice_write_date + ", notice_title=" + notice_title + ", notice_content=" + notice_content
				+ ", notice_readcount=" + notice_readcount + "]";
	}

	
	
}














