package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ContactAction;
import action.ProductCategoryProAction;
import action.ProductDetailProAction;
import action.ProductListProAction;
import action.ProductSearchProAction;
import action.SellRecentPostAddressAction;
import action.SendAdminAction;
import action.ShopingProAction;
import action.ShoppoingSucceedAction;
import action.sellWriteProAction;
import vo.ActionForward;

@WebServlet("*.pr")
public class ProductFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;
		
		if (command.equals("/MainPage.pr")) {// 로그인 후 메인 페이지
			forward = new ActionForward();
			forward.setPath("MainPage/first_page/index.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/SellForm.pr")) {// 상품 판매글(FORM.JSP)

			forward = new ActionForward();
			forward.setPath("MainPage/sell/sell_write.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/SellWritePro.pr")) {// 상품 판매글(비즈니스로직작업요청) 작성

			action = new sellWriteProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/Product.pr")) {// 상품 갯수 처리를 위한 페이징처리
			action = new ProductListProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ProductCategoryPro.pr")) { // 상품 카테고리 분류 처리
			action = new ProductCategoryProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/Product_list.pr")) {// 상품 판매목록(product_list.jsp)

			forward = new ActionForward();
			forward.setPath("MainPage/sell/product_list.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/ProductDetailPro.pr")) {// 상품 상세조회(ProductDetailProAction.java)
			
			action = new ProductDetailProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ProductSearchPro.pr")) {// 상품 상세조회(ProductDetailProAction.java)
			
			action = new ProductSearchProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		}else if (command.equals("/ProductDetailForm.pr")) {// 상품 판매목록 상세(product_detail.jsp)

			forward = new ActionForward();
			forward.setPath("MainPage/sell/product_detail.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/ShopingForm.pr")) {// 쇼핑카트 오더(shoping_cart.jsp)

			forward = new ActionForward();
			forward.setPath("MainPage/sell/shoping_cart.jsp");
			forward.setRedirect(false);
		
		} else if (command.equals("/ProductDetailPro.pr")) {// 상품 상세조회(ProductDetailProAction.java)
		
		action = new ShopingProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		}else if (command.equals("/ShopingPro.pr")) {// 쇼핑카트 액션  <곧 구매 버튼 선택 후 결제하기 들어갑니다.>
			
			action = new ShopingProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (command.equals("/SellRecentPostAddressAction.pr")) {// 배송지 주소 찾기 액션  
			
			action =  new SellRecentPostAddressAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (command.equals("/SellRecentPostAddress.pr")) {// 배송지 주소 찾기 jsp

			forward = new ActionForward();
			forward.setPath("MainPage/sell/recentPostAddress.jsp");
			forward.setRedirect(false);
		
		}else if (command.equals("/SucceedProduct.pr")) {// 쇼핑 결제 완료 페이지

			forward = new ActionForward();
			forward.setPath("MainPage/sell/succeedProduct.jsp");
			forward.setRedirect(false);
		
		} else if (command.equals("/SucceedProductAction.pr")) {// 쇼핑 결제 완료 페이지(Action에서 UPDATE 작업 진행 후 .JSP(file)로 값 가져옴)

			action = new ShoppoingSucceedAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		} else if (command.equals("/SucceedProduct.pr")) {// 쇼핑 결제 완료 페이지

			forward = new ActionForward();
			forward.setPath("MainPage/sell/succeedProduct.jsp");
			forward.setRedirect(false);
		
		} else if (command.equals("/Contact.pr")) {//

			action = new ContactAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if (command.equals("/SendAdmin.pr")) {// 

			action = new SendAdminAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} 

	//----------------------------------------------------------------------------------------------		      
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {

			System.out.println("ActionForward  null입니다");
		}
	}

	// --------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}

}
