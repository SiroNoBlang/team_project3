package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.SellerDTO;

public class productConfirmTypeService {

	public SellerDTO getListCountType() {
		SellerDTO CountType = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		CountType = adminDAO.getListCountType();
		
		close(con);
		return CountType;
	}

	public ArrayList<SellerDTO> getConfirmType(int pageNum, int listLimit, String column) {
		ArrayList<SellerDTO> productConfirmList  = null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		productConfirmList = adminDAO.selectConfirmType(pageNum, listLimit,column);
		
		close(con);
		
		return productConfirmList;
	}

	
	
	public int getListCount(String tableName) {
		int listCount = 0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectListCount(tableName);
		
		close(con);
		
		return listCount;
	}

}
