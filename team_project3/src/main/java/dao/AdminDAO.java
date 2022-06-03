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
import vo.SalesList;
import vo.SellerDTO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

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
			close(rs);


			sql = "INSERT INTO notice VALUES (?,?,REPLACE(now(),'-',''),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, notice.getNotice_nickname());
			pstmt.setString(3, notice.getNotice_title());
			pstmt.setString(4, notice.getNotice_content());
			pstmt.setInt(5, 0);
			
			insertCount = pstmt.executeUpdate();
			
			
			if(!noticeImgList.isEmpty()) {
				sql = "SELECT MAX(notice_img_file_real_num) FROM notice_img_file";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					// 조회된 레코드 중 Auto_increment 컬럼 값을 num 에 저장
					num = rs.getInt(1) + 1;
					
					for(NoticeImgFileBean noticeImg: noticeImgList) {
						sql = "INSERT INTO notice_img_file VALUES (?,(SELECT MAX(notice_num) FROM notice),?,?)"; 
						pstmt = con.prepareStatement(sql); 
						
						pstmt.setInt(1,num); 
						pstmt.setString(2,noticeImg.getNotice_img_file_name()); 
						pstmt.setString(3,noticeImg.getNotice_img_file_real_name());
					
						pstmt.executeUpdate();
						
						num++;
						
					} 
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

	//총 게시물 수를 조회
	public int selectListCount(String tableName) {
//		System.out.println("AdminDAO - selectListCount()");
		
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
			
			String sql = "SELECT COUNT(notice_num) FROM "+ tableName + " WHERE " + searchType + " LIKE ?";
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

	// 공지사항 등록된 사진이 있는지 확인
	public ArrayList<NoticeImgFileBean> selectImgExist(int notice_num) {
		ArrayList<NoticeImgFileBean> isNoticeImgExist = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT f.notice_img_file_name, f.notice_img_file_real_name "
					+ "FROM notice "
					+ "JOIN notice_img_file f "
					+ "on f.notice_img_file_num = notice_num "
					+ "WHERE notice_img_file_num =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
	
			rs = pstmt.executeQuery();
			
			isNoticeImgExist = new ArrayList<NoticeImgFileBean>();
			
			while(rs.next()) {
				NoticeImgFileBean noticeImg = new NoticeImgFileBean();
				noticeImg.setNotice_img_file_name(rs.getString("f.notice_img_file_name"));
				noticeImg.setNotice_img_file_real_name(rs.getString("f.notice_img_file_real_name"));
				
				isNoticeImgExist.add(noticeImg);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectImgExist()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return isNoticeImgExist;
	}
	
	// 공지사항 수정
	public int updateNoticeArticle(int notice_num, NoticeBean notice, ArrayList<NoticeImgFileBean> noticeImgList) {
//		System.out.println("AdminDAO - updateNoticeArticle()");
		int isNoticeModifySuccess = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try { //공지사항 글부분 
			String sql = "UPDATE notice SET notice_title=?, notice_content = ? WHERE notice_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getNotice_title());
			pstmt.setString(2, notice.getNotice_content());
			pstmt.setInt(3, notice_num);
			
			isNoticeModifySuccess = pstmt.executeUpdate();
			
			close(pstmt);
			
			sql = "DELETE FROM notice_img_file WHERE notice_img_file_num = ?"; // 첨부파일 삭제
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			
			pstmt.executeUpdate();
			
			
			if(!noticeImgList.isEmpty()) { // 첨부파일 리스트가 비어있지 않으면
				
				int num = 0;
				sql = "SELECT MAX(notice_img_file_real_num) FROM notice_img_file";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					num = rs.getInt(1) + 1;
					
					for(NoticeImgFileBean noticeImg: noticeImgList) { 
						
						sql = "INSERT INTO notice_img_file VALUES (?,?,?,?)"; 
						pstmt = con.prepareStatement(sql); 
						
						pstmt.setInt(1,num); 
						pstmt.setInt(2,notice_num); 
						pstmt.setString(3,noticeImg.getNotice_img_file_name()); 
						pstmt.setString(4,noticeImg.getNotice_img_file_real_name());
						
						pstmt.executeUpdate();
						
						num++;
						
					} 
				}
			} // if문 끝_!noticeImgList.isEmpty()
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - updateNoticeArticle()");
		} finally {
			close(pstmt);
		}
		return isNoticeModifySuccess;
	}
	
	

	//공지사항 삭제
	public int deleteNoticeArticle(int notice_num) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM notice WHERE notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - deleteNoticeArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;	
	}
	
	
	
	
	// 이벤트 글쓰기
	public int insertEventArticle(EventBean event,  ArrayList<EventImgFileBean> eventImgList) {
//			System.out.println("AdminDAO - insertEventArticle()");

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
				close(rs);

				sql = "INSERT INTO event VALUES (?,?,REPLACE(now(),'-',''),?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, event.getEvent_nickname());
				pstmt.setString(3, event.getEvent_title());
				pstmt.setString(4, event.getEvent_content());
				pstmt.setInt(5, 0);
				
				insertCount = pstmt.executeUpdate();
				
				
				if(!eventImgList.isEmpty()) {
					sql = "SELECT MAX(event_img_file_real_num) FROM event_img_file";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						// 조회된 레코드 중 Auto_increment 컬럼 값을 num 에 저장
						num = rs.getInt(1) + 1;
						
						for(EventImgFileBean eventImg: eventImgList) {
							sql = "INSERT INTO event_img_file VALUES (?,(SELECT MAX(event_num) FROM event),?,?)"; 
							
							pstmt = con.prepareStatement(sql); 
							pstmt.setInt(1,num); 
							pstmt.setString(2,eventImg.getEvent_img_file_name()); 
							pstmt.setString(3,eventImg.getEvent_img_file_real_name());
							
							pstmt.executeUpdate();
							
							num++;
						}// for문 끝
					} 
				} //if문 끝_!eventImgList.isEmpty()
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - insertEventArticle()");
			} finally {
				close(pstmt);
				close(rs);
			}
			return insertCount;
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
	
	//이벤트에 등록된 사진 가져오기
	public ArrayList<EventImgFileBean> selectEventImgExist(int event_num) {
		ArrayList<EventImgFileBean> isEventImgExist = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT f.event_img_file_name, f.event_img_file_real_name "
					+ "FROM event "
					+ "JOIN event_img_file f "
					+ "on f.event_img_file_num = event_num "
					+ "WHERE event_img_file_num =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, event_num);
	
			rs = pstmt.executeQuery();
			
			isEventImgExist = new ArrayList<EventImgFileBean>();
			
			while(rs.next()) {
				EventImgFileBean eventImg = new EventImgFileBean();
				eventImg.setEvent_img_file_name(rs.getString("f.event_img_file_name"));
				eventImg.setEvent_img_file_real_name(rs.getString("f.event_img_file_real_name"));
				
				isEventImgExist.add(eventImg);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectEventImgExist()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return isEventImgExist;
	}
	
	//이벤트 수정
	public int updateEventArticle(int event_num,EventBean event, ArrayList<EventImgFileBean> eventImgList) {
//		System.out.println("AdminDAO - updateEventArticle()");
		int isEventModifySuccess = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try { //공지사항 글부분 
			String sql = "UPDATE event SET event_title=?, event_content = ? WHERE event_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, event.getEvent_title());
			pstmt.setString(2, event.getEvent_content());
			pstmt.setInt(3, event_num);
			
			isEventModifySuccess = pstmt.executeUpdate();
			
			close(pstmt);
			
			sql = "DELETE FROM event_img_file WHERE event_img_file_num = ?"; // 첨부파일 삭제
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, event_num);
			
			pstmt.executeUpdate();
			
			
			if(!eventImgList.isEmpty()) { // 첨부파일 리스트가 비어있지 않으면
				
				int num = 0;
				sql = "SELECT MAX(event_img_file_real_num) FROM event_img_file";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					num = rs.getInt(1) + 1;
					
					for(EventImgFileBean eventImg: eventImgList) { 
						
						sql = "INSERT INTO event_img_file VALUES (?,?,?,?)"; 
						pstmt = con.prepareStatement(sql); 
						
						pstmt.setInt(1,num); 
						pstmt.setInt(2,event_num); 
						pstmt.setString(3,eventImg.getEvent_img_file_name()); 
						pstmt.setString(4,eventImg.getEvent_img_file_real_name());
						
						pstmt.executeUpdate();
						
						num++;
						
					} 
				}
			} // if문 끝_!eventImgList.isEmpty()
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - updateEventArticle()");
		} finally {
			close(pstmt);
		}
		return isEventModifySuccess;
	}
	
	
	
	
	//이벤트 삭제
	public int deleteEventArticle(int event_num) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM event WHERE event_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, event_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - deleteEventArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;	
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
	public boolean isArticleWriter(int qna_num, String qna_confirm) {
	boolean isArticleWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT qna_num, member_passwd FROM qna JOIN member ON qna_nickname = member_nickname WHERE qna_num =? AND member_passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.setString(2, qna_confirm);
			
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

	// MemberManagement 페이지 리스트 출력
	public ArrayList<MemberBean> selectMemberManagementList(int pageNum, int listLimit) {
		ArrayList<MemberBean> memberManagementList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			
			String sql = "SELECT a.member_code, a.member_num, a.member_nickname, a.member_email, b.member_service_log_status, b.member_service_log_join_date FROM member AS a JOIN member_service_log AS b ON a.member_code = b.member_service_log_code ORDER BY CAST(a.member_num AS SIGNED) ASC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			memberManagementList = new ArrayList<MemberBean>();
			
			while(rs.next()) {
				
				MemberBean bean = new MemberBean();
				bean.setMember_code(rs.getString("a.member_code"));
				bean.setMember_num(rs.getString("a.member_num"));
				bean.setMember_nickname(rs.getString("a.member_nickname"));
				bean.setMember_email(rs.getString("a.member_email"));
				bean.setMember_service_log_status(rs.getString("b.member_service_log_status"));
				bean.setMember_service_log_join_date(rs.getString("b.member_service_log_join_date").substring(0, 8));
				memberManagementList.add(bean);
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

	// 정상, 정지, 탈퇴, 검색 기능으로 이동할 페이지 리스트 출력
	public ArrayList<MemberBean> selectClassificationList(int pageNum, int listLimit, String value) {
		ArrayList<MemberBean> classificationList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			if(value.equals("0")) {
				String sql = "SELECT DISTINCT a.member_code, a.member_nickname, b.member_service_log_status, c.member_info_detail_acc_money, d.grade_name FROM member AS a JOIN member_service_log AS b ON a.member_code = b.member_service_log_code JOIN member_info_detail AS c ON a.member_code = c.member_info_detail_code JOIN grade AS d ON c.member_info_detail_acc_money BETWEEN d.lowest_acc_money AND d.highest_acc_money WHERE c.member_info_detail_acc_money >= 100001 AND c.member_info_detail_acc_money <= 999999999 ORDER BY c.member_info_detail_acc_money LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				classificationList = new ArrayList<MemberBean>();
				
				while(rs.next()) {
					MemberBean bean = new MemberBean();
					bean.setMember_code(rs.getString("a.member_code"));
					bean.setMember_nickname(rs.getString("a.member_nickname"));
					bean.setMember_service_log_status(rs.getString("b.member_service_log_status"));
					bean.setMember_info_detail_acc_money(rs.getInt("c.member_info_detail_acc_money"));
					bean.setGrade_name(rs.getString("d.grade_name"));
					classificationList.add(bean);
				}
				
			} else if(value.equals("1")) {
				String sql = "SELECT a.member_code, a.member_nickname, b.member_service_log_status, c.member_info_detail_acc_money, d.grade_name FROM member AS a JOIN member_service_log AS b ON a.member_code = b.member_service_log_code JOIN member_info_detail AS c ON a.member_code = c.member_info_detail_code JOIN grade AS d ON c.member_info_detail_acc_money BETWEEN d.lowest_acc_money AND d.highest_acc_money WHERE b.member_service_log_status='정상' ORDER BY a.member_num ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				classificationList = new ArrayList<MemberBean>();
				
				while(rs.next()) {
					MemberBean bean = new MemberBean();
					bean.setMember_code(rs.getString("a.member_code"));
					bean.setMember_nickname(rs.getString("a.member_nickname"));
					bean.setMember_service_log_status(rs.getString("b.member_service_log_status"));
					bean.setMember_info_detail_acc_money(rs.getInt("c.member_info_detail_acc_money"));
					bean.setGrade_name(rs.getString("d.grade_name"));
					classificationList.add(bean);
				}
				
			} else if(value.equals("2")) {
				String sql = "SELECT a.member_code, a.member_nickname, b.member_service_log_status, c.member_info_detail_acc_money, d.grade_name FROM member AS a JOIN member_service_log AS b ON a.member_code = b.member_service_log_code JOIN member_info_detail AS c ON a.member_code = c.member_info_detail_code JOIN grade AS d ON c.member_info_detail_acc_money BETWEEN d.lowest_acc_money AND d.highest_acc_money WHERE b.member_service_log_status='정지' ORDER BY a.member_num ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				classificationList = new ArrayList<MemberBean>();
				
				while(rs.next()) {
					MemberBean bean = new MemberBean();
					bean.setMember_code(rs.getString("a.member_code"));
					bean.setMember_nickname(rs.getString("a.member_nickname"));
					bean.setMember_service_log_status(rs.getString("b.member_service_log_status"));
					bean.setMember_info_detail_acc_money(rs.getInt("c.member_info_detail_acc_money"));
					bean.setGrade_name(rs.getString("d.grade_name"));
					classificationList.add(bean);
				}
				
			} else if(value.equals("3")) {
				String sql = "SELECT a.member_code, a.member_nickname, b.member_service_log_status, c.member_info_detail_acc_money, d.grade_name FROM member AS a JOIN member_service_log AS b ON a.member_code = b.member_service_log_code JOIN member_info_detail AS c ON a.member_code = c.member_info_detail_code JOIN grade AS d ON c.member_info_detail_acc_money BETWEEN d.lowest_acc_money AND d.highest_acc_money WHERE b.member_service_log_status='탈퇴' ORDER BY a.member_num ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				classificationList = new ArrayList<MemberBean>();
				
				while(rs.next()) {
					MemberBean bean = new MemberBean();
					bean.setMember_code(rs.getString("a.member_code"));
					bean.setMember_nickname(rs.getString("a.member_nickname"));
					bean.setMember_service_log_status(rs.getString("b.member_service_log_status"));
					bean.setMember_info_detail_acc_money(rs.getInt("c.member_info_detail_acc_money"));
					bean.setGrade_name(rs.getString("d.grade_name"));
					classificationList.add(bean);
				}
				
			} else {
				String sql = "SELECT a.member_code, a.member_nickname, b.member_service_log_status, c.member_info_detail_acc_money, d.grade_name FROM member AS a JOIN member_service_log AS b ON a.member_code = b.member_service_log_code JOIN member_info_detail AS c ON a.member_code = c.member_info_detail_code JOIN grade AS d ON c.member_info_detail_acc_money BETWEEN d.lowest_acc_money AND d.highest_acc_money WHERE a.member_nickname LIKE ? ORDER BY a.member_num ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + value + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				
				rs = pstmt.executeQuery();
				
				classificationList = new ArrayList<MemberBean>();
				
				while(rs.next()) {
					MemberBean bean = new MemberBean();
					bean.setMember_code(rs.getString("a.member_code"));
					bean.setMember_nickname(rs.getString("a.member_nickname"));
					bean.setMember_service_log_status(rs.getString("b.member_service_log_status"));
					bean.setMember_info_detail_acc_money(rs.getInt("c.member_info_detail_acc_money"));
					bean.setGrade_name(rs.getString("d.grade_name"));
					classificationList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return classificationList;
	}

	
	
	
	// 총 검수목록 
	public ArrayList<SellerDTO> selectConfirmList(int pageNum, int listLimit) {
		ArrayList<SellerDTO> productConfirmList  = null;
		SellerDTO confirm = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try { // 목록 카테고리에 필요한 값만 저장
			String sql = "SELECT  a.sell_num, a.sell_category,  a.sell_title, a.sell_brand, a.sell_write_date,"
					+ "c.sell_list_item_status,c.sell_list_approve_date "
					+ "FROM sell AS a JOIN sell_list AS c ON a.sell_num = c.sell_list_num "
					+ "ORDER BY a.sell_write_date DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			productConfirmList = new ArrayList<SellerDTO>();
			
			while(rs.next()) {
				
				confirm = new SellerDTO();
				confirm.setSell_num(rs.getInt("sell_num"));
				confirm.setSell_category(rs.getString("sell_category"));
				confirm.setSell_title(rs.getString("sell_title"));
				confirm.setSell_brand(rs.getString("sell_brand"));
				confirm.setSell_write_date(rs.getString("sell_write_date").substring(0,8));
				confirm.setSell_list_item_status(rs.getString("sell_list_item_status"));
				
				if(rs.getString("sell_list_approve_date") !=null) { //값이 없을 때 .substring(0,8)로 인해 오류발생
					confirm.setSell_list_approve_date(rs.getString("sell_list_approve_date").substring(0,8));
				}
				productConfirmList.add(confirm);
				
			}
			
//			System.out.println(productConfirmList);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectConfirmList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return productConfirmList;
		
	}
	
	//검수현황 카레코리 - 검색어에 해당하는 게시물 수
	public int selectConfirmSearchListCount(String tableName, String search, String searchType) {
		int listCount =  0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(sell_num) FROM "+ tableName+ " WHERE " + searchType + " LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectConfirmSearchListCount()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}

	//검수현황 카테고리 - 검색어에 해당하는 게시물 
	public ArrayList<SellerDTO> selectConfirmSearchList(int pageNum, int listLimit, String search, String searchType) {
		ArrayList<SellerDTO> productConfirmSearch =null;
		SellerDTO confirm = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			int startRow = (pageNum - 1) * listLimit;
			
			String sql = "SELECT  a.sell_num, a.sell_category, a.sell_title, a.sell_brand, a.sell_write_date, c.sell_list_item_status,c.sell_list_approve_date "
					+ "FROM sell AS a JOIN sell_list AS c ON a.sell_num = c.sell_list_num "
					+ "WHERE "+ searchType + " LIKE ? "
					+ "ORDER BY sell_num DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			productConfirmSearch = new ArrayList<SellerDTO>();
			
			while(rs.next()) {
				confirm = new SellerDTO();
				
				confirm.setSell_num(rs.getInt("sell_num"));
				confirm.setSell_category(rs.getString("sell_category"));
				confirm.setSell_title(rs.getString("sell_title"));
				confirm.setSell_brand(rs.getString("sell_brand"));
				confirm.setSell_write_date(rs.getString("sell_write_date").substring(0,8));
				confirm.setSell_list_item_status(rs.getString("sell_list_item_status"));
				if(rs.getString("sell_list_approve_date") !=null) { //값이 없을 때 .substring(0,8)로 인해 오류발생
					confirm.setSell_list_approve_date(rs.getString("sell_list_approve_date").substring(0,8));
				}
				
				productConfirmSearch.add(confirm);
			}
			
//				System.out.println(productConfirmSearch);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectConfirmSearchList()");
		} finally {
			close(pstmt);
			close(rs);
		}
		return productConfirmSearch;
	}
	
	//검수현황 상세정보 - 글정보
	public SellerDTO selectProductConfirmDetail(int sell_num) {

		SellerDTO confirm = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT  a.sell_num, a.sell_member_code,a.sell_title, a.sell_category, a.sell_category_detail, "
					+ "a.sell_content,a.sell_price, a.sell_color, a.sell_size, a.sell_brand, a.sell_write_date,"
					+ "c.sell_list_item_status,c.sell_list_approve_date, c.sell_list_approve_nickname "
					+ "FROM sell AS a JOIN sell_list AS c ON a.sell_num = c.sell_list_num "
					+ "WHERE a.sell_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				confirm = new SellerDTO();
				confirm.setSell_num(rs.getInt("sell_num"));
				confirm.setSell_member_code(rs.getString("sell_member_code"));
				confirm.setSell_title(rs.getString("sell_title"));
				confirm.setSell_category(rs.getString("sell_category"));
				confirm.setSell_category_detail(rs.getString("sell_category_detail"));
				confirm.setSell_content(rs.getString("sell_content"));
				confirm.setSell_price(rs.getShort("sell_price"));
				confirm.setSell_color(rs.getString("sell_color"));
				confirm.setSell_size(rs.getString("sell_size"));
				confirm.setSell_brand(rs.getString("sell_brand"));
				confirm.setSell_write_date(rs.getString("sell_write_date").substring(0,8));
				confirm.setSell_list_item_status(rs.getString("sell_list_item_status"));
				if(rs.getString("sell_list_approve_date") !=null) { //값이 없을 때 .substring(0,8)로 인해 오류발생
					
					confirm.setSell_list_approve_date(rs.getString("sell_list_approve_date").substring(0,8));
				}
				
				confirm.setSell_list_approve_nickname(rs.getString("sell_list_approve_nickname"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectProductConfirmDetail()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return confirm;
	}

	//검수현황 상세정보 - 이미지 정보
	public ArrayList<SellerimgDTO> getConfirmImg(int sell_num) {
		ArrayList<SellerimgDTO> confirmImgFileList = new ArrayList<SellerimgDTO>();
		
		SellerimgDTO confirmImg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			
			
			
			String sql ="SELECT b.sell_img_name, b.sell_img_real_name "
					+ "FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num WHERE b.sell_img_real_num=?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				confirmImg = new SellerimgDTO();
				confirmImg.setSell_img_name(rs.getString("b.sell_img_name"));
				confirmImg.setSell_img_real_name(rs.getString("b.sell_img_real_name"));
				
				confirmImgFileList.add(confirmImg);
			} 
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - getConfirmImg()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return confirmImgFileList;
		
		
	}

	
	//검수상태 변경하기
	public int updateConfirm(SellerProductDTO confirm) {
	
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			
			String status = confirm.getSell_list_item_status();
			String sql = "";
			
			if(status.equals("검수완료")) {
				 sql="UPDATE sell_list SET sell_list_item_status = '판매중' ";
			} else {
				 sql="UPDATE sell_list SET sell_list_item_status = '검수반려' ";
			}
			
				sql += ", sell_list_approve_date = REPLACE(now(),'-',''),"
					  + " sell_list_approve_nickname = ?"
					  + " WHERE sell_list_num = (SELECT sell_num FROM sell WHERE sell_num = ?)"; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, confirm.getSell_list_approve_nickname());
			pstmt.setInt(2, confirm.getSell_num());
			
//			System.out.println(sql);
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateConfirm()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
		
	}
	
	// 검수현황 타입별로 게시물 가져오기
	public ArrayList<SellerDTO> selectConfirmType(int pageNum, int listLimit, String column) {
		ArrayList<SellerDTO> productConfirmList  = null;
		SellerDTO confirm = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try { // 목록 카테고리에 필요한 값만 저장
			String sql = "SELECT  a.sell_num, a.sell_category,  a.sell_title, a.sell_brand, a.sell_write_date,"
					+ "c.sell_list_item_status,c.sell_list_approve_date "
					+ "FROM sell AS a JOIN sell_list AS c ON a.sell_num = c.sell_list_num "
					+ "WHERE sell_list_item_status=?"
					+ "ORDER BY a.sell_write_date DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, column);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			productConfirmList = new ArrayList<SellerDTO>();
			
			while(rs.next()) {
				confirm = new SellerDTO();
				confirm.setSell_num(rs.getInt("sell_num"));
				confirm.setSell_category(rs.getString("sell_category"));
				confirm.setSell_title(rs.getString("sell_title"));
				confirm.setSell_brand(rs.getString("sell_brand"));
				confirm.setSell_write_date(rs.getString("sell_write_date").substring(0,8));
				confirm.setSell_list_item_status(rs.getString("sell_list_item_status"));
				
				if(rs.getString("sell_list_approve_date") !=null) { //값이 없을 때 .substring(0,8)로 인해 오류발생
					confirm.setSell_list_approve_date(rs.getString("sell_list_approve_date").substring(0,8));
				}
				productConfirmList.add(confirm);
			}
//			System.out.println(productConfirmList);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectConfirmType()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return productConfirmList;
	}

	//검수현황 타입별 게시물 수 
	public SellerDTO getListCountType() {
		
		SellerDTO CountType = new SellerDTO();
		String[] types = {"검수중", "검수완료", "검수반려","판매완료"};
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			for(String type : types) {
			String sql = "SELECT COUNT(*) FROM sell_list WHERE sell_list_item_status=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				switch (type) {
				case "검수반려":
					CountType.setCancel(rs.getInt(1));
					break;
				case "검수완료":
					CountType.setCompletion(rs.getInt(1));
					break;
				case "검수중":
					CountType.setProgress(rs.getInt(1));
					break;
				case "판매완료":
					CountType.setSale(rs.getInt(1));
					break;
				default:
					break;
					}
				}
			
			}//for문 끝
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - getListCountType()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return CountType;
	}

	
	//검수현황 페이징처리
	public int selectListTypeCount(String tableName, String column) {
//		System.out.println("AdminDAO - selectListTypeCount()");
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(sell_list_item_status) FROM "+tableName + " WHERE sell_list_item_status=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, column);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListTypeCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	// 차트 그리기 위한 select 구문
	public ArrayList<SalesList> selectSalesChartList(String[] month) {
		ArrayList<SalesList> salesChartList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT sell_price, sell_fee, buy_price, buy_fee, ROUND((sell_fee + buy_fee)*3/5) AS net_income"
					+ " FROM ("
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ "	FROM sell"
					+ "	WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ "	FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ "	FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ " SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ "	FROM sell"
					+ "	WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ " SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ " SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ "	WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ "	WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ "	FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ "	UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(SUM(sell_price)*10, 0) AS sell_price"
					+ ", IFNULL(ROUND((SUM(sell_price)*10)*(2/10)), 0) AS sell_fee"
					+ ", ? AS num"
					+ " FROM sell"
					+ " WHERE SUBSTRING(sell_write_date, 5, 2) = ?"
					+ ") A, ("
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ " SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ "	WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ "	WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ " UNION ALL"
					+ "	SELECT IFNULL(ROUND(SUM(buy_price)/1000), 0) AS buy_price"
					+ ", IFNULL(ROUND((ROUND(SUM(buy_price)/1000))*(2/10)), 0) AS buy_fee"
					+ ", ? AS num"
					+ " FROM buy"
					+ " WHERE SUBSTRING(buy_sell_item_date, 5, 2) = ?"
					+ ") B"
					+ " WHERE A.num = B.num";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, month[0]);
			pstmt.setString(2, month[0]);
			pstmt.setString(3, month[1]);
			pstmt.setString(4, month[1]);
			pstmt.setString(5, month[2]);
			pstmt.setString(6, month[2]);
			pstmt.setString(7, month[3]);
			pstmt.setString(8, month[3]);
			pstmt.setString(9, month[4]);
			pstmt.setString(10, month[4]);
			pstmt.setString(11, month[5]);
			pstmt.setString(12, month[5]);
			pstmt.setString(13, month[6]);
			pstmt.setString(14, month[6]);
			pstmt.setString(15, month[7]);
			pstmt.setString(16, month[7]);
			pstmt.setString(17, month[8]);
			pstmt.setString(18, month[8]);
			pstmt.setString(19, month[9]);
			pstmt.setString(20, month[9]);
			pstmt.setString(21, month[10]);
			pstmt.setString(22, month[10]);
			pstmt.setString(23, month[11]);
			pstmt.setString(24, month[11]);
			pstmt.setString(25, month[0]);
			pstmt.setString(26, month[0]);
			pstmt.setString(27, month[1]);
			pstmt.setString(28, month[1]);
			pstmt.setString(29, month[2]);
			pstmt.setString(30, month[2]);
			pstmt.setString(31, month[3]);
			pstmt.setString(32, month[3]);
			pstmt.setString(33, month[4]);
			pstmt.setString(34, month[4]);
			pstmt.setString(35, month[5]);
			pstmt.setString(36, month[5]);
			pstmt.setString(37, month[6]);
			pstmt.setString(38, month[6]);
			pstmt.setString(39, month[7]);
			pstmt.setString(40, month[7]);
			pstmt.setString(41, month[8]);
			pstmt.setString(42, month[8]);
			pstmt.setString(43, month[9]);
			pstmt.setString(44, month[9]);
			pstmt.setString(45, month[10]);
			pstmt.setString(46, month[10]);
			pstmt.setString(47, month[11]);
			pstmt.setString(48, month[11]);
			
			rs = pstmt.executeQuery();
			
			salesChartList = new ArrayList<SalesList>();
			
			int i = 0;
			while(rs.next()) {
				SalesList salesList = new SalesList();
				salesList.setMonth(month[i]);
				salesList.setSell_total_money(rs.getInt("sell_price"));
				salesList.setSell_fee(rs.getInt("sell_fee"));
				salesList.setBuy_total_money(rs.getInt("buy_price"));
				salesList.setBuy_fee(rs.getInt("buy_fee"));
				salesList.setNet_income(rs.getInt("net_income"));
				i++;
				salesChartList.add(salesList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return salesChartList;
	}

	// Member 상세정보를 위한 select 구문
	public MemberBean getMemberArticle(String member_code) {
		MemberBean memberDetail = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT a.member_code"
					+ ", a.member_num"
					+ ", a.member_nickname"
					+ ", a.member_id"
					+ ", a.member_email"
					+ ", b.member_info_name"
					+ ", b.member_info_gender"
					+ ", b.member_info_phone"
					+ ", b.member_info_age"
					+ ", b.member_info_post_code"
					+ ", b.member_info_address"
					+ ", b.member_info_address_detail"
					+ ", b.member_info_ship_post_code"
					+ ", b.member_info_ship_address"
					+ ", b.member_info_ship_address_detail"
					+ ", b.member_info_mypage_img_name"
					+ ", b.member_info_mypage_real_img_name"
					+ ", c.member_info_detail_like_style"
					+ ", c.member_info_detail_like_brand"
					+ ", c.member_info_detail_like_category"
					+ ", c.member_info_detail_point"
					+ ", c.member_info_detail_acc_money"
					+ ", d.member_service_log_status"
					+ ", d.member_service_log_join_date"
					+ ", d.member_service_log_passwd_change_date"
					+ ", d.member_service_log_login_date"
					+ ", d.member_service_log_order_count"
					+ ", d.member_service_log_status_reason"
					+ ", e.grade_name "
					+ "FROM member AS a "
					+ "JOIN member_info AS b "
					+ "ON a.member_code = b.member_info_code "
					+ "JOIN member_info_detail AS c "
					+ "ON a.member_code = c.member_info_detail_code "
					+ "JOIN member_service_log AS d "
					+ "ON a.member_code = d.member_service_log_code "
					+ "JOIN grade AS e "
					+ "ON c.member_info_detail_acc_money BETWEEN e.lowest_acc_money AND e.highest_acc_money "
					+ "WHERE a.member_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDetail = new MemberBean();
				memberDetail.setMember_code(rs.getString("a.member_code"));
				memberDetail.setMember_num(rs.getString("a.member_num"));
				memberDetail.setMember_nickname(rs.getString("a.member_nickname"));
				memberDetail.setMember_id(rs.getString("a.member_id"));
				memberDetail.setMember_email(rs.getString("a.member_email"));
				memberDetail.setMember_info_name(rs.getString("b.member_info_name"));
				memberDetail.setMember_info_gender(rs.getString("b.member_info_gender"));
				memberDetail.setMember_info_phone(rs.getString("b.member_info_phone"));
				memberDetail.setMember_info_age(rs.getString("b.member_info_age"));
				memberDetail.setMember_info_post_code(rs.getString("b.member_info_post_code"));
				memberDetail.setMember_info_address(rs.getString("b.member_info_address"));
				memberDetail.setMember_info_address_detail(rs.getString("b.member_info_address_detail"));
				memberDetail.setMember_info_ship_post_code(rs.getString("b.member_info_ship_post_code"));
				memberDetail.setMember_info_ship_address(rs.getString("b.member_info_ship_address"));
				memberDetail.setMember_info_ship_address_detail(rs.getString("b.member_info_ship_address_detail"));
				memberDetail.setMember_info_mypage_img_name(rs.getString("b.member_info_mypage_img_name"));
				memberDetail.setMember_info_mypage_real_img_name(rs.getString("b.member_info_mypage_real_img_name"));
				memberDetail.setMember_info_detail_like_style(rs.getString("c.member_info_detail_like_style"));
				memberDetail.setMember_info_detail_like_brand(rs.getString("c.member_info_detail_like_brand"));
				memberDetail.setMember_info_detail_like_category(rs.getString("c.member_info_detail_like_category"));
				memberDetail.setMember_info_detail_point(rs.getInt("c.member_info_detail_point")); 
				memberDetail.setMember_info_detail_acc_money(rs.getInt("c.member_info_detail_acc_money")); 
				memberDetail.setMember_service_log_status(rs.getString("d.member_service_log_status"));
				memberDetail.setMember_service_log_join_date(rs.getString("d.member_service_log_join_date").substring(0,8)); 
				memberDetail.setMember_service_log_passwd_change_date(rs.getString("d.member_service_log_passwd_change_date").substring(0,8)); 
				memberDetail.setMember_service_log_login_date(rs.getString("d.member_service_log_login_date").substring(0,8));
				memberDetail.setMember_service_log_order_count(rs.getInt("d.member_service_log_order_count"));
				memberDetail.setReason_num(rs.getString("d.member_service_log_status_reason"));
				memberDetail.setGrade_name(rs.getString("e.grade_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return memberDetail;
	}

	public boolean getMemberUpdate(String member_code, String member_status, String reason) {
		boolean isMemberUpdate = false;
		
		PreparedStatement pstmt = null;
		int sucess = 0;
		
		try {
			if(member_status.equals("정상")) {
				String sql = "UPDATE member_service_log SET member_service_log_status=?,member_service_log_status_reason=0 WHERE member_service_log_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_status);
				pstmt.setString(2, member_code);
				sucess = pstmt.executeUpdate();
			} else if(member_status.equals("정지")) {
				String sql = "UPDATE member_service_log SET member_service_log_status=?,member_service_log_login_date=REPLACE(now(),'-',''),member_service_log_status_reason=? WHERE member_service_log_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_status);
				pstmt.setString(2, reason);
				pstmt.setString(3, member_code);
				sucess = pstmt.executeUpdate();
			} else {
				String sql = "UPDATE member_service_log SET member_service_log_status=?,member_service_log_login_date=REPLACE(now(),'-','') WHERE member_service_log_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_status);
				pstmt.setString(2, member_code);
				sucess = pstmt.executeUpdate();
			}
			
			if(sucess > 0) {
				isMemberUpdate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return isMemberUpdate;
	}

	public MemberBean getMemberStatus() {
		MemberBean bean = null;
		
		int top_level = 0;
		int nomal = 0;
		int suspension = 0;
		int withdrawal = 0;
		String[] status = {"정상", "정지", "탈퇴"};
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null, rs2 = null;
		
		try {
			for(String str : status) {
				String sql = "SELECT COUNT(*) FROM member_service_log WHERE member_service_log_status=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, str);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					switch (str) {
					case "정상":
						nomal = rs.getInt(1);
						break;
					case "정지":
						suspension = rs.getInt(1);
						break;
					case "탈퇴":
						withdrawal = rs.getInt(1);
						break;
					default:
						break;
					}
				}
			}
			
			String sql = "SELECT COUNT(*) FROM member_info_detail WHERE member_info_detail_acc_money >= 100001 AND member_info_detail_acc_money <= 999999999";
			pstmt2 = con.prepareStatement(sql);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				top_level = rs2.getInt(1);
			}
			
			bean = new MemberBean();
			bean.setTop_level(top_level);
			bean.setNomal(nomal);
			bean.setSuspension(suspension);
			bean.setWithdrawal(withdrawal);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(rs2);
			close(pstmt);
			close(pstmt2);
		}
		
		return bean;
	}

	// 탈퇴한 회원 삭제
	public int deleteMemberArticle(String member_code) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM member WHERE member_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - deleteNoticeArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;	
	}
}	
	
	
	


