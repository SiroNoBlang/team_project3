package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import dao.MemberDAO;
import vo.MemberBean;

public class MemberManagementListService {

	// 총 개시물 수를 가져오기 위해 DAO로 연결하는 service 객체 내의 getListCount() 메서드 _이효민 06.12 수정
	public int getListCount() {
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		String tableName = "member";
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
	}

	// 화면에 보여줄 리스트를 담을 값을 가져오기 위한 Service 객체의 getMemberManagementList() 메서드 _이효민 06.12 수정
	public ArrayList<MemberBean> getMemberManagementList(int pageNum, int listLimit) {
		ArrayList<MemberBean> memberManagementList = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		memberManagementList = adminDAO.selectMemberManagementList(pageNum, listLimit);
		
		close(con);
		
		return memberManagementList;
	}

	// 상태에 따라 다른 회원의 수를 가져오기 위한 getStatusCount() 메서드 _ 이효민 06.12 수정
	public MemberBean getStatusCount() {
		MemberBean bean = null;
		
		Connection con = getConnection();
		AdminDAO dao = AdminDAO.getInstance();
		
		dao.setConnection(con);
		
		bean = dao.getMemberStatus();
		
		close(con);
		
		return bean;
	}

	// 상태 및 검색어 따른 리스트 목록을 보여주기 위한 getClassificationList()메서드 _ 이효민 06.12 수정
	public ArrayList<MemberBean> getClassificationList(int pageNum, int listLimit, String value) {
		ArrayList<MemberBean> classificationList = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		classificationList = adminDAO.selectClassificationList(pageNum, listLimit, value);
		
		close(con);
		
		return classificationList;
	}
	
	// 각 값에 해당하는 총 개시물 수를 가져오기 위해 DAO로 연결하는 service 객체 내의 getListCount() 메서드 _이효민 06.14 수정
	public int getListCount(String value) {
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		String tableName = "";
		if(value.equals("0")) {
			tableName = "member_info_detail";
			value = "0";
		} else if(value.equals("1") || value.equals("2") || value.equals("3")) {
			tableName = "member_service_log";
			if(value.equals("1")) {
				value = "정상";
			} else if(value.equals("2")) {
				value = "정지";
			} else {
				value = "탈퇴";
			}
		} else {
			tableName = "member";
		}
		
		listCount = adminDAO.selectListCount(tableName, value);
		
		close(con);
		
		return listCount;
	}

}
