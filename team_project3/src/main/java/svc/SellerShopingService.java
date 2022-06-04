package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.SellerDAO;
import vo.MemberBean;
import vo.SellerProductDTO;
public class SellerShopingService {
	
	public SellerProductDTO getShoping(int sell_num) {
		SellerProductDTO shoping = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		sellerDAO.setConnection(con);
		
		shoping = sellerDAO.selectArticle(sell_num);  //SellerDAO 에서   <190행selectArticle(int sell_num)매서드 활용>
		
		close(con);
		
		return shoping;
	
	}

	public MemberBean getMemberShop(String member_code) {
		MemberBean MemberShop = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		sellerDAO.setConnection(con);
		
		MemberShop = sellerDAO.selectMemberShop(member_code);
		
		close(con);
		
		return MemberShop;
	}


}
