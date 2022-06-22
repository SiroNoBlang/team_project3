package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class ChatAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = null;
		
		String code = request.getParameter("id");
		System.out.println(code);
		request.setAttribute("code", "이게뭔데");
		forward = new ActionForward("MainPage/menu/chatBot.jsp", false);
		
		return forward;
	}

}
