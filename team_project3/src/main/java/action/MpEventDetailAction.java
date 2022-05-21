package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EventDetailService;
import vo.ActionForward;
import vo.EventBean;
import vo.EventImgFileBean;

public class MpEventDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//			System.out.println("MpEventDetailAction");
			ActionForward forward = null;
			
			int event_num = Integer.parseInt(request.getParameter("event_num"));
			
			EventDetailService service = new EventDetailService();
			EventBean eventArticle = service.getEventArticle(event_num);
			
			ArrayList<EventImgFileBean> eventImgFileList = service.getNoticeImg(event_num);
			
			
			if(eventArticle != null) {
//				System.out.println("조회수 증가");
				service.increaseEventReadcount(event_num);
			}
			
			request.setAttribute("eventArticle", eventArticle);
			request.setAttribute("eventImgFileList", eventImgFileList);
			
			forward = new ActionForward();
			forward.setPath("MainPage/community/communityEventView.jsp");
			forward.setRedirect(false);
			
			return forward;
	}

}
