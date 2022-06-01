package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ContactService;
import vo.ActionForward;

public class ContactAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		// 있다고 가정하고 진행할 예정임 나중에 넘어오도록 고쳐야함
		String code = request.getParameter("code");
		
		ContactService service = new ContactService();
		String email = service.getContactEmail(code);
		
		request.setAttribute("email", email);
		forward = new ActionForward("MainPage/community/contact.jsp", false);
		
		return forward;
	}

}
