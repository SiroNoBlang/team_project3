package svc;


import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.BuyDTO;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.SellerProductDTO;

import static db.JdbcUtil.*;
public class BuyListService {

	public BuyDTO getBuyList(String code) {
	System.out.println("EventDetailService - getEventArticle()");
	
	BuyDTO buyList = null;
	
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	
	memberDAO.setConnection(con);
	
	buyList = memberDAO.selectBuyList(code);
	
	close(con);
	
	return buyList;
}

public ArrayList<SellerProductDTO> getBuyListImg(String code) {

	ArrayList<SellerProductDTO> buyImgFileList = null;
	
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	 
	memberDAO.setConnection(con);
	
	buyImgFileList = memberDAO.getBuyListImg(code);
	
	close(con);
	
	return buyImgFileList;
}

	
}
