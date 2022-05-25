package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.SellerDTO;

public class ProductConfirmListService {

	public int getListCount(String tableName) {
//		System.out.println("ProductConfirmListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		return listCount;
	}

	public ArrayList<SellerDTO> getConfirmList(int pageNum, int listLimit) {
		ArrayList<SellerDTO> productConfirmList  = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		productConfirmList = adminDAO.selectConfirmList(pageNum, listLimit);
		
		close(con);
		
		return productConfirmList;
	}

	
	
	
	
	
	
}
