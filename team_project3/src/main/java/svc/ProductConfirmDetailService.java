package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.SellerDTO;

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

	public ArrayList<SellerDTO> getProductConfirmImg(int sell_num) {
		ArrayList<SellerDTO> confirmImgFileList = null;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		confirmImgFileList = adminDAO.getConfirmImg(sell_num);
		
		close(con);
		
		return confirmImgFileList;
	}

}
