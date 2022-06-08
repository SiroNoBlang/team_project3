package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BuyListAction;
import action.FindIdAction;
import action.FindPasswdAction;
import action.JoinProAction;
import action.LikeListAction;
import action.LikeSmallListAction;
import action.LoginProAction;
import action.LogoutAction;
import action.MpEventDetailAction;
import action.MpEventListAction;
import action.MpEventSearchAction;
import action.MpNoticeDetailAction;
import action.MpNoticeListAction;
import action.MpNoticeSearchAction;
import action.MpQnaConfirmProAction;
import action.MpQnaDeleteAction;
import action.MpQnaDetailAction;
import action.MpQnaListAction;
import action.MpQnaModifyFormAction;
import action.MpQnaModifyProAction;
import action.MpQnaSearchAction;
import action.MpQnaWriteProAction;
import action.MyPageAction;
import action.MyPageImgUpdateAction;
import action.MyPageUpdateAction;
import action.SellListAction;
import action.SendEmailAction;
import vo.ActionForward;

@WebServlet("*.ma")
public class Main extends HttpServlet {
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // post방식 한글화
		
		String command = request.getServletPath(); // 주소 추출
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/JoinPro.ma")) {
			// 여기 컨트롤러에 남겨도 됨
			action = new JoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Login.ma")) {
			// 이것도 여기 있어도 됨.
			forward = new ActionForward("join/login.jsp", false);
			
		} else if(command.equals("/LoginPro.ma")) {
			// 여기 있어도 됨.
			action = new LoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Logout.ma")) {
			// 이것도 여기 있어도 됨.
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Mypage.ma")) {
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MyPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Modify_Member.ma")) {
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MyPageUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/ProfileImgUpdate.ma")) {
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MyPageImgUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Suspension.ma")) {
			// 이건 여기 남겨도 됨.
			forward = new ActionForward("HomePage/guide_page/suspension.jsp", false);
			
		} else if(command.equals("/Withdrawal.ma")) {
			// 이건 여기 남겨도 됨.
			forward = new ActionForward("HomePage/guide_page/withdrawal.jsp", false);
			
		} else if(command.equals("/Reason.ma")) {
			// 이건 여기 남겨도 됨.
			forward = new ActionForward("HomePage/guide_page/reason.jsp", false);
			
		}else if(command.equals("/CommunityNotice.ma")) { // 커뮤니티 클릭시 공지사항으로(/CommunityNotice.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommunityNoticeDetail.ma")) { // 커뮤니티 공지사항 디테일(/CommunityNoticeDetail.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpNoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommunityNoticeSearch.ma")) { // 커뮤니티 공지사항 검색(/CommunityNoticeSearch.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpNoticeSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommunityEvent.ma")) { // 커뮤니티 이벤트 클릭(/CommunityEvent.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpEventListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommunityEventDetail.ma")) { // 커뮤니티 이벤트 디테일(/CommunityEventDetail.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpEventDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommunityEventSearch.ma")) { // 커뮤니티 이벤트 검색기능(/CommunityEventSearch.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpEventSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQna.ma")) { // 커뮤니티 QnA으로(/CommunityEventSearch.ma)
			action = new MpQnaListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQnaConfirm.ma")) { // 커뮤니티 Qna 확인 form(/CommunityQnaConfirm.ma)
			// 이것도 메인페이지 컨트롤러로 옮겨야 함.
			forward = new ActionForward("MainPage/community/communityQnaConfirm.jsp", false);
		}else if(command.equals("/CommunityQnaConfirmPro.ma")) { // 커뮤니티 Qna 확인(/CommunityQnaConfirmPro.ma)
			// 이것도 메인페이지 컨트롤러로 옮겨야함.
			action = new MpQnaConfirmProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommunityQnaDetail.ma")) { // 커뮤니티 QnA 디테일(/CommunityNoticeDetail.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpQnaDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQnaSearch.ma")) { // 커뮤니티 Qna 검색(/CommunityQnaSearch.ma)
			// 이것도 메인페이지 컨트롤러로 옮겨야함.
			action = new MpQnaSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQnaDeleteForm.ma")) { // 삭제시 비밀번호 확인(/CommunityQnaDeleteForm.co)
			// 이것도 메인페이지 컨트롤러로 옮겨야함.
			forward = new ActionForward("MainPage/community/communityQnaDelete.jsp",false);

		} else if(command.equals("/CommunityQnaDeletePro.ma")) { // 커뮤니티 Qna 삭제(/CommunityQnaDeletePro.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpQnaDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQnaWriteForm.ma")) { // 커뮤니티 Qna 글쓰기폼(/CommunityQnaWriteForm.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			forward = new ActionForward("MainPage/community/communityQnaWrite.jsp", false);
		} else if(command.equals("/CommunityQnaWritePro.ma")) { // 커뮤니티 Qna 글쓰기(/CommunityQnaWritePro.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpQnaWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQnaModifyForm.ma")) { // 커뮤니티 Qna 수정 form(/CommunityQnaModifyForm.ma)
			// 이것도 메인페이지 컨트롤러로 옮겨야함.
			action = new MpQnaModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CommunityQnaModifyPro.ma")) { // 커뮤니티 Qna 수정 form(/CommunityQnaModifyForm.ma)
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new MpQnaModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/FindId.ma")) { //아이디 찾기
			// 이건 여기 있어도 됨.
			action = new FindIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/FindPasswd.ma")) { // 비밀번호 찾기
			// 이것도 여기 있어도 됨.
			action = new FindPasswdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/SendEmail.ma")) { // 회원가입시 인증 이메일 보내기
			// 이것도 여기 있어도 됨.
			action = new SendEmailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/LikeList.ma")) { // 찜리스트 확인
			// 이건 메인 페이지 컨트롤러로 옮겨야함.
			action = new LikeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if(command.equals("/SellList.ma")) { // 판매리스트 확인
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new SellListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BuyList.ma")) { // 구매목록 조회
			// 이것도 메인 페이지 컨트롤러로 옮겨야함.
			action = new BuyListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/LikeSmallList.ma")) { // 찜목록 사진 조회
			action = new LikeSmallListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
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
