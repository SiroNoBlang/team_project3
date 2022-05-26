package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.SellerDTO;
import vo.SellerimgDTO;

public class ProductConfirmDetailService {

	public SellerDTO getProductConfirmArticle(int sell_num) {
		
		
		SellerDTO confirmArticle = null;
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		confirmArticle = adminDAO.selectProductConfirmDetail(sell_num);
		
		close(con);
		
		return confirmArticle;
		
	}

	public ArrayList<SellerimgDTO> getProductConfirmImg(int sell_num) {
		ArrayList<SellerimgDTO> confirmImgFileList = null;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		confirmImgFileList = adminDAO.getConfirmImg(sell_num);
		
		close(con);
		
		return confirmImgFileList;
	}

}
