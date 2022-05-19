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
