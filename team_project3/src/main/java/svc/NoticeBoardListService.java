package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.NoticeBean;

public class NoticeBoardListService {

	public int getListCount() {
		System.out.println("NoticeBoardListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		String tableName = "notice";
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<NoticeBean> getNoticeleList(int pageNum, int listLimit) {
		System.out.println("NoticeBoardListService - getNoticeleList()");
		
		ArrayList<NoticeBean> noticeList = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeList = adminDAO.selectNoticeList(pageNum, listLimit);
		
		close(con);
		
		// 7. 조회 결과 리턴
		return noticeList;
	}

	
}
