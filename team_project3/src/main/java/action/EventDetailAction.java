package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EventDetailService;
import svc.NoticeDetailService;
import vo.ActionForward;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class EventDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("EventDetailAction");
		ActionForward forward = null;
		
		int admin_event_num = Integer.parseInt(request.getParameter("admin_event_num"));
		
		EventDetailService service = new EventDetailService();
		EventBean eventArticle = service.getEventArticle(admin_event_num);
		
		ArrayList<EventImgFileBean> eventImgFileList = service.getNoticeImg(admin_event_num);
		
		
		if(eventArticle != null) {
//			System.out.println("조회수 증가");
			service.increaseEventReadcount(admin_event_num);
		}
		
		request.setAttribute("eventArticle", eventArticle);
		request.setAttribute("eventImgFileList", eventImgFileList);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/event/eventView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
