package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.MemberBean;
import vo.SellerAddress;
import vo.SellerProductDTO;

public class SellRecentPostAddressService {
	public ArrayList< SellerAddress> findAddress(String sell_member_code) {
		System.out.println("SellRecentPostAddressService- findAddress()");  //배송지 찾기 리턴타입:ArrayList<address>
		
		SellerProductDTO shoping = null;
		SellerAddress address = new SellerAddress();
		ArrayList< SellerAddress> addressArr = new ArrayList<SellerAddress>();
		Connection con = getConnection(); 
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		sellerDAO.setConnection(con);
		
		addressArr = sellerDAO.findAddress(sell_member_code);    
		
		close(con);
		
		return addressArr;
		
	}

	public void insertAddress(SellerAddress address) {
		System.out.println("SellRecentPostAddressService- insertAddress()");//배송지 저장 리턴타입:void
		
		Connection con = getConnection(); 
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		sellerDAO.setConnection(con);
		
		 sellerDAO.insertAddress(address);    
		commit(con);
		close(con);
		
	}
	
}


	
