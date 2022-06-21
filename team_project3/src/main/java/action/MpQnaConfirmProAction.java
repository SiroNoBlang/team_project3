package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import googleSMTPAuthenticator.MyMessageDigest;
import svc.QnaDeleteProService;
import vo.ActionForward;

public class MpQnaConfirmProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("MpQnaConfirmProAction");
		
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("page");
		
		
		//비밀번호 암호화
		String algorithm = "SHA-256";
		String qna_confirm = request.getParameter("qna_confirm"); // 평문 암호
		
		// MyMessageDigest 객체 생성 시 생성자에 암호알고리즘명과 평문암호 전달하여 암호화 수행
		MyMessageDigest mmd = new MyMessageDigest(algorithm, qna_confirm);
		
		// MyMessageDigest 객체의 getHashedData() 메서드를 호출하여 암호화 된 암호문을 리턴받아 출력
		String result = mmd.getHashedData();
		qna_confirm = result;
		
//		System.out.println(qna_confirm);
		
		
		QnaDeleteProService service = new QnaDeleteProService();
		boolean isArticleWriter = service.isArticleWriter(qna_num, qna_confirm);
		
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시물 확인 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else{
			forward = new ActionForward();
			forward.setPath("CommunityQnaModifyForm.ma?page=" + pageNum + "&qna_num="+qna_num);
			forward.setRedirect(true);
		}
	
		return forward;
	}

}
