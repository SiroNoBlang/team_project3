package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.NoticeBean;

public class NoticeListService {

	public int getListCount(String tableName) {
		System.out.println("NoticeListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<NoticeBean> getNoticeList(int pageNum, int listLimit) {
		System.out.println("NoticeListService - getNoticeleList()");
		
		ArrayList<NoticeBean> noticeList = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeList = adminDAO.selectNoticeList(pageNum, listLimit);
		
		close(con);
		
		return noticeList;
	}

	
}
