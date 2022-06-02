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
		
		salesChartList = adminDAO.selectSalesChartList(month);
		
		System.out.println("SERVICE" + salesChartList);
		
		close(con);
		
		return salesChartList;
	}

}
