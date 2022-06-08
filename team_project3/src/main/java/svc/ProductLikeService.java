package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.Map;

import dao.SellerDAO;

public class ProductLikeService {

	public int recCheck(String sCode,int sell_num) {   //체크여부 확인해주는 매서드
		System.out.println("ProductLikeService-recCheck()");
		int result=0;

		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		result = sellerDAO.recCeck(sCode,sell_num);  //좋아요(클릭 or 취소) 확인하는 기능 
		
		close(con);
		return result;
		
	}

	public void recUpdate(String sCode,int sell_num) { //좋아요 클릭
		System.out.println("ProductLikeService-좋아요 실행");
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		sellerDAO.recUpdate(sCode,sell_num); 
		commit(con);
		close(con);
	}

	public void reDelete(String sCode,int sell_num) {  //좋아요 취소
		System.out.println("ProductLikeService-좋아요 취소");
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		sellerDAO.reDelete(sCode,sell_num);  //좋아요(클릭 or 취소) 확인하는 기능 
		commit(con);
		close(con);
		
	}


}
