package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.EventBean;
import vo.EventImgFileBean;
import vo.MemberBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

import static db.JdbcUtil.*;

public class AdminDAO {
	private static AdminDAO instance = new AdminDAO();

	private AdminDAO() {
	}

	public static AdminDAO getInstance() {
		return instance;
	};

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 공지사항 글쓰기
	public int insertNoticeArticle(NoticeBean notice, NoticeImgFileBean noticeImg) {
		System.out.println("AdminDAO - insertArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		int num = 1;

		try {
			String sql = "SELECT MAX(admin_notice_num) FROM admin_notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 조회된 레코드 중 Auto_increment 컬럼 값을 num 에 저장
				num = rs.getInt(1) + 1;
			}
			close(pstmt);

			sql = "INSERT INTO admin_notice VALUES (?,?,REPLACE(now(),'-',''),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, notice.getAdmin_notice_nickname());
			pstmt.setString(3, notice.getAdmin_notice_title());
			pstmt.setString(4, notice.getAdmin_notice_content());
			pstmt.setInt(5, 0);
			
			insertCount = pstmt.executeUpdate();
			
			
			if(noticeImg.getNotice_img_file_name() !=null) {
				
				sql = "INSERT INTO notice_img_file VALUES ((SELECT MAX(admin_notice_num) FROM admin_notice),?,?)"; 
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1,noticeImg.getNotice_img_file_name()); 
				pstmt.setString(2,noticeImg.getNotice_img_file_real_name());
				
				pstmt.executeUpdate();
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - insertNoticeArticle()");
		} finally {
			close(pstmt);
			close(pstmt2);
			close(rs);
		}
		return insertCount;
	}

	// 이벤트 글쓰기
	public int insertEventArticle(EventBean event, EventImgFileBean eventImg) {
		System.out.println("AdminDAO - insertEventArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		int num = 1;

		try {
			String sql = "SELECT MAX(admin_event_num) FROM admin_event";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 조회된 레코드 중 Auto_increment 컬럼 값을 num 에 저장
				num = rs.getInt(1) + 1;
			}
			close(pstmt);

			sql = "INSERT INTO admin_event VALUES (?,?,REPLACE(now(),'-',''),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, event.getAdmin_event_nickname());
			pstmt.setString(3, event.getAdmin_event_title());
			pstmt.setString(4, event.getAdmin_event_content());
			pstmt.setInt(5, 0);
			
			insertCount = pstmt.executeUpdate();
			
			if(eventImg.getEvent_img_file_name() !=null) {
			
			sql = "INSERT INTO event_img_file VALUES ((SELECT MAX(admin_event_num) FROM admin_event),?,?)"; 
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1,eventImg.getEvent_img_file_name()); 
			pstmt.setString(2,eventImg.getEvent_img_file_real_name());
			
			pstmt.executeUpdate();
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - insertEventArticle()");
		} finally {
			close(pstmt);
			close(pstmt2);
			close(rs);
		}
		return insertCount;
	}


	//총 공지사항게시물 수를 조회
	public int selectListCount(String tableName) {
		System.out.println("AdminDAO - selectListCount()");
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM " + tableName;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	//공지사항 목록을 조회
	public ArrayList<NoticeBean> selectNoticeleList(int pageNum, int listLimit) {
	ArrayList<NoticeBean> noticeList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM admin_notice LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			noticeList = new ArrayList<NoticeBean>();
			
			while(rs.next()) {
				
				NoticeBean notice = new NoticeBean();
				notice.setAdmin_notice_num(rs.getInt("admin_notice_num"));
				notice.setAdmin_notice_nickname(rs.getString("admin_notice_nickname"));
				notice.setAdmin_notice_write_date(rs.getString("admin_notice_write_date").substring(0,8));
				notice.setAdmin_notice_title(rs.getString("admin_notice_title"));
				notice.setAdmin_notice_content(rs.getString("admin_notice_content"));
				notice.setAdmin_notice_readcount(rs.getInt("admin_notice_readcount"));
				
				
				
				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectNoticeleList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return noticeList;
		
	}

	//공지사항 상세정보 조회
	public NoticeBean selectNoticeArticle(int admin_notice_num) {

		NoticeBean noticeArticle = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM admin_notice WHERE admin_notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_notice_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeArticle = new NoticeBean();
				noticeArticle.setAdmin_notice_num(rs.getInt("admin_notice_num"));
				noticeArticle.setAdmin_notice_nickname(rs.getString("admin_notice_nickname"));
				noticeArticle.setAdmin_notice_write_date(rs.getString("admin_notice_write_date").substring(0,8));
				noticeArticle.setAdmin_notice_title(rs.getString("admin_notice_title"));
				noticeArticle.setAdmin_notice_content(rs.getString("admin_notice_content"));
				noticeArticle.setAdmin_notice_readcount(rs.getInt("admin_notice_readcount"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectNoticeArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return noticeArticle;
		
		
	}

	//공지사항 조회수 증가
	public void updateReadcount(int admin_notice_num) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE admin_notice SET admin_notice_readcount=admin_notice_readcount+1 WHERE admin_notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_notice_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}

	// 공지사항 첨부파일 조회
	public NoticeImgFileBean getNoticeImg(int admin_notice_num) {
		NoticeImgFileBean noticeImgFile =  null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			//쿼리확인해야함
			String sql ="SELECT f.notice_img_file_num, f.notice_img_file_real_name, admin_notice_num FROM admin_notice JOIN notice_img_file f ON f.notice_img_file_num = admin_notice_num WHERE notice_img_file_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_notice_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeImgFile = new NoticeImgFileBean();
				noticeImgFile.setNotice_img_file_name(rs.getString("f.notice_img_file_num"));
				noticeImgFile.setNotice_img_file_real_name(rs.getString("f.notice_img_file_real_name"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - getNoticeImg()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return noticeImgFile;
	}

	public ArrayList<MemberBean> selectMemberManagementList(int pageNum, int listLimit) {
		ArrayList<MemberBean> memberManagementList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			
			String sql = "SELECT a.member_code, a.member_num, a.member_nickname, a.member_email, b.member_service_log_status, b.member_service_log_join_date FROM member AS a JOIN member_service_log AS b LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			memberManagementList = new ArrayList<MemberBean>();
			
			while(rs.next()) {
				
				MemberBean bean2 = new MemberBean();
				bean2.setMember_code(rs.getString("a.member_code"));
				bean2.setMember_num(rs.getString("a.member_num"));
				bean2.setMember_nickname(rs.getString("a.member_nickname"));
				bean2.setMember_email(rs.getString("a.member_email"));
				bean2.setMember_service_log_status(rs.getString("b.member_service_log_status"));
				bean2.setMember_service_log_join_date(rs.getString("b.member_service_log_join_date").substring(0, 8));
				
				memberManagementList.add(bean2);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectMemberManagementList()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return memberManagementList;
	}
	
}


