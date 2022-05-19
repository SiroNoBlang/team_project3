package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberDetailService {

	public MemberBean getMemberDetail(String member_code) {
		System.out.println("MemberDetailService");
		
		MemberBean memberDetail = null;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		memberDetail = memberDAO.getMemberArticle(member_code);
		
		if(memberDetail != null) {
			commit(con);
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return memberDetail;
	}
	

}