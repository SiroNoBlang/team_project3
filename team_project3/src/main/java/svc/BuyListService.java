package svc;


import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.SellerProductDTO;

import static db.JdbcUtil.*;
public class BuyListService {
	
	//총 구매목록 수 조회
	public int getListCount(String tableName) {
		
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectBuyListCount(tableName);
		
		close(con);
		return listCount;
	}

	//구매리스트 목록 담아오기
	public ArrayList<SellerProductDTO> getBuyList(int pageNum, int listLimit, String code) {
	
	ArrayList<SellerProductDTO> buyList = null;
	
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	
	memberDAO.setConnection(con);
	
	buyList = memberDAO.selectBuyList(pageNum, listLimit, code);
	
	commit(con);
	close(con);
	
	return buyList;
	}
	
}
