package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;
import vo.EventImgFileBean;

public class EventDetailService {

	public EventBean getEventArticle(int admin_event_num) {
//		System.out.println("EventDetailService - getNoticeArticle()");
		
		EventBean eventArticle = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		eventArticle = adminDAO.selectEventArticle(admin_event_num);
		
		close(con);
		
		return eventArticle;
	}

	public ArrayList<EventImgFileBean> getNoticeImg(int admin_event_num) {
	
		ArrayList<EventImgFileBean> eventImgFileList = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		eventImgFileList = adminDAO.getEventImg(admin_event_num);
		
		close(con);
		
		return eventImgFileList;
	}

	public void increaseEventReadcount(int admin_event_num) {
		Connection con = getConnection(); 
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		adminDAO.updateEventReadcount(admin_event_num);
		
		commit(con);
		
		close(con);
		
	}

	
	
}
