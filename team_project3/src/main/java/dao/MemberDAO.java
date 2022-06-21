package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.BuyDTO;
import vo.LikeListBean;
import vo.MemberBean;
import vo.SellerDTO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

import static db.JdbcUtil.*;
public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {} //생성자 잠그기
	
	public static MemberDAO getInstance() { 
		return instance;
	};
	
	private Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	//회원가입
	public int joinSuccess(MemberBean memberBean) {
        int joinCount = 0;
        
        PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null, pstmt4 = null, pstmt5 = null , pstmt6 = null;

        try {
           String sql = "INSERT INTO member VALUES ((SELECT A.NUM FROM (SELECT IFNULL(MAX(CAST(member_num AS UNSIGNED)), 0) + 1 AS num FROM member) A),REPLACE(UUID(),'-',''),?,?,?,?)";
           pstmt = con.prepareStatement(sql);
           pstmt.setString(1, memberBean.getMember_nickname());
           pstmt.setString(2, memberBean.getMember_id());
           pstmt.setString(3, memberBean.getMember_passwd());
           pstmt.setString(4, memberBean.getMember_email());
           
           sql = "INSERT INTO member_info (member_info_code,member_info_gender, member_info_age) VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1),?,?)";
           pstmt2 = con.prepareStatement(sql);
           pstmt2.setString(1, memberBean.getMember_info_gender());
           pstmt2.setString(2, memberBean.getMember_info_age());
           
           sql = "INSERT INTO member_info_detail (member_info_detail_code,member_info_detail_like_style, member_info_detail_like_brand, member_info_detail_like_category, member_info_detail_point, member_info_detail_acc_money) VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1),?,?,?,0,0)";
           pstmt3 = con.prepareStatement(sql);
           pstmt3.setString(1, memberBean.getMember_info_detail_like_style());
           pstmt3.setString(2, memberBean.getMember_info_detail_like_brand());
           pstmt3.setString(3, memberBean.getMember_info_detail_like_category());
           
           sql ="INSERT INTO member_service_log VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1), '정상', REPLACE(now(),'-',''), REPLACE(now(),'-',''), REPLACE(now(),'-',''), 0, '0')";
           pstmt4 = con.prepareStatement(sql);
           
           
           joinCount = pstmt.executeUpdate();
           joinCount = pstmt2.executeUpdate();
           joinCount = pstmt3.executeUpdate();
           joinCount = pstmt4.executeUpdate();
        } catch (SQLException e) {
           
        } finally {
           close(pstmt4);
           close(pstmt3);
           close(pstmt2);
           close(pstmt);
        }
        
        return joinCount;
     }

	//로그인
	public MemberBean isLogin(String member_id, String member_passwd) {
		MemberBean isLogin = null;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null, rs2 = null;
		
		try {
			String sql = "SELECT a.auth_code, a.email FROM auth a JOIN member m ON a.email = m.member_email WHERE m.member_id=?"; //인증여부확인
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs=pstmt.executeQuery();
			if(!rs.next()) { //auth 테이블에 인증코드가 없어야 로그인 가능
				sql = "SELECT a.member_code, c.grade_name, a.member_nickname, d.member_service_log_status, d.member_service_log_login_date, e.reason_content FROM member AS a JOIN  member_info_detail AS b ON a.member_code=b.member_info_detail_code JOIN member_service_log AS d ON a.member_code = d.member_service_log_code JOIN reason AS e ON d.member_service_log_status_reason = e.reason_num JOIN grade AS c ON b.member_info_detail_acc_money BETWEEN c.lowest_acc_money AND c.highest_acc_money WHERE a.member_id=? AND a.member_passwd=?"; 
				
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, member_id);
				pstmt2.setString(2, member_passwd);
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					isLogin = new MemberBean();
					isLogin.setMember_code(rs2.getString("a.member_code"));
					isLogin.setGrade_name(rs2.getString("c.grade_name"));
					isLogin.setMember_nickname(rs2.getString("a.member_nickname"));
					isLogin.setMember_service_log_status(rs2.getString("d.member_service_log_status"));
					isLogin.setMember_service_log_login_date(rs2.getString("d.member_service_log_login_date"));
					isLogin.setReason_content(rs2.getString("e.reason_content"));
				}
			}
		} catch (SQLException e) {

		} finally {
			close(pstmt2);
			close(rs2);
			close(pstmt);
			close(rs);
		}
		return isLogin;
	}
	

	public MemberBean getMemberArticle(String member_code) {
		MemberBean memberDetail = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT a.member_code"
					+ ", a.member_num"
					+ ", a.member_nickname"
					+ ", a.member_id"
					+ ", a.member_passwd"
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
				memberDetail.setMember_passwd(rs.getString("a.member_passwd"));
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

	public int getUpdateCount(MemberBean memberBean) { //마이페이지 수정
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member AS a, member_info AS b,member_info_detail AS c"
					+ " SET a.member_passwd=?,a.member_email=?,b.member_info_name=?,b.member_info_gender=?,b.member_info_phone=?,"
					+ "b.member_info_age=?,b.member_info_post_code=?,b.member_info_address=?,b.member_info_address_detail=?,"
					+ "b.member_info_ship_post_code=?,b.member_info_ship_address=?,b.member_info_ship_address_detail=?,"
					+ "c.member_info_detail_like_style=?,c.member_info_detail_like_brand=?,c.member_info_detail_like_category=?"
					+ " WHERE a.member_code=?"
					+ " AND b.member_info_code=?"
					+ " AND c.member_info_detail_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt .setString(1, memberBean.getMember_passwd());
			pstmt .setString(2, memberBean.getMember_email());
			pstmt .setString(3, memberBean.getMember_info_name());
			pstmt .setString(4, memberBean.getMember_info_gender());
			pstmt .setString(5, memberBean.getMember_info_phone());
			pstmt .setString(6, memberBean.getMember_info_age());
			pstmt .setString(7, memberBean.getMember_info_post_code());
			pstmt .setString(8, memberBean.getMember_info_address());
			pstmt .setString(9, memberBean.getMember_info_address_detail());
			pstmt .setString(10, memberBean.getMember_info_ship_post_code());
			pstmt .setString(11, memberBean.getMember_info_ship_address());
			pstmt .setString(12, memberBean.getMember_info_ship_address_detail());
			pstmt .setString(13, memberBean.getMember_info_detail_like_style());
			pstmt .setString(14, memberBean.getMember_info_detail_like_brand());
			pstmt .setString(15, memberBean.getMember_info_detail_like_category());
			pstmt .setString(16, memberBean.getMember_code());
			pstmt .setString(17, memberBean.getMember_code());
			pstmt .setString(18, memberBean.getMember_code());
			
			updateCount = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int getUpateImg(MemberBean member) { //이미지 수정
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			String sql = "UPDATE member_info"
					+ " SET member_info_mypage_img_name=?,member_info_mypage_real_img_name=?"
					+ " WHERE member_info_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_info_mypage_img_name());
			pstmt.setString(2, member.getMember_info_mypage_real_img_name());
			pstmt.setString(3, member.getMember_code());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	// 닉네임 중복체크
	// 아이디 중복 DAO랑 하나로 합쳐서 판별해야함.
	public boolean checkNickname(String nickname) {
		boolean isDuplicate = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member WHERE member_nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isDuplicate = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isDuplicate;
	}
	
	// 아이디 중복 체크
	// 닉네임 중복 체크 DAO랑 합쳐서 판별하여 하나로 만들어야함. 
	public boolean checkId(String id) {
		boolean isDuplicate = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isDuplicate = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isDuplicate;
	}

	//아이디 찾기
	public String isFindId(String nickname, String email) {
		String isFindId = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT member_id FROM member WHERE member_nickname=? AND member_email=?"; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isFindId = rs.getString("member_id").toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return isFindId;
	}

	//비밀번호 찾기
	public boolean isFindPasswd(String id, String email, String code) {
		
		boolean isFindPasswd = false;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT member_passwd FROM member WHERE member_id=? AND member_email=?"; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sql = "UPDATE member SET member_passwd=? WHERE member_id=? AND member_email=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, code);
				pstmt2.setString(2, id);
				pstmt2.setString(3, email);
				isFindPasswd=pstmt2.execute();
				isFindPasswd = true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - insertAuthInfo()");
		} finally {
			close(pstmt2);
			close(pstmt);
			close(rs);
		}
		return isFindPasswd;
	}
	
	//회원가입시 인증코드 보내기
	public boolean insertAuthInfo(String receiver, String code) {
		boolean isSendEmail = false;
		
		PreparedStatement pstmt = null, pstmt2 = null, pstmt3=null;
		ResultSet rs = null, rs2=null; 
		try {
			String sql = "SELECT member_email FROM member WHERE member_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receiver);
			rs= pstmt.executeQuery();
			if(!rs.next()) {
				sql = "SELECT auth_code FROM auth WHERE email=? ";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, receiver);
				
				rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					sql = "UPDATE auth SET auth_code=? WHERE email=?";
					pstmt3 = con.prepareStatement(sql);
					pstmt3.setString(1, code);
					pstmt3.setString(2, receiver);
					pstmt3.executeUpdate();
					isSendEmail =true;
				}else {
					sql = "INSERT INTO auth VALUES(?,?)";
					pstmt3 = con.prepareStatement(sql);
					pstmt3.setString(1, receiver);
					pstmt3.setString(2, code);
					pstmt3.executeUpdate();	
					isSendEmail =true;
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - insertAuthInfo()");
		} finally {
			close(rs2);
			close(rs);
			close(pstmt3);
			close(pstmt2);
			close(pstmt);
		}
		return isSendEmail;
	}
	
	//회원가입시 인증과정
	public int selectAuthInfo(String email, String code) {
		System.out.println("MemberDAO - selectAuthInfo");
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT auth_code FROM auth WHERE email=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			if(rs.next() ) {
				if(code.equals(rs.getString("auth_code"))) {
					result = 1;
				}
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectAuthInfo()");
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//인증하기 완료시 auth 테이블에서 auth_code 지우기
	public boolean changeAuthStatus(String email) {
		boolean AuthStatusResult = false;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM auth WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			AuthStatusResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - changeAuthStatus()");
		} finally {
			close(pstmt);
		}
		return AuthStatusResult;
	}
	
	public int selectListCount(String member_code) { //좋아료 리스트 카운트
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT count(distinct like_list_item_num), a.sell_title, c.sell_img_real_name"
					+ " FROM like_list AS b"
					+ " JOIN sell AS a"
					+ " ON a.sell_num = b.like_list_item_num"
					+ " JOIN sell_img AS c"
					+ " ON c.sell_img_num = a.sell_num"
					+ " WHERE b.like_list_member_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}
	
	public ArrayList<SellerProductDTO> selectArticleList(int pageNum, int listLimit, String member_code) { //좋아요 리스트
		ArrayList<SellerProductDTO> articleList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT a.member_code, s.sell_title, b.sell_img_name, b.sell_img_real_name, s.sell_num, l.like_list_member_code, l.like_list_item_num"
					+ " FROM sell s"
					+ " JOIN (SELECT * FROM sell_img WHERE sell_img_num = 1) b"
					+ " ON b.sell_img_real_num = s.sell_num"
					+ " JOIN member a"
					+ " ON a.member_code = s.sell_member_code"
					+ " JOIN like_list l"
					+ " ON l.like_list_item_num = s.sell_num"
					+ " WHERE l.like_list_member_code=?"
					+ " ORDER BY s.sell_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			articleList = new ArrayList<SellerProductDTO>();
			
			while(rs.next()) {
				SellerProductDTO article = new SellerProductDTO();
				article.setSell_num(rs.getInt("sell_num"));
				article.setSell_title(rs.getString("sell_title"));
				article.setSell_img_real_name(rs.getString("sell_img_real_name"));
				
				articleList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}

	//총 구매목록 수 조회
	public int selectBuyListCount(String tableName) {
		
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

	//구매목록 담아오기 
	public ArrayList<SellerProductDTO> selectBuyList(int pageNum, int listLimit, String code) {
		ArrayList<SellerProductDTO> buyList = null;
		SellerProductDTO buy = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try { // 목록 카테고리에 필요한 값만 저장
			String sql = " SELECT s.sell_title, s.sell_size, b.buy_sell_item_date, b.buy_item_status, si.sell_img_name, si.sell_img_real_name, s.sell_num"
					+ " FROM sell s"
					+ " JOIN buy b"
					+ " ON s.sell_num = b.buy_item_num"
					+ " JOIN (SELECT * FROM sell_img WHERE sell_img_num = 1) si"
					+ " ON si.sell_img_real_num = s.sell_num"
					+ " JOIN sell_list sl"
					+ " ON sl.sell_list_num = s.sell_num"
					+ " WHERE b.buy_member_code=?"
					+ " ORDER BY buy_sell_item_date DESC LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			buyList = new ArrayList<SellerProductDTO>();
			
			while(rs.next()) {
				
				buy = new SellerProductDTO();
				buy.setSell_title(rs.getString("s.sell_title"));
				buy.setSell_size(rs.getString("s.sell_size"));
				buy.setBuy_sell_item_date(rs.getString("b.buy_sell_item_date").substring(0,8));
				buy.setBuy_item_status(rs.getString("b.buy_item_status"));
				buy.setSell_img_real_name(rs.getString("si.sell_img_real_name"));
				buy.setSell_num(rs.getInt("s.sell_num"));
				
				buyList.add(buy);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectBuyList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return buyList;
	}
	
	public int selectSellListCount(String member_code) { //판매리스트 카운트
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM sell WHERE sell_member_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	
	public ArrayList<SellerProductDTO> selectSellArticleList(int pageNum, int listLimit, String member_code) { //판매리스트 조회
		ArrayList<SellerProductDTO> sellarticleList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT a.member_code, s.sell_title, s.sell_price, b.sell_img_name, b.sell_img_real_name, s.sell_num, s.sell_category, s.sell_write_date"
					+ " FROM sell s"
					+ " JOIN (SELECT * FROM sell_img WHERE sell_img_num = 1) b"
					+ " ON b.sell_img_real_num = s.sell_num"
					+ " JOIN member a"
					+ " ON a.member_code = s.sell_member_code "
					+ " WHERE a.member_code=?"
					+ " ORDER BY s.sell_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			sellarticleList = new ArrayList<SellerProductDTO>();
			
			while(rs.next()) {
				SellerProductDTO article = new SellerProductDTO();
				article.setSell_num(rs.getInt("sell_num"));
				article.setSell_title(rs.getString("sell_title"));
				article.setSell_img_real_name(rs.getString("sell_img_real_name"));
				article.setSell_price(rs.getInt("sell_price"));
				article.setSell_category(rs.getString("sell_category"));
				article.setSell_write_date(rs.getString("sell_write_date"));
				
				sellarticleList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sellarticleList;
		
	}

	public int selectLikeSmallListCount(String tableName) {
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

	public int getMemberSecessionCount(MemberBean member) { //회원탈퇴기능
		System.out.println("DAO");
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member_service_log b"
					+ " JOIN member a"
					+ " ON b.member_service_log_code=a.member_code"
					+ " SET b.member_service_log_status='탈퇴'"
					+ " WHERE a.member_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt .setString(1, member.getMember_code());
			
			updateCount = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

}
