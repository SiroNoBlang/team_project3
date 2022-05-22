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
import vo.QnaBean;

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
			String sql = "SELECT MAX(notice_num) FROM notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 조회된 레코드 중 Auto_increment 컬럼 값을 num 에 저장
				num = rs.getInt(1) + 1;
			}
			close(pstmt);

			sql = "INSERT INTO notice VALUES (?,?,REPLACE(now(),'-',''),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, notice.getNotice_nickname());
			pstmt.setString(3, notice.getNotice_title());
			pstmt.setString(4, notice.getNotice_content());
			pstmt.setInt(5, 0);
			
			insertCount = pstmt.executeUpdate();
			
			
			if(!noticeImgList.isEmpty()) {
				for(NoticeImgFileBean noticeImg: noticeImgList) {
					sql = "INSERT INTO notice_img_file VALUES ((SELECT MAX(notice_num) FROM notice),?,?)"; 
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
			String sql = "SELECT MAX(event_num) FROM event";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 조회된 레코드 중 Auto_increment 컬럼 값을 num 에 저장
				num = rs.getInt(1) + 1;
			}
			close(pstmt);

			sql = "INSERT INTO event VALUES (?,?,REPLACE(now(),'-',''),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, event.getEvent_nickname());
			pstmt.setString(3, event.getEvent_title());
			pstmt.setString(4, event.getEvent_content());
			pstmt.setInt(5, 0);
			
			insertCount = pstmt.executeUpdate();
			
			
			if(!eventImgList.isEmpty()) {
				for(EventImgFileBean eventImg: eventImgList) {
					sql = "INSERT INTO event_img_file VALUES ((SELECT MAX(event_num) FROM event),?,?)"; 
					
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

	//총 게시물 수를 조회
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
			String sql = "SELECT * FROM notice ORDER BY notice_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			noticeList = new ArrayList<NoticeBean>();
			
			while(rs.next()) {
				
				NoticeBean notice = new NoticeBean();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_nickname(rs.getString("notice_nickname"));
				notice.setNotice_write_date(rs.getString("notice_write_date").substring(0,8));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_readcount(rs.getInt("notice_readcount"));
				
				
				
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
	public NoticeBean selectNoticeArticle(int notice_num) {

		NoticeBean noticeArticle = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM notice WHERE notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeArticle = new NoticeBean();
				noticeArticle.setNotice_num(rs.getInt("notice_num"));
				noticeArticle.setNotice_nickname(rs.getString("notice_nickname"));
				noticeArticle.setNotice_write_date(rs.getString("notice_write_date").substring(0,8));
				noticeArticle.setNotice_title(rs.getString("notice_title"));
				noticeArticle.setNotice_content(rs.getString("notice_content"));
				noticeArticle.setNotice_readcount(rs.getInt("notice_readcount"));
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
	public void updateNoticeReadcount(int notice_num) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE notice SET notice_readcount=notice_readcount+1 WHERE notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateNoticeReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}

	// 공지사항 첨부파일 조회
	public ArrayList<NoticeImgFileBean>  getNoticeImg(int notice_num) {
		ArrayList<NoticeImgFileBean> noticeImgFileList = new ArrayList<NoticeImgFileBean>();
		
		NoticeImgFileBean noticeImg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql ="SELECT f.notice_img_file_name, f.notice_img_file_real_name, notice_num FROM notice JOIN notice_img_file f ON f.notice_img_file_num = notice_num WHERE notice_img_file_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
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
	
	//공지사항 검색어에 해당하는 게시물 수 
	public int selectNoticeSearchListCount(String tableName, String search, String searchType) {
		int listCount =  0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(notice_num) FROM "+ tableName+ " WHERE " + searchType + " LIKE ?";
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
			
			String sql = "SELECT * FROM notice "
					+ "WHERE " + searchType + " LIKE ? "
					+ "ORDER BY notice_num DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			noticeSearchList = new ArrayList<NoticeBean>();
			
			while(rs.next()) {
				NoticeBean noticeArticle = new NoticeBean();
				
				noticeArticle.setNotice_num(rs.getInt("notice_num"));
				noticeArticle.setNotice_nickname(rs.getString("notice_nickname"));
				noticeArticle.setNotice_write_date(rs.getString("notice_write_date").substring(0,8));
				noticeArticle.setNotice_title(rs.getString("notice_title"));
				noticeArticle.setNotice_content(rs.getString("notice_content"));
				noticeArticle.setNotice_readcount(rs.getInt("notice_readcount"));
			
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
			String sql = "SELECT * FROM event ORDER BY event_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			eventList = new ArrayList<EventBean>();
			
			while(rs.next()) {
				
				EventBean event = new EventBean();
				event.setEvent_num(rs.getInt("event_num"));
				event.setEvent_nickname(rs.getString("event_nickname"));
				event.setEvent_write_date(rs.getString("event_write_date").substring(0,8));
				event.setEvent_title(rs.getString("event_title"));
				event.setEvent_content(rs.getString("event_content"));
				event.setEvent_readcount(rs.getInt("event_readcount"));
				
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
	public EventBean selectEventArticle(int event_num) {
		EventBean eventArticle = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM event WHERE event_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, event_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				eventArticle = new EventBean();
				eventArticle.setEvent_num(rs.getInt("event_num"));
				eventArticle.setEvent_nickname(rs.getString("event_nickname"));
				eventArticle.setEvent_write_date(rs.getString("event_write_date").substring(0,8));
				eventArticle.setEvent_title(rs.getString("event_title"));
				eventArticle.setEvent_content(rs.getString("event_content"));
				eventArticle.setEvent_readcount(rs.getInt("event_readcount"));
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
	public ArrayList<EventImgFileBean> getEventImg(int event_num) {
	ArrayList<EventImgFileBean> eventImgFileList = new ArrayList<EventImgFileBean>();
		
		EventImgFileBean eventImg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql ="SELECT f.event_img_file_name, f.event_img_file_real_name, event_num FROM event JOIN event_img_file f ON f.event_img_file_num = event_num WHERE event_img_file_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, event_num);
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
	public void updateEventReadcount(int event_num) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE event SET event_readcount=event_readcount+1 WHERE event_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, event_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateEventReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}
	
	// 이벤트에 해당하는 게시물 수 
	public int selectEventSearchListCount(String tableName, String search, String searchType) {
		int listCount =  0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(event_num) FROM "+ tableName+ " WHERE " + searchType + " LIKE ?";
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
			
			String sql = "SELECT * FROM event "
					+ "WHERE " + searchType + " LIKE ? "
					+ "ORDER BY event_num DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			eventSearchList = new ArrayList<EventBean>();
			
			while(rs.next()) {
				EventBean eventArticle = new EventBean();
				
				eventArticle.setEvent_num(rs.getInt("event_num"));
				eventArticle.setEvent_nickname(rs.getString("event_nickname"));
				eventArticle.setEvent_write_date(rs.getString("event_write_date").substring(0,8));
				eventArticle.setEvent_title(rs.getString("event_title"));
				eventArticle.setEvent_content(rs.getString("event_content"));
				eventArticle.setEvent_readcount(rs.getInt("event_readcount"));
			
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
	
	//QnA 글쓰기
	public int insertQnaArticle(QnaBean qna) {

		int insertCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 1;

		try {
			String sql = "SELECT MAX(qna_num) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			close(pstmt);

			sql = "INSERT INTO qna VALUES (?,?,REPLACE(now(),'-',''),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, qna.getQna_nickname());
			pstmt.setString(3, qna.getQna_title());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setInt(5, num); // qna_re_ref
			pstmt.setInt(6, 0); // qna_re_lev
			pstmt.setInt(7, 0); // qna_re_seq
			pstmt.setInt(8, 0); // qna_readcount
			
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - insertQnaArticle()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}

	//QnA 리스트
	public ArrayList<QnaBean> selectQnaList(int pageNum, int listLimit) {
		ArrayList<QnaBean> qnaList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM qna ORDER BY qna_re_ref DESC, qna_re_seq ASC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			qnaList = new ArrayList<QnaBean>();
			
			while(rs.next()) {
				
				QnaBean qna = new QnaBean();
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setQna_nickname(rs.getString("qna_nickname"));
				qna.setQna_write_date(rs.getString("qna_write_date").substring(0,8));
				qna.setQna_title(rs.getString("qna_title"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				qna.setQna_readcount(rs.getInt("qna_readcount"));
				
				qnaList.add(qna);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectQnaList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return qnaList;
		
	}

	
	//QnA  상세정보 조회
	public QnaBean selectQnaArticle(int qna_num) {

		QnaBean qnaArticle = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM qna WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaArticle = new QnaBean();
				qnaArticle.setQna_num(rs.getInt("qna_num"));
				qnaArticle.setQna_nickname(rs.getString("qna_nickname"));
				qnaArticle.setQna_write_date(rs.getString("qna_write_date").substring(0,8));
				qnaArticle.setQna_title(rs.getString("qna_title"));
				qnaArticle.setQna_content(rs.getString("qna_content"));
				qnaArticle.setQna_re_ref(rs.getInt("qna_re_ref"));
				qnaArticle.setQna_re_lev(rs.getInt("qna_re_lev"));
				qnaArticle.setQna_re_seq(rs.getInt("qna_re_seq"));
				qnaArticle.setQna_readcount(rs.getInt("qna_readcount"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectQnaArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return qnaArticle;
		
	}
	
	//QnA 조회수 증가
	public void updateQnaReadcount(int qna_num) {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE qna SET qna_readcount=qna_readcount+1 WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateQnaReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
	}


	//qna 검색어에 해당하는 게시물 수 
	public int selectQnaSearchListCount(String tableName, String search, String searchType) {
		int listCount =  0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(qna_num) FROM "+ tableName+ " WHERE " + searchType + " LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectQnaSearchListCount()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}

	
	//qna 검색어에 해당하는 게시물 목록
	public ArrayList<QnaBean> selectSearchQnaList(int pageNum, int listLimit, String search, String searchType) {
		ArrayList<QnaBean> qnaSearchList =null;
		QnaBean qnaArticle = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			int startRow = (pageNum - 1) * listLimit;
			
			String sql = "SELECT * FROM qna "
					+ "WHERE " + searchType + " LIKE ? "
					+ "ORDER BY qna_num DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			qnaSearchList = new ArrayList<QnaBean>();
			
			while(rs.next()) {
				
				qnaArticle = new QnaBean();
				qnaArticle.setQna_num(rs.getInt("qna_num"));
				qnaArticle.setQna_nickname(rs.getString("qna_nickname"));
				qnaArticle.setQna_write_date(rs.getString("qna_write_date").substring(0,8));
				qnaArticle.setQna_title(rs.getString("qna_title"));
				qnaArticle.setQna_content(rs.getString("qna_content"));
				qnaArticle.setQna_re_ref(rs.getInt("qna_re_ref"));
				qnaArticle.setQna_re_lev(rs.getInt("qna_re_lev"));
				qnaArticle.setQna_re_seq(rs.getInt("qna_re_seq"));
				qnaArticle.setQna_readcount(rs.getInt("qna_readcount"));
				
				qnaSearchList.add(qnaArticle);
			}
			
//				System.out.println(qnaSearchList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectSearchQnaList()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return qnaSearchList;
	}
	
	// qna 글 수정
	public int updateQnaArticle(QnaBean qna) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE qna "
					+ "SET qna_nickname=?,qna_title=?,qna_write_date=?,qna_content=? "
					+ "WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_nickname());
			pstmt.setString(2, qna.getQna_title());
			pstmt.setString(3, qna.getQna_write_date());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setInt(5, qna.getQna_num());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateQnaArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	// qna 답글 등록
	public int insertQnaReplyArticle(QnaBean qnaArticle) {
		int insertCount = 0;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		int num = 1; 
		try {
			String sql = "SELECT MAX(qna_num) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			sql = "UPDATE qna SET qna_re_seq=qna_re_seq+1 "
					+ "WHERE qna_re_ref=? AND qna_re_seq>?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, qnaArticle.getQna_re_ref()); // 참조글번호
			pstmt2.setInt(2, qnaArticle.getQna_re_seq()); // 순서번호
			pstmt2.executeUpdate();
			
			close(pstmt2);
			
			// 답글(새글) 등록 작업 처리
			sql = "INSERT INTO qna VALUES (?,?,REPLACE(now(),'-',''),?,?,?,?,?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, num); // 새 글 번호
			pstmt2.setString(2, qnaArticle.getQna_nickname());
			pstmt2.setString(3, qnaArticle.getQna_title());
			pstmt2.setString(4, qnaArticle.getQna_content());
			pstmt2.setInt(5, qnaArticle.getQna_re_ref());
			pstmt2.setInt(6, qnaArticle.getQna_re_lev() + 1);
			pstmt2.setInt(7, qnaArticle.getQna_re_seq() + 1);
			pstmt2.setInt(8, 0); 
			
			insertCount = pstmt2.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertQnaReplyArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(pstmt2);
		}
		
		return insertCount;
	}

	// qna 삭제
	public int deleteQnaArticle(int qna_num) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM qna WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - deleteQnaArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;	
	}


	// 메인페이지에서 삭제기능을 위한 패스워드 확인
	public boolean isArticleWriter(int qna_num, String qna_delete) {
	boolean isArticleWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT qna_num, member_passwd FROM qna JOIN member ON qna_nickname = member_nickname WHERE qna_num =? AND member_passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.setString(2, qna_delete);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isArticleWriter = true;
			}
			
	    } catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - isArticleWriter()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return isArticleWriter;
	}


	
	public ArrayList<MemberBean> selectMemberManagementList(int pageNum, int listLimit) {
		ArrayList<MemberBean> memberManagementList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			
			String sql = "SELECT a.member_code, a.member_num, a.member_nickname, a.member_email, b.member_service_log_status, b.member_service_log_join_date, b.member_service_log_login_date FROM member AS a JOIN member_service_log AS b LIMIT ?,?";
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
//				bean2.setMember_service_log_login_date(rs.getString("b.member_service_log_login_date").substring(0,8));
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
	
	
	


