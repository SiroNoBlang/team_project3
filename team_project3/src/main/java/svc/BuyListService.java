package svc;


import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.BuyDTO;
import vo.SellerDTO;
import vo.SellerProductDTO;

import static db.JdbcUtil.*;
public class BuyListService {
	
	//총 구매목록 수 조회
	public int getListCount(String tableName) {
//		System.out.println("ProductConfirmListService - getListCount()");
		
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
	System.out.println("BuyListService - getBuyList()");
	
	ArrayList<SellerProductDTO> buyList = null;
	
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	
	memberDAO.setConnection(con);
	
	buyList = memberDAO.selectBuyList(pageNum, listLimit, code);
	
	commit(con);
	close(con);
	
	return buyList;
}

//	//구매리스트 목록에 필요한 사진
//	public ArrayList<SellerProductDTO> getBuyListImg(String code) {
//		System.out.println("BuyListService - getBuyListImg()");
//		
//		ArrayList<SellerProductDTO> buyImgFileList = null;
//		
//		Connection con = getConnection();
//		MemberDAO memberDAO = MemberDAO.getInstance();
//		 
//		memberDAO.setConnection(con);
//		
//		buyImgFileList = memberDAO.getBuyListImg(code);
//		
//		close(con);
//		
//		return buyImgFileList;
//	}

//	public ArrayList<SellerDTO> getBuySellList(int pageNum, int listLimit, String code) {
//		System.out.println("BuyListService - getBuySellList()");
//		ArrayList<SellerDTO> sellList = null;
//		
//		Connection con = getConnection();
//		MemberDAO memberDAO = MemberDAO.getInstance();
//		
//		memberDAO.setConnection(con);
//		
//		sellList = memberDAO.selectBuySellList(pageNum, listLimit, code);
//		
//		close(con);
//		
//		return sellList;
//	}

	
}
