package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberBean;
import vo.MemberBean;

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

	public int joinSuccess(MemberBean memberBean) {
        System.out.println("joinSuccess - DAO");
        int joinCount = 0;
        
        PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null, pstmt4 = null, pstmt5 = null;

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
           
           sql = "INSERT INTO member_info_detail (member_info_detail_code,member_info_detail_like_style, member_info_detail_like_brand, member_info_detail_like_category) VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1),?,?,?)";
           pstmt3 = con.prepareStatement(sql);
           pstmt3.setString(1, memberBean.getMember_info_detail_like_style());
           pstmt3.setString(2, memberBean.getMember_info_detail_like_brand());
           pstmt3.setString(3, memberBean.getMember_info_detail_like_category());
           
           sql ="INSERT INTO member_service_log VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1), '정상', REPLACE(now(),'-',''), REPLACE(now(),'-',''), REPLACE(now(),'-',''), 0, 0)";
           pstmt4 = con.prepareStatement(sql);
           
//           System.out.println(memberBean.getAgreement_name());
//           System.out.println(memberBean.getAgreement_content());
//           sql = "INSERT INTO agreement VALUES ((SELECT MAX(member_code) FROM member ORDER BY member_num), REPLACE(now(),'-',''),?,?)";
//           pstmt5 = con.prepareStatement(sql);
//           pstmt5.setString(1, memberBean.getAgreement_name());
//           pstmt5.setString(2, memberBean.getAgreement_content());
           
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

	public MemberBean isLogin(String member_id, String member_passwd) {
		System.out.println("MemberDAO isLogin");
		MemberBean isLogin = null;
		System.out.println(member_id);
		System.out.println(member_passwd);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT a.member_code, c.grade_name, a.member_nickname, d.member_service_log_status, d.member_service_log_login_date, e.reason_content FROM member AS a JOIN  member_info_detail AS b ON a.member_code=b.member_info_detail_code JOIN member_service_log AS d ON a.member_code = d.member_service_log_code JOIN reason AS e ON d.memner_service_log_status_reason = e.reason_num JOIN grade AS c ON b.member_info_detail_acc_money BETWEEN c.lowest_acc_money AND c.highest_acc_money WHERE a.member_id=? AND a.member_passwd=?"; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_passwd);
			rs = pstmt.executeQuery();
			
			System.out.println(pstmt); 
			if(rs.next()) {
				isLogin = new MemberBean();
				isLogin.setMember_code(rs.getString("a.member_code"));
				isLogin.setGrade_name(rs.getString("c.grade_name"));
				isLogin.setMember_nickname(rs.getString("a.member_nickname"));
				isLogin.setMember_service_log_status(rs.getString("d.member_service_log_status"));
				isLogin.setMember_service_log_login_date(rs.getString("d.member_service_log_login_date"));
				isLogin.setReason_content(rs.getString("e.reason_content"));
			}
		} catch (SQLException e) {

		} finally {
			close(pstmt);
			close(rs);
		}
		System.out.println("dao다 : " + isLogin);
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
				memberDetail.setMember_service_log_grade_change_date(rs.getString("d.member_service_log_grade_change_date").substring(0,8));
				memberDetail.setMember_service_log_login_date(rs.getString("d.member_service_log_login_date").substring(0,8));
				memberDetail.setMember_service_log_order_count(rs.getInt("d.member_service_log_order_count")); 
				memberDetail.setGrade_name(rs.getString("e.grade_name"));
				
			}
		} catch (SQLException e) {
			
		} finally {
			close(pstmt);
			close(rs);
		}
		System.out.println("getMemberArticle" + memberDetail);
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
//			pstmt .setString(1, memberBean.getMember_code());
//			pstmt .setString(2, memberBean.getMember_num());
//			pstmt .setString(3, memberBean.getMember_nickname());
//			pstmt .setString(4, memberBean.getMember_id());
			pstmt .setString(1, memberBean.getMember_passwd());
			pstmt .setString(2, memberBean.getMember_email());
//			pstmt .setString(7, memberBean.getMember_info_code());
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
//			pstmt .setString(13, memberBean.getMember_info_grade_code());
//			pstmt .setString(19, memberBean.getMember_info_mypage_img_name());
//			pstmt .setString(20, memberBean.getMember_info_mypage_real_img_name());
//			pstmt .setString(21, memberBean.getMember_info_detail_code());
			pstmt .setString(14, memberBean.getMember_info_detail_like_style());
			pstmt .setString(15, memberBean.getMember_info_detail_like_brand());
			pstmt .setString(16, memberBean.getMember_info_detail_like_category());
//			pstmt .setInt(25, memberBean.getMember_info_detail_point());
//			pstmt .setInt(26, memberBean.getMember_info_detail_acc_money());
//			pstmt .setString(27, memberBean.getMember_service_log_code());
//			pstmt .setString(28, memberBean.getMember_service_log_status());
//			pstmt .setString(29, memberBean.getMember_service_log_join_date());
//			pstmt .setString(30, memberBean.getMember_service_log_passwd_change_date());
//			pstmt .setString(31, memberBean.getMember_service_log_grade_change_date());
//			pstmt .setString(32, memberBean.getMember_service_log_login_date());
//			pstmt .setInt(33, memberBean.getMember_service_log_order_count());
			pstmt .setString(17, memberBean.getMember_code());
			pstmt .setString(18, memberBean.getMember_code());
			pstmt .setString(19, memberBean.getMember_code());
//			pstmt .setString(20, memberBean.getMember_code());
			
			updateCount = pstmt.executeUpdate(); 
			System.out.println("DAO업데이트 :" + updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public boolean getMemberUpdate(String member_code, String member_status) {
		boolean isMemberUpdate = false;
		
		PreparedStatement pstmt = null;
		int sucess = 0;
		System.out.println(member_status);
		try {
			if(member_status.equals("정상")) {
				String sql = "UPDATE member_service_log SET member_service_log_status=? WHERE member_service_log_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_status);
				pstmt.setString(2, member_code);
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
	
	
}
