package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.MemberBean;

public class MemberManagementListService {

	public int getListCount() {
		System.out.println("MemberManagementListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		String tableName = "member";
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<MemberBean> getMemberManagementList(int pageNum, int listLimit) {
		System.out.println("NoticeBoardListService - getMemberManagementList()");
		
		ArrayList<MemberBean> memberManagementList = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		memberManagementList = adminDAO.selectMemberManagementList(pageNum, listLimit);
		
		close(con);
		
		// 7. 조회 결과 리턴
		return memberManagementList;
	}

}
