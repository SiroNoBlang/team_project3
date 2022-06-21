package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ContactService;
import vo.ActionForward;
import vo.MemberBean;

public class ContactAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String code = request.getParameter("member_code");
		
		ContactService service = new ContactService();
		MemberBean member = service.getContactEmail(code);
		
		request.setAttribute("member", member);
		forward = new ActionForward("MainPage/community/contact.jsp", false);
		
		return forward;
	}

}
