package action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductLikeService;
import vo.ActionForward;

public class ProductRecUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("좋아요 기능-UPDATE");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = null;
		String sCode = request.getParameter("id");
		int sell_num = Integer.parseInt((request.getParameter("sell_num")));
		
		
		// no와 id값을 map에 저장
//		Map<String, String> m = new HashMap<>();
//		m.put("sCode", request.getParameter("id"));
//		m.put("sell_num", request.getParameter("sell_num"));
//		System.out.println(m);
		ProductLikeService service = new ProductLikeService();
		int result =service.recCheck(sCode,sell_num);  //회원정보와 판매글을 이용하여 좋아요를 체크(Y)취소(N)을 했는지 구분함
		
		if(result ==0) {
			service.recUpdate(sCode,sell_num); //좋아요 체크  result ==0 일시, result =1 로  (INSERT)테이블에 sCode & sell_num 
			System.out.println("좋아요~");
		}else {
			service.reDelete(sCode,sell_num);  //좋아요 취소	result =1 일시, result =0 으로 (DELETE)테이블에 sCode & sell_num 
			System.out.println("좋아요 취소~");
		}
		return null;
	}

}
