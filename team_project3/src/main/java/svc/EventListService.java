package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;

public class EventListService {

	public int getListCount(String tableName) {
//		System.out.println("EventListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
		
	}

	public ArrayList<EventBean> getEventleList(int pageNum, int listLimit) {
//		System.out.println("NoticeListService - getNoticeleList()");
		
		ArrayList<EventBean> eventList = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		eventList = adminDAO.selectEventList(pageNum, listLimit);
		
		close(con);
		
		return eventList;
	}
	

}
