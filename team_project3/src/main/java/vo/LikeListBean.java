package vo;

/*
 * CREATE TABLE mypage_like_list (
like_list_count int,
like_list_title VARCHAR(100),
like_list_img_name VARCHAR(100)
);
 * */

public class LikeListBean {
	private int like_list_count;
	private String like_list_title;
	private String like_list_img_name;
	
	
	public int getLike_list_count() {
		return like_list_count;
	}
	public void setLike_list_count(int like_list_count) {
		this.like_list_count = like_list_count;
	}
	public String getLike_list_title() {
		return like_list_title;
	}
	public void setLike_list_title(String like_list_title) {
		this.like_list_title = like_list_title;
	}
	public String getLike_list_img_name() {
		return like_list_img_name;
	}
	public void setLike_list_img_name(String like_list_img_name) {
		this.like_list_img_name = like_list_img_name;
	}
	
	@Override
	public String toString() {
		return "LikeListBean [like_list_count=" + like_list_count + ", like_list_title=" + like_list_title
				+ ", like_list_img_name=" + like_list_img_name + "]";
	}
	
	
	
}
