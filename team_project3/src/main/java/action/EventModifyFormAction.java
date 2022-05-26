package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EventDetailService;
import vo.ActionForward;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeImgFileBean;

public class EventModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("EventModifyFormAction");
		
		ActionForward forward = null;
		
		// request 객체를 통해 전달받은 파라미터 가져오기
		int event_num = Integer.parseInt(request.getParameter("event_num"));
//		System.out.println(notice_num);
		
		EventDetailService service = new EventDetailService();
		EventBean eventArticle = service.getEventArticle(event_num);
		ArrayList<EventImgFileBean> eventImgFileList = service.getEventImg(event_num);
		
		request.setAttribute("eventArticle", eventArticle);
		request.setAttribute("eventImgFileList", eventImgFileList);
		
		forward = new ActionForward();
		forward.setPath("./AdminPage/event/eventModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
