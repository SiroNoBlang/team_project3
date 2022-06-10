package action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.ProductLikeService;
import vo.ActionForward;

public class ProductRecCountProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int likeCount =0;
		int sell_num = Integer.parseInt((request.getParameter("sell_num")));   //판매 번호로 좋아요 갯수찾기
		ActionForward forward = null;
		
		ProductLikeService service = new ProductLikeService();
		
		likeCount = service.likeCount(sell_num);
		System.out.println(likeCount);
		PrintWriter out = response.getWriter();
		out.print(likeCount);
	
		out.println(likeCount);
//		out.close();
		
		
		//이파일 곧 삭제 예정.
		
		return null;
	}

}
