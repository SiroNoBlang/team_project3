package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BuyListService;
import vo.ActionForward;
import vo.BuyDTO;
import vo.SellerProductDTO;

public class BuyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BuyListAction");
		ActionForward forward = null;
		
//		int event_num = Integer.parseInt(request.getParameter("event_num"));
//		System.out.println(event_num);
		
		String code = request.getParameter("member_code");
		System.out.println("멤버 코드 " + code);
		
		BuyListService service = new BuyListService();
//		EventBean eventArticle = service.getEventArticle(event_num);
		BuyDTO buyList = service.getBuyList(code);  
		
//		ArrayList<EventImgFileBean> eventImgFileList = service.getEventImg(event_num);
		ArrayList<SellerProductDTO> buyImgFileList = service.getBuyListImg(code);
		
//		//조회수 증가라 필요 없을 거 같음
//		if(eventArticle != null) {
////			System.out.println("조회수 증가");
//			service.increaseEventReadcount(event_num);
//		}
		
		request.setAttribute("buyList", buyList);
		request.setAttribute("buyImgFileList", buyImgFileList);
		
		forward = new ActionForward();
		forward.setPath("MainPage/my_page/buyList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
