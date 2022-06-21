package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ClassificationAction;
import action.ClassificationDeleteAction;
import action.ClassificationDetailAction;
import action.ClassificationUpdateAction;
import action.CommunityBoardWriteProAction;
import action.EventDeleteAction;
import action.EventDetailAction;
import action.EventListAction;
import action.EventModifyFormAction;
import action.EventSearchAction;
import action.MemberDeleteAction;
import action.MemberDetailAction;
import action.MemberManagementListAction;
import action.MemberUpdateAction;
import action.NoticeDeleteAction;
import action.NoticeDetailAction;
import action.NoticeListAction;
import action.NoticeModifyFormAction;
import action.NoticeModifyProAction;
import action.NoticeSearchAction;
import action.ProductConfirmDetailAction;
import action.ProductConfirmListAction;
import action.ProductConfirmSearchAction;
import action.QnaDeleteProAction;
import action.QnaDetailAction;
import action.QnaModifyFormAction;
import action.QnaModifyProAction;
import action.QnaReplyFormAction;
import action.QnaReplyProAction;
import action.QnaSearchAction;
import action.QnadListAction;
import action.QnadWriteProAction;
import action.SalesChartAction;
import action.UpdateProductConfirmAction;
import action.ProductConfirmTypeAction;
import vo.ActionForward;

// 커뮤니티 게시판 Controller
@WebServlet("*.co")
public class CommunityFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		// 서블릿 주소 판별
		// 1. 글쓰기 폼에 대한 요청 판별("/CommunityWriteForm.co")
		if (command.equals("/CommunityWriteForm.co")) {
			forward = new ActionForward("AdminPage/notice/communityWrite.jsp", false);

		} else if (command.equals("/CommunityBoardWritePro.co")) { // 작성완료 버튼 클릭시
			action = new CommunityBoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NoticeList.co")) {// 공지사항 리스트(/NoticeList.co) 요청
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/NoticeDetail.co")) {// 공지사항 상세정보(/NoticeDetail.co) 요청
			action = new NoticeDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NoticeSearch.co")) {// 공지사항 검색(/NoticeSearch.co) 요청
			action = new NoticeSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NoticeModifyForm.co")) { // 공지사항 수정하는 상세정보(/NoticeModifyForm.co) 요청
			action = new NoticeModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NoticeModifyPro.co")) {// 공지사항 및 이벤트 수정(/NoticeModifyPro.co) 요청
			action = new NoticeModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NoticeDelete.co")) {// 공지사항 삭제(/NoticeDelete.co) 요청
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EventList.co")) {// 이벤트 리스트(/EventList.co) 요청
			action = new EventListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EventDetail.co")) {// 이벤트 상세정보(/EventDetail.co) 요청
			action = new EventDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EventModifyForm.co")) { // 이벤트 수정하는 상세정보(/EventModifyForm.co) 요청
			action = new EventModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("/EventSearch.co")) {// 이벤트 검색(/EventSearch.co) 요청
			action = new EventSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EventDelete.co")) {// 이벤트 삭제(/EventDelete.co) 요청
			action = new EventDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/QnaWritePro.co")) { // QnA 글쓰기(/QnaWritePro.co) 요청
			action = new QnadWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/QusetionList.co")) { // QnA 리스트(/QnaList.co) 요청
			action = new QnadListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/QnaDetail.co")) { // QnA 리스트 상세정보(/QnaDetail.co) 요청
			action = new QnaDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/QnaSearch.co")) { // QnA 검색(/QnaSearch.co) 요청
			action = new QnaSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaModifyForm.co")) { // QnA 수정form(/QnaModifyForm.co) 요청
			// 비즈니스 로직 처리를 위해 BoardModifyFormAction 클래스의 execute() 메서드 호출
			action = new QnaModifyFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QnaModifyPro.co")) { // QnA 수정 (/QnaModifyPro.co) 요청
			action = new QnaModifyProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaReplyForm.co")) { // QnA 답글 (/QnaReplyForm.co) 요청
			action = new QnaReplyFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaReplyPro.co")) { // QnA 답글 (/QnaReplyPro.co) 요청
			action = new QnaReplyProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QnaDeletePro.co")) { //  QnA 삭제 (/QnaDeletePro.co) 요청
			action = new QnaDeleteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/MemberManagement.co")) { // 가입된 회원 목록 _이효민 06.12 확인
			action = new MemberManagementListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberDetail.co")) { // 회원의 상세 정보 _이효민 06.12 확인
			action = new MemberDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberUpdate.co")) { // 회원의 상태 변경 _이효민 06.12 확인
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberDelete.co")) { // 탈퇴회원의 삭제 요청 _이효민 06.21 확인
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Classification.co")) { // 상태와 등급에 따른 회원 목록 _이효민 06.12 확인
			action = new ClassificationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ClassificationDetail.co")) { // 상태와 등급에 회원의 상세 정보 _이효민 06.12 확인
			action = new ClassificationDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ClassificationUpdate.co")) { // 상태와 등급에 회원의 상태 변경 _이효민 06.12 확인
			action = new ClassificationUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ClassificationDelete.co")) { // 탈퇴회원 삭제(/ClassificationDelete.co) 요청 _이효민 06.21 확인
			action = new ClassificationDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Sales.co")) { // 매출관리 페이지(따로 DB 작업 필요 없습니다.) _이효민 06.12 확인
			forward =  new ActionForward("AdminPage/sales/sales_main.jsp", false);
		} else if (command.equals("/SalesChart.co")) { // 매출그래프를 위한 AJAX 작업용 _이효민 06.12 확인
			action = new SalesChartAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ProductConfirm.co")) { // 검수목록(/ProductConfirm.co)
			action = new ProductConfirmListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ProductSearch.co")) { // 검수 검색(/ProductSearch.co) 요청
			action = new ProductConfirmSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ProductConfirmDetail.co")) { // 검수 디테일(/ProductDetail.co) 요청
			action = new ProductConfirmDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/UpdateProductConfirm.co")) { // 검수 업데이트(/UpdateProductConfirm.co) 요청
			action = new UpdateProductConfirmAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ProductConfirmType.co")) { // 검수현황 리스트 (/ProductConfirmType.co) 요청
			action = new ProductConfirmTypeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// ActionForward 객체에 저장된 포워딩 정보에 따른 포워딩 작업 수행
		if (forward != null) { // ActionForward 객체가 비어있지 않을 경우
			// Redirect 방식 vs Dispatcher 방식 판별하여 각 방식으로 포워딩
			if (forward.isRedirect()) { // Redirect 방식
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			// ActionForward 객체가 비어있을 경우 메세지 출력(임시)
			System.out.println("ActionForward 객체가 null 입니다!");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
