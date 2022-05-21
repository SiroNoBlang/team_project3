package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeModifyProAction");
		ActionForward forward = null;
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		

		
		
		
		
		
		
		
		
		
		
		
		
		return forward;
	}

}
