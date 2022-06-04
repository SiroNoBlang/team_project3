package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

public class SellerDetailService {

	public SellerProductDTO getArticle(int sell_num) {
		SellerProductDTO article = null;
		
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		// BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		article = sellerDAO.selectArticle(sell_num);
		
		close(con);
		
		return article;
	
	}

	// 여기 리턴 값은 어딨나요? 메서드 자체에 문제가 있는데요.
	public void increaseReadcount(int sell_num) {
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		sellerDAO.updateReadcount(sell_num);
		
		commit(con);
		
		close(con);
		
	}
	
	public ArrayList<SellerProductDTO> getProductRe(String sell_brand,int sell_num) {
		ArrayList<SellerProductDTO> ProductRe = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		ProductRe = sellerDAO.selectProductRe(sell_brand,sell_num);
		
		close(con);
		
		return ProductRe;

	}

	public ArrayList<SellerimgDTO> getimgArticle(int sell_num) {
		ArrayList<SellerimgDTO> Sellerdetailimg = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		Sellerdetailimg = sellerDAO.selectProductimg(sell_num);
		
		close(con);
		
		return Sellerdetailimg;

	}

}
