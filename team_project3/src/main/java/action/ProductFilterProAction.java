package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class ProductFilterProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;		

		String sendData = request.getParameter("sendData");
		String a = request.getParameter("a");
		
		System.out.println(sendData);
		System.out.println(a);
		return forward;
	}

}
