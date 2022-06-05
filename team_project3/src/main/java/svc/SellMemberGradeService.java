package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.MemberBean;

public class SellMemberGradeService {

	public ArrayList<MemberBean> MemberGrade(String member_code) {
		ArrayList<MemberBean> memberArr = new ArrayList<MemberBean>();
		
		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		memberArr = sellerDAO.memberGrade(member_code);

		close(con);
		
		
		return memberArr;
	}

}
