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
           
//           System.out.println(memberBean.getMember_info_gender());
           sql = "INSERT INTO member_info (member_info_code,member_info_gender, member_info_age,member_info_grade_code) VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1),?,?,(SELECT grade_code FROM grade WHERE grade_name='Basic'))";
           pstmt2 = con.prepareStatement(sql);
           pstmt2.setString(1, memberBean.getMember_info_gender());
           pstmt2.setString(2, memberBean.getMember_info_age());
           
           sql = "INSERT INTO member_info_detail (member_info_detail_code,member_info_detail_like_style, member_info_detail_like_brand, member_info_detail_like_category) VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1),?,?,?)";
           pstmt3 = con.prepareStatement(sql);
           pstmt3.setString(1, memberBean.getMember_info_detail_like_style());
           pstmt3.setString(2, memberBean.getMember_info_detail_like_brand());
           pstmt3.setString(3, memberBean.getMember_info_detail_like_category());
           
           sql ="INSERT INTO member_service_log VALUES ((SELECT member_code FROM member ORDER BY CAST(member_num AS SIGNED) DESC LIMIT 1) ), '정상', REPLACE(now(),'-',''), REPLACE(now(),'-',''), REPLACE(now(),'-',''), REPLACE(now(),'-',''), 0)";
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
//           joinCount = pstmt5.executeUpdate();
        } catch (SQLException e) {
           
        } finally {
//           close(pstmt5);
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
//		System.out.println(member_id);
//		System.out.println(member_passwd);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT a.member_code, c.grade_name, a.member_nickname FROM member AS a JOIN  member_info AS b ON a.member_code=b.member_info_code JOIN grade AS c ON b.member_info_grade_code=c.grade_code WHERE a.member_id=? AND a.member_passwd=?"; 
			
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
			
			}
		} catch (SQLException e) {

		} finally {
			close(pstmt);
			close(rs);
		}
		System.out.println(isLogin);
		return isLogin;
	}

	public MemberBean getMemberArticle(String member_code) {
		MemberBean memberDetail = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT a.member_code, a.member_num, a.member_nickname, a.member_id, a.member_passwd, a.member_email, b.member_info_name, b.member_info_gender, b.member_info_phone, b.member_info_age, b.member_info_post_code, b.member_info_address, b.member_info_address_detail, b.member_info_ship_post_code, b.member_info_ship_address, b.member_info_ship_address_detail, b. member_info_mypage_img_name, b.member_info_mypage_real_img_name, c.grade_name, d.member_info_detail_like_style, d.member_info_detail_like_brand, d.member_info_detail_like_category, d.member_info_detail_point, d.member_info_detail_acc_money, e.member_service_log_code, e.member_service_log_status, e.member_service_log_join_date, e.member_service_log_passwd_change_date, e.member_service_log_grade_change_date, e.member_service_log_login_date, e.member_service_log_order_count"
					+ " FROM member AS a"
					+ " JOIN member_info AS b"
					+ " ON a.member_code = b.member_info_code"
					+ " JOIN grade AS c"
					+ " ON b.member_info_grade_code  = c.grade_code"
					+ " JOIN member_info_detail AS d"
					+ " ON b.member_info_code = d.member_info_detail_code"
					+ " JOIN member_service_log AS e"
					+ " ON d.member_info_detail_code = e.member_service_log_code"
					+ " WHERE a.member_code = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDetail = new MemberBean();
				memberDetail.setMember_code(rs.getString("member_code"));
				memberDetail.setMember_num(rs.getString("member_num"));
				memberDetail.setMember_nickname(rs.getString("member_nickname"));
				memberDetail.setMember_id(rs.getString("member_id"));
				memberDetail.setMember_passwd(rs.getString("member_passwd"));
				memberDetail.setMember_email(rs.getString("member_email"));
				memberDetail.setMember_info_name(rs.getString("member_info_name"));
				memberDetail.setMember_info_gender(rs.getString("member_info_gender"));
				memberDetail.setMember_info_phone(rs.getString("member_info_phone"));
				memberDetail.setMember_info_age(rs.getString("member_info_age"));
				memberDetail.setMember_info_post_code(rs.getString("member_info_post_code"));
				memberDetail.setMember_info_address(rs.getString("member_info_address"));
				memberDetail.setMember_info_address_detail(rs.getString("member_info_address_detail"));
				memberDetail.setMember_info_ship_post_code(rs.getString("member_info_ship_post_code"));
				memberDetail.setMember_info_ship_address(rs.getString("member_info_ship_address"));
				memberDetail.setMember_info_ship_address_detail(rs.getString("member_info_ship_address_detail"));
				memberDetail.setMember_info_mypage_img_name(rs.getString("member_info_mypage_img_name"));
				memberDetail.setMember_info_mypage_real_img_name(rs.getString("member_info_mypage_real_img_name"));
				memberDetail.setMember_info_detail_like_style(rs.getString("member_info_detail_like_style"));
				memberDetail.setMember_info_detail_like_brand(rs.getString("member_info_detail_like_brand"));
				memberDetail.setMember_info_detail_like_category(rs.getString("member_info_detail_like_category"));
				memberDetail.setMember_info_detail_point(rs.getInt("member_info_detail_point")); 
				memberDetail.setMember_info_detail_acc_money(rs.getInt("member_info_detail_acc_money")); 
				memberDetail.setMember_service_log_status(rs.getString("member_service_log_status"));
				memberDetail.setMember_service_log_join_date(rs.getString("member_service_log_join_date").substring(0,8)); 
				memberDetail.setMember_service_log_passwd_change_date(rs.getString("member_service_log_passwd_change_date").substring(0,8)); 
				memberDetail.setMember_service_log_grade_change_date(rs.getString("member_service_log_grade_change_date").substring(0,8));
				memberDetail.setMember_service_log_login_date(rs.getString("member_service_log_login_date").substring(0,8));
				memberDetail.setMember_service_log_order_count(rs.getInt("member_service_log_order_count")); 
				memberDetail.setGrade_name(rs.getString("grade_name"));
				
			}
		} catch (SQLException e) {
			
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return memberDetail;
	}

	public int getUpdateCount(MemberBean memberBean) {
		System.out.println("getUpdateCount -dao");
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member AS a, member_info AS b,member_info_detail AS c, member_service_log AS d"
					+ " SET a.member_code=?,a.member_num=?,a.member_nickname=?,a.member_id=?,a.member_passwd=?,a.member_email=?,b.member_info_code=?,b.member_info_name=?,b.member_info_gender=?,b.member_info_phone=?,b.member_info_age=?,b.member_info_post_code=?,b.member_info_address=?,b.member_info_address_detail=?,b.member_info_ship_post_code=?,b.member_info_ship_address=?,b.member_info_ship_address_detail=?,b.member_info_grade_code=?,b.member_info_mypage_img_name=?,b.member_info_mypage_real_img_name=?,c.member_info_detail_code=?,c.member_info_detail_like_style=?,c.member_info_detail_like_brand=?,c.member_info_detail_like_category=?,c.member_info_detail_point=?,c.member_info_detail_acc_money=?,d.member_service_log_code=?,d.member_service_log_status=?,d.member_service_log_join_date=?,d.member_service_log_passwd_change_date=?,d.member_service_log_grade_change_date=?,d.member_service_log_login_date=?,d.member_service_log_order_count=?"
					+ " WHERE a.member_code=?"
					+ " AND b.member_info_code=?"
					+ " AND c.member_info_detail_code=?"
					+ " AND d.member_service_log_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt .setString(1, memberBean.getMember_code());
			pstmt .setString(2, memberBean.getMember_num());
			pstmt .setString(3, memberBean.getMember_nickname());
			pstmt .setString(4, memberBean.getMember_id());
			pstmt .setString(5, memberBean.getMember_passwd());
			pstmt .setString(6, memberBean.getMember_email());
			pstmt .setString(7, memberBean.getMember_info_code());
			pstmt .setString(8, memberBean.getMember_info_name());
			pstmt .setString(9, memberBean.getMember_info_gender());
			pstmt .setString(10, memberBean.getMember_info_phone());
			pstmt .setString(11, memberBean.getMember_info_age());
			pstmt .setString(12, memberBean.getMember_info_post_code());
			pstmt .setString(13, memberBean.getMember_info_address());
			pstmt .setString(14, memberBean.getMember_info_address_detail());
			pstmt .setString(15, memberBean.getMember_info_ship_post_code());
			pstmt .setString(16, memberBean.getMember_info_ship_address());
			pstmt .setString(17, memberBean.getMember_info_ship_address_detail());
			pstmt .setString(18, memberBean.getMember_info_grade_code());
			pstmt .setString(19, memberBean.getMember_info_mypage_img_name());
			pstmt .setString(20, memberBean.getMember_info_mypage_real_img_name());
			pstmt .setString(21, memberBean.getMember_info_detail_code());
			pstmt .setString(22, memberBean.getMember_info_detail_like_style());
			pstmt .setString(23, memberBean.getMember_info_detail_like_brand());
			pstmt .setString(24, memberBean.getMember_info_detail_like_category());
			pstmt .setInt(25, memberBean.getMember_info_detail_point());
			pstmt .setInt(26, memberBean.getMember_info_detail_acc_money());
			pstmt .setString(27, memberBean.getMember_service_log_code());
			pstmt .setString(28, memberBean.getMember_service_log_status());
			pstmt .setString(29, memberBean.getMember_service_log_join_date());
			pstmt .setString(30, memberBean.getMember_service_log_passwd_change_date());
			pstmt .setString(31, memberBean.getMember_service_log_grade_change_date());
			pstmt .setString(32, memberBean.getMember_service_log_login_date());
			pstmt .setInt(33, memberBean.getMember_service_log_order_count());
			pstmt .setString(34, memberBean.getMember_code());
			pstmt .setString(35, memberBean.getMember_code());
			pstmt .setString(36, memberBean.getMember_code());
			pstmt .setString(37, memberBean.getMember_code());
			
			updateCount = pstmt.executeUpdate(); 
			System.out.println("DAO업데이트 :" + updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		if(memberBean.getMember_passwd() != null) {
//			String sql = "UPDATE member SET member_passwd=?, member_email=? WHERE member_id=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(updateCount, sql);
//		}
		
//		try {
//			if(memberBean.getPass() != null) {
//				String sql = "UPDATE member SET pass=?,email1=?,email2=?,name=?,gender=?,phone=?,age=?,modify_date=now(),address_num=?,address=?,address_detail=?,"
//						+ "ship_address_num=?,ship_address=?,ship_address_detail=?,style=?,brand=?,like_item=? WHERE id=?";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, memberBean.getPass());
//				pstmt.setString(2, memberBean.getEmail1());
//				pstmt.setString(3, memberBean.getEmail2());
//				pstmt.setString(4, memberBean.getName());
//				pstmt.setString(5, memberBean.getGender());
//				pstmt.setString(6, memberBean.getPhone());
//				pstmt.setString(7, memberBean.getAge());
//				pstmt.setString(8, memberBean.getAddress_num());
//				pstmt.setString(9, memberBean.getAddress());
//				pstmt.setString(10, memberBean.getAddress_detail());
//				pstmt.setString(11, memberBean.getShip_address_num());
//				pstmt.setString(12, memberBean.getShip_address());
//				pstmt.setString(13, memberBean.getShip_address_detail());
//				pstmt.setString(14, memberBean.getStyle());
//				pstmt.setString(15, memberBean.getBrand());
//				pstmt.setString(16, memberBean.getLike_item());
//				pstmt.setString(17, memberBean.getId());
//				
//				updateCount = pstmt.executeUpdate();
//				
//			} else {
//				String sql =  "UPDATE member SET email1=?,email2=?,name=?,gender=?,phone=?,age=?,modify_date=now(),address_num=?,address=?,address_detail=?,"
//						+ "ship_address_num=?,ship_address=?,ship_address_detail=?,style=?,brand=?,like_item=? WHERE id=?";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, memberBean.getEmail1());
//				pstmt.setString(2, memberBean.getEmail2());
//				pstmt.setString(3, memberBean.getName());
//				pstmt.setString(4, memberBean.getGender());
//				pstmt.setString(5, memberBean.getPhone());
//				pstmt.setString(6, memberBean.getAge());
//				pstmt.setString(7, memberBean.getAddress_num());
//				pstmt.setString(8, memberBean.getAddress());
//				pstmt.setString(9, memberBean.getAddress_detail());
//				pstmt.setString(10, memberBean.getShip_address_num());
//				pstmt.setString(11, memberBean.getShip_address());
//				pstmt.setString(12, memberBean.getShip_address_detail());
//				pstmt.setString(13, memberBean.getStyle());
//				pstmt.setString(14, memberBean.getBrand());
//				pstmt.setString(15, memberBean.getLike_item());
//				pstmt.setString(16, memberBean.getId());
//				
//				updateCount = pstmt.executeUpdate();
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
		
		return updateCount;
	}

	public boolean getMemberUpdate(String member_code, String grade_name) {
		boolean isMemberUpdate = false;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE member_service_log SET member_service_log_status=? WHERE member_service_log_code=?";
		
		return isMemberUpdate;
	}
	
	
	
}
