package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.JoinProAction;
import action.LoginProAction;
import action.LogoutAction;
import action.MpEventDetailAction;
import action.MpEventListAction;
import action.MpEventSearchAction;
import action.MpNoticeDetailAction;
import action.MpNoticeListAction;
import action.MpNoticeSearchAction;
import action.MyPageAction;
import action.MyPageUpdateAction;
import vo.ActionForward;

@WebServlet("*.ma")
public class Main extends HttpServlet {
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // post방식 한글화
		
		String command = request.getServletPath(); // 주소 추출
		
		ActionForward forward = null;
		Action action = null;
		
		System.out.println("주소추출 : " + command);
		if(command.equals("/JoinPro.ma")) {
			action = new JoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				
			}
		} else if(command.equals("/Login.ma")) {
			forward = new ActionForward("join/login.jsp", false);
			
		} else if(command.equals("/LoginPro.ma")) {
			action = new LoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				
			}
		} else if(command.equals("/Logout.ma")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		} else if(command.equals("/Mypage.ma")) {
			action = new MyPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		} else if(command.equals("/Modify_Member.ma")) {
			action = new MyPageUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				
			}
		} else if(command.equals("/Suspension.ma")) {
			forward = new ActionForward("HomePage/guide_page/suspension.jsp", false);
			
		} else if(command.equals("/Withdrawal.ma")) {
			forward = new ActionForward("HomePage/guide_page/withdrawal.jsp", false);
			
		} else if(command.equals("/Reason.ma")) {
			forward = new ActionForward("HomePage/guide_page/reason.jsp", false);
			
		}	
		
		
		
		
		
		
		
		//커뮤니티 작업 서블릿 
		else if(command.equals("/CommunityNotice.ma")) { // 커뮤니티 클릭시 공지사항으로(/Community.ma)
			action = new MpNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		}else if(command.equals("/CommunityNoticeDetail.ma")) { // 커뮤니티 공지사항 디테일(/CommunityNoticeDetail.ma)
			action = new MpNoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		}else if(command.equals("/CommunityNoticeSearch.ma")) { // 커뮤니티 공지사항 검색(/CommunityNoticeSearch.ma)
			action = new MpNoticeSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		}else if(command.equals("/CommunityEvent.ma")) { // 커뮤니티 이벤트 클릭(/CommunityNotice.ma)
			action = new MpEventListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		}else if(command.equals("/CommunityEventDetail.ma")) { // 커뮤니티 이벤트 디테일(/CommunityEventDetail.ma)
			action = new MpEventDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		}else if(command.equals("/CommunityEventSearch.ma")) { // 커뮤니티 이벤트 검색기능(/CommunityEventSearch.ma)
			action = new MpEventSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			}
		}
		
		
		
		
		
		//ActionForward 객체에 저장된 포워딩 정보에 따른 포워딩 작업 수행
		if(forward != null) { 
			if(forward.isRedirect()) { //Redirect방식
				response.sendRedirect(forward.getPath());
			} else{ //Dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			//임시로 만들어둠
			System.out.println("ActionForward 객체가 null 입니다!");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
