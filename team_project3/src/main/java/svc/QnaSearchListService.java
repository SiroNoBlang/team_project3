package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.QnaBean;

public class QnaSearchListService {

	public int selectSearchListCount(String tableName, String search, String searchType) {
//		System.out.println("QnaSearchListService - selectSearchListCount");
		int listCount =  0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectQnaSearchListCount(tableName,search,searchType);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<QnaBean> selectQnaSearchList(int pageNum, int listLimit, String search, String searchType) {
//		System.out.println("QnaSearchListService - selectQnaSearchList");
		
		ArrayList<QnaBean> qnaSearchList =null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		qnaSearchList = adminDAO.selectSearchQnaList(pageNum, listLimit, search, searchType);
		
		close(con);
		return qnaSearchList;
	}

}
