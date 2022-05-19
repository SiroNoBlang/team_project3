package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.NoticeBean;

public class NoticeSearchListService {

	public int selectSearchListCount(String tableName,String search, String searchType) {
		System.out.println("selectSearchListCount");
		int listCount =  0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectSearchListCount(tableName,search,searchType);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<NoticeBean> selectnoticeSearchList(int pageNum, int listLimit, String search, String searchType) {
		System.out.println("selectnoticeSearchList");
		
		ArrayList<NoticeBean> noticeSearchList =null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeSearchList = adminDAO.selectSearchNoticeList(pageNum, listLimit, search, searchType);
		
		close(con);
		
		return noticeSearchList;
	}

}
