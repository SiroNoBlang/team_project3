package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class ProductRecCountProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("좋아요 갯수 찾는 기능");
		request.setCharacterEncoding("utf-8");
		String sCode = request.getParameter("id");
		int sell_num = Integer.parseInt((request.getParameter("sell_num")));
		System.out.println("sell_num"+sell_num);
		System.out.println("sCode"+sCode);
		ActionForward forward = null;
		return forward;
	}

}
