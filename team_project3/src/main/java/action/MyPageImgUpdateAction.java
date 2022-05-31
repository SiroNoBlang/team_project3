package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import svc.MyPageUpdateImgService;
import vo.ActionForward;
import vo.MemberBean;

public class MyPageImgUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MyPageImgUpdateAction");
		
		ActionForward forward = null;
		String saveDirectory = request.getServletContext().getRealPath("Upload/mypage_img");
		File saveDir = new File(saveDirectory);
		if(!saveDir.exists())
			saveDir.mkdirs();
		int fileSize = 1024 * 1024 * 10;
		
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String fileElement = multi.getFileNames().nextElement().toString();
		
		String member_code = multi.getParameter("member_code");
		System.out.println(member_code);
		String member_info_mypage_img_name = multi.getOriginalFileName(fileElement); 
		String member_info_mypage_real_img_name = multi.getFilesystemName(fileElement); 
		
		MemberBean member = new MemberBean();
		member.setMember_code(member_code);
		member.setMember_info_mypage_img_name(member_info_mypage_img_name);
		member.setMember_info_mypage_real_img_name(member_info_mypage_real_img_name);
		
		MyPageUpdateImgService service = new MyPageUpdateImgService();
		boolean isImgUpdate = service.imgUpdate(member);
		
		if(!isImgUpdate) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("MainPage/my_page/about_mypage.jsp");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
