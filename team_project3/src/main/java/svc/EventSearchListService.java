package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;

public class EventSearchListService {

	public int selectSearchListCount(String tableName, String search, String searchType) {
//		System.out.println("EventSearchListService - selectSearchListCount");
		int listCount =  0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectEventSearchListCount(tableName,search,searchType);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<EventBean> selectEventSearchList(int pageNum, int listLimit, String search, String searchType) {
//		System.out.println("EventSearchListService - selectEventSearchList");
		
		ArrayList<EventBean> eventSearchList =null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		eventSearchList = adminDAO.selectSearchEventList(pageNum, listLimit, search, searchType);
		
		close(con);
		
		return eventSearchList;
	}

}
