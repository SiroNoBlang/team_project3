package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.LikeListBean;
import vo.SellerProductDTO;

public class LikeListService {

	public int getListCount(String member_code) {
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectListCount(member_code);
		
		close(con);		
		
		return listCount;
	}

	public ArrayList<SellerProductDTO> getArticleList(int pageNum, int listLimit, String member_code) {
		ArrayList<SellerProductDTO> articleList = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		articleList = memberDAO.selectArticleList(pageNum, listLimit, member_code);
		
		close(con);		
		
		return articleList;
	}

}
