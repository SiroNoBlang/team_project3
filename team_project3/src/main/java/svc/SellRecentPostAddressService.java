package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.AddressVO;
import vo.SellerAddress;
import vo.SellerProductDTO;

public class SellRecentPostAddressService {
	public ArrayList<SellerAddress> findAddress(String sell_member_code) {
		// 여기 아래도 마찬가지 및에 변수는 생성해두고 쓰지 않을 꺼면 없애주세요.
//		SellerProductDTO shoping = null;
//		SellerAddress address = new SellerAddress();
		ArrayList<SellerAddress> addressArr = new ArrayList<SellerAddress>();
		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		addressArr = sellerDAO.findAddress(sell_member_code);

		close(con);

		return addressArr;

	}

	public void insertAddress(SellerAddress address) {
		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		sellerDAO.insertAddress(address);
		
		commit(con);
		close(con);

	}

	public AddressVO getAddress() {  //결제 완료페이지에서 배송지 찾기
		AddressVO post = new AddressVO();
		
		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		post=sellerDAO.getAddress();
		
		
		close(con);
		
		return post;
	}

}
