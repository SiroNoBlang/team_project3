package vo;


/*
CREATE TABLE qna (
		qna_num INT Auto_Increment PRIMARY KEY,
		qna_nickname VARCHAR(16),
		qna_write_date VARCHAR(17),
		qna_title VARCHAR(50),
		qna_content VARCHAR(3000),
		qna_re_ref INT,
		qna_re_lev INT,
		qna_re_seq INT,
		qna_readcount INT
	);
*/

public class QnaBean {
	private int qna_num;
	private String qna_nickname;
	private String qna_write_date; 
	private String qna_title;
	private String qna_content;
	private int qna_re_ref;
	private int qna_re_lev;
	private int qna_re_seq;
	private int qna_readcount;
	
	
	
	
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getQna_nickname() {
		return qna_nickname;
	}
	public void setQna_nickname(String qna_nickname) {
		this.qna_nickname = qna_nickname;
	}
	public String getQna_write_date() {
		return qna_write_date;
	}
	public void setQna_write_date(String qna_write_date) {
		this.qna_write_date = qna_write_date;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public int getQna_re_ref() {
		return qna_re_ref;
	}
	public void setQna_re_ref(int qna_re_ref) {
		this.qna_re_ref = qna_re_ref;
	}
	public int getQna_re_lev() {
		return qna_re_lev;
	}
	public void setQna_re_lev(int qna_re_lev) {
		this.qna_re_lev = qna_re_lev;
	}
	public int getQna_re_seq() {
		return qna_re_seq;
	}
	public void setQna_re_seq(int qna_re_seq) {
		this.qna_re_seq = qna_re_seq;
	}
	public int getQna_readcount() {
		return qna_readcount;
	}
	public void setQna_readcount(int qna_readcount) {
		this.qna_readcount = qna_readcount;
	}
	
	
	@Override
	public String toString() {
		return "QnaBean [qna_num=" + qna_num + ", qna_nickname=" + qna_nickname + ", qna_write_date=" + qna_write_date
				+ ", qna_title=" + qna_title + ", qna_content=" + qna_content + ", qna_re_ref=" + qna_re_ref
				+ ", qna_re_lev=" + qna_re_lev + ", qna_re_seq=" + qna_re_seq + ", qna_readcount=" + qna_readcount
				+ "]";
	}
	
	
	
}














