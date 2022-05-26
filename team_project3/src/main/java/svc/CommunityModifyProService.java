package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class CommunityModifyProService {

	
	// 공지사항 수정 작업을 요청하는 noticeModifyArticle() 메서드 정의
	public boolean noticeModifyArticle(int notice_num, NoticeBean notice, ArrayList<NoticeImgFileBean> noticeImgList) {
//		System.out.println("CommunityModifyProService - noticeModifyArticle");
		boolean isNoticeModifySuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
				
		int updateCount = adminDAO.updateNoticeArticle(notice_num,notice,noticeImgList);
		
		if(updateCount > 0) {
			commit(con);
			isNoticeModifySuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isNoticeModifySuccess;
	}

	// 이벤트 수정 작업을 요청하는 eventModifyArticle() 메서드 정의
	public boolean eventModifyArticle(int event_num,EventBean event, ArrayList<EventImgFileBean> eventImgList) {
//		System.out.println("CommunityModifyProService - eventModifyArticle");

		boolean isEventModifySuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int updateCount = adminDAO.updateEventArticle(event_num,event,eventImgList);
		
		if(updateCount > 0) {
			commit(con);
			isEventModifySuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isEventModifySuccess;
	}

	//공지사항 등록된 사진 확인 작업
	public ArrayList<NoticeImgFileBean> isImgExist(int notice_num) {
		ArrayList<NoticeImgFileBean> isNoticeImgExist = null;
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		isNoticeImgExist = adminDAO.selectImgExist(notice_num);
		
		close(con);
		
		return isNoticeImgExist;

	}

	//이벤트 등록된 사진 확인 작업
	public ArrayList<EventImgFileBean> isEventImgExist(int event_num) {
		ArrayList<EventImgFileBean> isEventImgExist = null;
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		isEventImgExist = adminDAO.selectEventImgExist(event_num);
		
		close(con);
		
		return isEventImgExist;
	}



}
