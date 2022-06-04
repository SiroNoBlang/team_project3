package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.SellerProductDTO;

public class productSearchProService {

	public int getSearchListCount() {
		int listCount = 0;

		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		listCount = sellerDAO.selectListCount();

		close(con);

		return listCount;
	}

	public ArrayList<SellerProductDTO> getProductList(int pageNum, int listLimit, String ProductSearch) {
		ArrayList<SellerProductDTO> productList = null;

		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		productList = sellerDAO.searchArticleList(pageNum, listLimit, ProductSearch);

		close(con);

		return productList;
	}

	// 카테고리 분류
	public ArrayList<SellerProductDTO> getProductCateList(int pageNum, int listLimit, String Category) {
		ArrayList<SellerProductDTO> productCateList = null;

		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		productCateList = sellerDAO.CateArticleList(pageNum, listLimit, Category);

		close(con);

		return productCateList;
	}

}
