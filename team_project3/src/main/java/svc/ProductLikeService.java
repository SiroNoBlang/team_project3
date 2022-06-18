package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.Map;

import dao.SellerDAO;

public class ProductLikeService {

	public int recCheck(String sCode,int sell_num) {   //체크여부 확인해주는 매서드
		int result=0;

		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		result = sellerDAO.recCeck(sCode,sell_num);  //좋아요(클릭 or 취소) 확인하는 기능 
		
		close(con);
		return result;
		
	}

	public void recUpdate(String sCode,int sell_num) { //좋아요 클릭
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		sellerDAO.recUpdate(sCode,sell_num); 
		commit(con);
		close(con);
	}

	public void reDelete(String sCode,int sell_num) {  //좋아요 취소
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		sellerDAO.reDelete(sCode,sell_num);  //좋아요(클릭 or 취소) 확인하는 기능 
		commit(con);
		close(con);
		
	}

	public int likeCount(int sell_num) {  //판매글을 통하여 좋아요 갯수 구하기
		int likeCount =0;
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		likeCount =sellerDAO.likeCount(sell_num);  //좋아요(클릭 or 취소) 확인하는 기능 
		
		close(con);
		
		return likeCount;
	}


}
