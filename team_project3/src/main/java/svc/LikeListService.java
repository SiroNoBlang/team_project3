package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.LikeListBean;

public class LikeListService {

	public int getListCount(String member_code) {
		System.out.println("LikeListService");
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectListCount(member_code);
		
		close(con);		
		
		return listCount;
	}

	public ArrayList<LikeListBean> getArticleList(int pageNum, int listLimit, String member_code) {
		System.out.println("ArrayList - service");
		System.out.println("ArrayList-서비스에서 :" + member_code);
		ArrayList<LikeListBean> articleList = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		articleList = memberDAO.selectArticleList(pageNum, listLimit, member_code);
		
		close(con);		
		
		return articleList;
	}

}
