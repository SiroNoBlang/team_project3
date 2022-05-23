package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MyPageUpdateImgService {

	public boolean imgUpdate(MemberBean member) {
		System.out.println("MyPageUpdateImgService");
		boolean isSuccess = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int updateCount = memberDAO.getUpateImg(member);
		
		if(updateCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return isSuccess;
	}
	
	
	
	
}
