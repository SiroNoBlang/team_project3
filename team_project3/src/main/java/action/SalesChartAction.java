package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import svc.SalesChartService;
import vo.ActionForward;
import vo.SalesList;

public class SalesChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		SalesChartService service = new SalesChartService();
		
		List<SalesList> salesChartList = service.getSalesChartList(month);
		
		request.setAttribute("salesChartList", salesChartList);
		String json = new Gson().toJson(salesChartList);
		
		response.getWriter().write(json);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/sales/sales_data.jsp");
		forward.setRedirect(false);
		forward = null;
		
		return forward;
	}

}
