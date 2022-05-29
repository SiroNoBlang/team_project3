package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.SellerProductDTO;

public class UpdateProductConfirmService {

	public boolean confirmUpdate(SellerProductDTO confirm) {
		boolean isUpdateSuccess = false;
		
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int updateCount = adminDAO.updateConfirm(confirm);
		
		if(updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isUpdateSuccess;
	}

}
