package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.SellerDAO;

public class ContactService {

	public String getContactEmail(String code) {
		String email = "";
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		email = sellerDAO.getEmail(code);
		
		close(con);
		
		return email;
	}

}
