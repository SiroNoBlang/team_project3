package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.SellerProductDTO;

public class LikeSmallListService {  

	public int getLikeListCount(String tableName) {
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectLikeSmallListCount(tableName);
		
		close(con);
		return listCount;
	}

	public ArrayList<SellerProductDTO> getLikeSmallList(int pageNum, int listLimit, String code) {
		ArrayList<SellerProductDTO> likeSmallList = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		likeSmallList = memberDAO.selectLikeSmallList(pageNum, listLimit, code);
		
		commit(con);
		close(con);
		
		return likeSmallList;
	}

}
