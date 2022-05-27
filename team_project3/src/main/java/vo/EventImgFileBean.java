package vo;


//CREATE TABLE event_img_file (
//		event_img_file_real_num INT,
//		event_img_file_num INT,
//		event_img_file_name VARCHAR(400),
//		event_img_file_real_name VARCHAR(400),
//		foreign key(event_img_file_num) REFERENCES event(event_num) ON DELETE CASCADE
//	);



public class EventImgFileBean {
	private int event_img_file_real_num ;
	private int event_img_file_num;
	private String event_img_file_name;
	private String event_img_file_real_name;
	
	
	
	public int getEvent_img_file_real_num() {
		return event_img_file_real_num;
	}
	public void setEvent_img_file_real_num(int event_img_file_real_num) {
		this.event_img_file_real_num = event_img_file_real_num;
	}
	public int getEvent_img_file_num() {
		return event_img_file_num;
	}
	public void setEvent_img_file_num(int event_img_file_num) {
		this.event_img_file_num = event_img_file_num;
	}
	public String getEvent_img_file_name() {
		return event_img_file_name;
	}
	public void setEvent_img_file_name(String event_img_file_name) {
		this.event_img_file_name = event_img_file_name;
	}
	public String getEvent_img_file_real_name() {
		return event_img_file_real_name;
	}
	public void setEvent_img_file_real_name(String event_img_file_real_name) {
		this.event_img_file_real_name = event_img_file_real_name;
	}
	
	
	
	@Override
	public String toString() {
		return "EventImgFileBean [event_img_file_real_num=" + event_img_file_real_num + ", event_img_file_num="
				+ event_img_file_num + ", event_img_file_name=" + event_img_file_name + ", event_img_file_real_name="
				+ event_img_file_real_name + "]";
	}
	
	
	
}














