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
	public int insertNoticeArticle(NoticeBean notice,  ArrayList<NoticeImgFileBean> noticeImgList) {
//		System.out.println("AdminDAO - insertArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
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
			
			
			if(!noticeImgList.isEmpty()) {
				for(NoticeImgFileBean noticeImg: noticeImgList) {
					sql = "INSERT INTO notice_img_file VALUES ((SELECT MAX(admin_notice_num) FROM admin_notice),?,?)"; 
					pstmt = con.prepareStatement(sql); 
					
					pstmt.setString(1,noticeImg.getNotice_img_file_name()); 
					pstmt.setString(2,noticeImg.getNotice_img_file_real_name());
					
					pstmt.executeUpdate();
				} 
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - insertNoticeArticle()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}

	// 이벤트 글쓰기
	public int insertEventArticle(EventBean event,  ArrayList<EventImgFileBean> eventImgList) {
//		System.out.println("AdminDAO - insertEventArticle()");

		int insertCount = 0;

		PreparedStatement pstmt = null;
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
			
			
			if(!eventImgList.isEmpty()) {
				for(EventImgFileBean eventImg: eventImgList) {
					sql = "INSERT INTO event_img_file VALUES ((SELECT MAX(admin_event_num) FROM admin_event),?,?)"; 
					
					pstmt = con.prepareStatement(sql); 
					pstmt.setString(1,eventImg.getEvent_img_file_name()); 
					pstmt.setString(2,eventImg.getEvent_img_file_real_name());
					
					pstmt.executeUpdate();
				}
			} 


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - insertEventArticle()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}

	//총 공지사항게시물 수를 조회
	public int selectListCount(String tableName) {
//		System.out.println("AdminDAO - selectListCount()");
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM "+tableName;
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
	public ArrayList<NoticeBean> selectNoticeList(int pageNum, int listLimit) {
	ArrayList<NoticeBean> noticeList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM admin_notice ORDER BY admin_notice_num DESC LIMIT ?,?";
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
	public void updateNoticeReadcount(int admin_notice_num) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE admin_notice SET admin_notice_readcount=admin_notice_readcount+1 WHERE admin_notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_notice_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateNoticeReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}

	// 공지사항 첨부파일 조회
	public ArrayList<NoticeImgFileBean>  getNoticeImg(int admin_notice_num) {
		ArrayList<NoticeImgFileBean> noticeImgFileList = new ArrayList<NoticeImgFileBean>();
		
		NoticeImgFileBean noticeImg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql ="SELECT f.notice_img_file_name, f.notice_img_file_real_name, admin_notice_num FROM admin_notice JOIN notice_img_file f ON f.notice_img_file_num = admin_notice_num WHERE notice_img_file_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_notice_num);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				noticeImg = new NoticeImgFileBean();
				noticeImg.setNotice_img_file_name(rs.getString("f.notice_img_file_name"));
				noticeImg.setNotice_img_file_real_name(rs.getString("f.notice_img_file_real_name"));
				
				noticeImgFileList.add(noticeImg);
				
			} 
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - getNoticeImg()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return noticeImgFileList;
	}
	
	//공지사항 검색어에 해당하는 게시물 수 (코드 수정하기)
	public int selectNoticeSearchListCount(String tableName, String search, String searchType) {
		int listCount =  0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(admin_notice_num) FROM "+ tableName+ " WHERE " + searchType + " LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectNoticeSearchListCount()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}

	//공지사항 검색어에 해당하는 게시물 목록
	public ArrayList<NoticeBean> selectSearchNoticeList(int pageNum, int listLimit, String search, String searchType) {
		ArrayList<NoticeBean> noticeSearchList =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			int startRow = (pageNum - 1) * listLimit;
			
			String sql = "SELECT * FROM admin_notice "
					+ "WHERE " + searchType + " LIKE ? "
					+ "ORDER BY admin_notice_num DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			noticeSearchList = new ArrayList<NoticeBean>();
			
			while(rs.next()) {
				NoticeBean noticeArticle = new NoticeBean();
				
				noticeArticle.setAdmin_notice_num(rs.getInt("admin_notice_num"));
				noticeArticle.setAdmin_notice_nickname(rs.getString("admin_notice_nickname"));
				noticeArticle.setAdmin_notice_write_date(rs.getString("admin_notice_write_date").substring(0,8));
				noticeArticle.setAdmin_notice_title(rs.getString("admin_notice_title"));
				noticeArticle.setAdmin_notice_content(rs.getString("admin_notice_content"));
				noticeArticle.setAdmin_notice_readcount(rs.getInt("admin_notice_readcount"));
			
				noticeSearchList.add(noticeArticle);
			}
			
//				System.out.println(noticeSearchList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectSearchNoticeList()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return noticeSearchList;
	}

	//이벤트 글목록 조회
	public ArrayList<EventBean> selectEventList(int pageNum, int listLimit) {
		ArrayList<EventBean> eventList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM admin_event ORDER BY admin_event_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			eventList = new ArrayList<EventBean>();
			
			while(rs.next()) {
				
				EventBean event = new EventBean();
				event.setAdmin_event_num(rs.getInt("admin_event_num"));
				event.setAdmin_event_nickname(rs.getString("admin_event_nickname"));
				event.setAdmin_event_write_date(rs.getString("admin_event_write_date").substring(0,8));
				event.setAdmin_event_title(rs.getString("admin_event_title"));
				event.setAdmin_event_content(rs.getString("admin_event_content"));
				event.setAdmin_event_readcount(rs.getInt("admin_event_readcount"));
				
				eventList.add(event);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectEventList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return eventList;
		
	}
	
	//이벤트 상세정보 조회
	public EventBean selectEventArticle(int admin_event_num) {
		EventBean eventArticle = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM admin_event WHERE admin_event_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_event_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				eventArticle = new EventBean();
				eventArticle.setAdmin_event_num(rs.getInt("admin_event_num"));
				eventArticle.setAdmin_event_nickname(rs.getString("admin_event_nickname"));
				eventArticle.setAdmin_event_write_date(rs.getString("admin_event_write_date").substring(0,8));
				eventArticle.setAdmin_event_title(rs.getString("admin_event_title"));
				eventArticle.setAdmin_event_content(rs.getString("admin_event_content"));
				eventArticle.setAdmin_event_readcount(rs.getInt("admin_event_readcount"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectEventArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return eventArticle;
	}

	//이벤트 첨부파일 조회
	public ArrayList<EventImgFileBean> getEventImg(int admin_event_num) {
	ArrayList<EventImgFileBean> eventImgFileList = new ArrayList<EventImgFileBean>();
		
		EventImgFileBean eventImg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql ="SELECT f.event_img_file_name, f.event_img_file_real_name, admin_event_num FROM admin_event JOIN event_img_file f ON f.event_img_file_num = admin_event_num WHERE event_img_file_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_event_num);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				eventImg = new EventImgFileBean();
				eventImg.setEvent_img_file_name(rs.getString("f.event_img_file_name"));
				eventImg.setEvent_img_file_real_name(rs.getString("f.event_img_file_real_name"));
				
				eventImgFileList.add(eventImg);
			} 
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - getEventImg()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return eventImgFileList;
	}

	//이벤트 조회수 증가
	public void updateEventReadcount(int admin_event_num) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE admin_event SET admin_event_readcount=admin_event_readcount+1 WHERE admin_event_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin_event_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateEventReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}
	
	// 이벤트에 해당하는 게시물 수 (코드 수정하기)
	public int selectEventSearchListCount(String tableName, String search, String searchType) {
		int listCount =  0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(admin_event_num) FROM "+ tableName+ " WHERE " + searchType + " LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectEventSearchListCount()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}
	
	//이벤트 검색어에 해당하는 게시물 목록
	public ArrayList<EventBean> selectSearchEventList(int pageNum, int listLimit, String search, String searchType) {
		ArrayList<EventBean> eventSearchList =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			int startRow = (pageNum - 1) * listLimit;
			
			String sql = "SELECT * FROM admin_event "
					+ "WHERE " + searchType + " LIKE ? "
					+ "ORDER BY admin_event_num DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			eventSearchList = new ArrayList<EventBean>();
			
			while(rs.next()) {
				EventBean eventArticle = new EventBean();
				
				eventArticle.setAdmin_event_num(rs.getInt("admin_event_num"));
				eventArticle.setAdmin_event_nickname(rs.getString("admin_event_nickname"));
				eventArticle.setAdmin_event_write_date(rs.getString("admin_event_write_date").substring(0,8));
				eventArticle.setAdmin_event_title(rs.getString("admin_event_title"));
				eventArticle.setAdmin_event_content(rs.getString("admin_event_content"));
				eventArticle.setAdmin_event_readcount(rs.getInt("admin_event_readcount"));
			
				eventSearchList.add(eventArticle);
			}
//				System.out.println(eventSearchList);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectSearchEventList()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return eventSearchList;
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
	
	
	


