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
		// 있다고 가정하고 진행할 예정임 나중에 넘어오도록 고쳐야함
		String code = request.getParameter("member_code");
		
		ContactService service = new ContactService();
		MemberBean member = service.getContactEmail(code);
		
		request.setAttribute("member", member);
		forward = new ActionForward("MainPage/community/contact.jsp", false);
		
		return forward;
	}

}
