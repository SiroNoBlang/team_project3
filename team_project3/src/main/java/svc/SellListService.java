package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.LikeListBean;
import vo.SellerDTO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

public class SellListService {

	public int getListCount(String member_code) {
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectSellListCount(member_code);
		
		close(con);		
		
		
		return listCount;
	}
//====================================================================================================
	public ArrayList<SellerProductDTO> getArticleList(int pageNum, int listLimit, String member_code) {
		ArrayList<SellerProductDTO> sellarticleList = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		sellarticleList = memberDAO.selectSellArticleList(pageNum, listLimit, member_code);
		
		close(con);	
		
		return sellarticleList;
	}

}
