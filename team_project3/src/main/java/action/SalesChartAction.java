package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SalesChartService;
import vo.ActionForward;
import vo.SalesList;

public class SalesChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SalesChartAction");
		ActionForward forward = null;
		
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		SalesChartService service = new SalesChartService();
		
		ArrayList<SalesList> salesChartList = service.getSalesChartList(month);
		
		request.setAttribute("month", month);
		request.setAttribute("salesChartList", salesChartList);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/sales/sales_data.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
