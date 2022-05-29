package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaModifyProService;
import svc.UpdateProductConfirmService;
import vo.ActionForward;
import vo.SellerProductDTO;

public class UpdateProductConfirmAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("UpdateProductConfirmAction");
		ActionForward forward = null;
		
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		String sell_list_item_status = request.getParameter("cmStatus");
		String sell_list_approve_nickname = request.getParameter("sNname");
		
//		System.out.println(sell_num);
//		System.out.println(sell_list_item_status);
//		System.out.println(sell_list_approve_nickname);
		
		SellerProductDTO confirm = new SellerProductDTO();
		confirm.setSell_num(sell_num);
		confirm.setSell_list_item_status(sell_list_item_status);
		confirm.setSell_list_approve_nickname(sell_list_approve_nickname);
		
		
		UpdateProductConfirmService service = new UpdateProductConfirmService();
		boolean isUpdateSuccess = service.confirmUpdate(confirm);
		
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('검수확인 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("ProductConfirm.co");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
