package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.QnaBean;

public class QnaListService {

	public int getListCount(String tableName) {
//		System.out.println("QnaListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<QnaBean> getQnaList(int pageNum, int listLimit) {
//		System.out.println("QnaListService - getQnaList()");
		
		ArrayList<QnaBean> qnaList = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		qnaList = adminDAO.selectQnaList(pageNum, listLimit);
		
		close(con);
		
		return qnaList;
	}

}
