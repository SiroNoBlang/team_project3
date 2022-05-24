package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FindIdService;
import vo.ActionForward;

public class FindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FindIdAction");
		
		ActionForward forward = null;
		
		String nickname = request.getParameter("find_id_member_nickname");
		String email = request.getParameter("find_id_member_email");
		
		FindIdService service = new FindIdService();
		String isFindId = service.isFindId(nickname, email); 
		
		if(isFindId == null || isFindId.equals("")) {
			//history.back
		} else {
			request.setAttribute("isFindId", isFindId);
			forward = new ActionForward("", false);
		}
		
		return forward;
	}

}
