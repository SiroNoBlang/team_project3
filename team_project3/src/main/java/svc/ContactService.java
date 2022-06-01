package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.SellerDAO;
import vo.MemberBean;

public class ContactService {

	public MemberBean getContactEmail(String code) {
		MemberBean member = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		member = sellerDAO.getEmail(code);
		
		close(con);
		
		return member;
	}

}
