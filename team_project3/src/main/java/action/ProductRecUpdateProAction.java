package action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import svc.ProductLikeService;
import vo.ActionForward;

public class ProductRecUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = null;
		String sCode = request.getParameter("id");
		int sell_num = Integer.parseInt((request.getParameter("sell_num")));
		int likeCount =0;
		
		ProductLikeService service = new ProductLikeService();
		int result =service.recCheck(sCode,sell_num);  //회원정보와 판매글을 이용하여 좋아요를 체크(Y)취소(N)을 했는지 구분함
		
		if(result ==0) {
			service.recUpdate(sCode,sell_num); //좋아요 체크  result ==0 일시, result =1 로  (INSERT)테이블에 sCode & sell_num 
			System.out.println("좋아요~");
		}else {
			service.reDelete(sCode,sell_num);  //좋아요 취소	result =1 일시, result =0 으로 (DELETE)테이블에 sCode & sell_num 
			System.out.println("좋아요 취소~");
		}
		
		ProductLikeService service1 = new ProductLikeService();
		
		likeCount = service1.likeCount(sell_num);  //좋아요 갯수를 카운트 해주는 기능
		PrintWriter out = response.getWriter();
		
		out.print("{\"likeCount\":\""+likeCount); 
		out.print("\",\"result\":\""+result+"\"}");   //result =1 좋아요 result =0좋아요 취소
		return forward;
	}

}
