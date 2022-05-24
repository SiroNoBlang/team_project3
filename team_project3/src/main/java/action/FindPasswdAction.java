package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.FindPasswdService;
import svc.LoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class FindPasswdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FindPasswdAction");
		
		ActionForward forward = null;
		
		String id = request.getParameter("find_passwd_member_id");
		String email = request.getParameter("find_passwd_member_email");
		
		FindPasswdService service = new FindPasswdService();
		boolean isFindPasswd = service.isFindPasswd(id, email);
		
		return forward;
	}

}
