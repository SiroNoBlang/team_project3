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
		// 여기선 그냥 널이 갈 수 밖에 없음 이동하는 것이 아니기 때문에.
		ActionForward forward = null;
		
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		SalesChartService service = new SalesChartService();
		
		// 해당하는 월의 값을 List에 담기
		List<SalesList> salesChartList = service.getSalesChartList(month);
		
		// AJAX를 통해 json 파일을 건네주어야하기 때문에 받은 List의 값을 json 변환 작업 _이효민 06.12 확인
		String json = new Gson().toJson(salesChartList);
		
		response.getWriter().write(json);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/sales/sales_data.jsp");
		forward.setRedirect(false);
		forward = null;
		
		return forward;
	}

}
