package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.SellerProductDTO;

public class productListProService {

	public int getListCount() {
		int listCount = 0;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		listCount = sellerDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}

	public ArrayList<SellerProductDTO> getArticleList(int pageNum, int listLimit) {
		ArrayList<SellerProductDTO> articleList = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		// BoardDAO 객체의 selectArticleList() 메서드를 호출
		articleList = sellerDAO.selectArticleList(pageNum, listLimit);
		
		close(con);
		
		return articleList;
	}

}