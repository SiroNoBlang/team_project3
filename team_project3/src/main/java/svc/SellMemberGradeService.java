package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.MemberBean;

public class SellMemberGradeService {

	public ArrayList<MemberBean> MemberGrade() { //grade 테이블의 모든 컬럼의 정보를 가져옴 
		ArrayList<MemberBean> memberArr = new ArrayList<MemberBean>();
		
		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		memberArr = sellerDAO.memberGrade();
	
		close(con);
		
		
		return memberArr;
	}

	public MemberBean memberHasGrade(String member_code) { //기존 회원의 등급을 가져옴
		MemberBean member = new MemberBean();
		
		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		member = sellerDAO.memberHasGrade(member_code);
	
		close(con);
		
		return member;
	}

}
