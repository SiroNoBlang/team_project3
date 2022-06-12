package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.MemberBean;

public class MemberDetailService {

	public MemberBean getMemberDetail(String member_code) {
		MemberBean memberDetail = null;
		
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		// 해당 멤버코드의 상세 정보 출력 _이효민 06.12 수정
		memberDetail = adminDAO.getMemberArticle(member_code);
		
		if(memberDetail != null) {
			commit(con);
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return memberDetail;
	}
	

}
