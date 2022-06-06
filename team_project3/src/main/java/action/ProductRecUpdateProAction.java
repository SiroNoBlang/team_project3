package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class ProductRecUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("좋아요 기능-UPDATE");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = null;
		String sCode = request.getParameter("id");
		String sell_num = (request.getParameter("no"));
		
		System.out.println("scode"+sCode);
		System.out.println("sell_num:"+sell_num);
		
		return forward;
	}

}
