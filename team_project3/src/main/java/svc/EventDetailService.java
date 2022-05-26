package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;
import vo.EventImgFileBean;

public class EventDetailService {

	public EventBean getEventArticle(int event_num) {
//		System.out.println("EventDetailService - getEventArticle()");
		
		EventBean eventArticle = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		eventArticle = adminDAO.selectEventArticle(event_num);
		
		close(con);
		
		return eventArticle;
	}

	public ArrayList<EventImgFileBean> getEventImg(int event_num) {
	
		ArrayList<EventImgFileBean> eventImgFileList = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		eventImgFileList = adminDAO.getEventImg(event_num);
		
		close(con);
		
		return eventImgFileList;
	}

	public void increaseEventReadcount(int event_num) {
		Connection con = getConnection(); 
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		adminDAO.updateEventReadcount(event_num);
		
		commit(con);
		
		close(con);
		
	}

	
	
}
