package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.NoticeBean;
import vo.SellerDTO;

public class ProductConfirmSearchService {

	public int selectConfirmSearchListCount(String tableName, String search, String searchType) {
	int listCount =  0;
		
		Connection con = getConnection();
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		listCount = adminDAO.selectConfirmSearchListCount(tableName,search,searchType);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<SellerDTO> selectConfirmSearchList(int pageNum, int listLimit, String search, String searchType) {
//		System.out.println("selectConfirmSearchList");
		
		ArrayList<SellerDTO> productConfirmSearch =null;
		
		Connection con = getConnection(); 
		AdminDAO adminDAO= AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		productConfirmSearch = adminDAO.selectConfirmSearchList(pageNum, listLimit, search, searchType);
		
		close(con);
		
		return productConfirmSearch;
	}

}
