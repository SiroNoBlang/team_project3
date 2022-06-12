package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;

import static db.JdbcUtil.*;
import vo.SalesList;

public class SalesChartService {

	public ArrayList<SalesList> getSalesChartList(String[] month) {
		ArrayList<SalesList> salesChartList = null;
		
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		// 차트에 가져올 데이터를 DB에서 가져오기 _이효민 06.12 확인
		salesChartList = adminDAO.selectSalesChartList(month);
		
		close(con);
		
		return salesChartList;
	}

}
