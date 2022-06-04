package action;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.SellerWriteProService;
import vo.ActionForward;
import vo.EventImgFileBean;
import vo.NoticeImgFileBean;
import vo.SellerDTO;
import vo.SellerimgDTO;

public class sellWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String uploadPath = "Upload/sell_img";

		int fileSize = 1024 * 1024 * 10; // byte(1) -> KB(1024Byte) -> MB(1024KB) -> 10MB 단위로 변환

		// request 객체의 getServletContext() 메서드를 호출
		ServletContext context = request.getServletContext();

		// ServletContext 객체의 getRealPath() 메서드를 호출
		String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달

		// D:\Developer\workspace_teamProject\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\team_project3\Upload\sell_img
		// => 실제 업로드 될 폴더 위치(워크스페이스 내의 프로젝트 폴더에 있는 upload 폴더는 가상 폴더)

		// MultipartRequest 객체 생성 시 request 객체를 전달하여 데이터를 관리해야함

		MultipartRequest multi = new MultipartRequest(
				request, // 1) 실제 요청 정보가 포함된 request 객체
				realPath, // 2) 실제 업로드 폴더 경로
				fileSize, // 3) 업로드 파일 크기(10MB 제한)
				"UTF-8", // 4) 한글 파일명에 대한 인코딩 방식
				new DefaultFileRenamePolicy()); // 5) 중복 파일명에 대한 이름 변경 처리를 담당하는 객체

		SellerDTO seller = new SellerDTO();
		String sell_member_code = request.getParameter("sell_member_code");

		seller.setSell_member_code(sell_member_code);
		seller.setSell_content(multi.getParameter("sell_content"));
		seller.setSell_title(multi.getParameter("sell_title"));
		seller.setSell_color(multi.getParameter("sell_color1") + "," + multi.getParameter("sell_color2"));
		seller.setSell_size(multi.getParameter("sell_size"));
		seller.setSell_brand(multi.getParameter("sell_brand"));
		// 주의! 파일 정보는 getParameter() 메서드로 가져올 수 없으며 별도의 추가 작업 필요
		seller.setSell_price(Integer.parseInt(multi.getParameter("sell_price")));
		seller.setSell_category(multi.getParameter("sell_category"));
		seller.setSell_category_detail(multi.getParameter("sell_category_detail"));
		seller.setSell_list_item_status("검수중"); // 글작업수행 후 검수중으로 바꿔줘야됨. 현재 관리자페이지 관여없어서 강제 판매중.

		ArrayList<SellerimgDTO> sellimglist = new ArrayList<SellerimgDTO>();

		Enumeration fileElements = multi.getFileNames();
		int i = 1;

		while (fileElements.hasMoreElements()) {

			String fileElement = (String) fileElements.nextElement();
			File file = multi.getFile(fileElement);

			if (file != null) {
				String sell_img_name = multi.getOriginalFileName(fileElement);
				String sell_img_real_name = multi.getFilesystemName(fileElement);

				SellerimgDTO sellimg = new SellerimgDTO();
				sellimg.setSell_img_num(i);
				sellimg.setSell_img_name(sell_img_name);
				sellimg.setSell_img_real_name(sell_img_real_name);

				sellimglist.add(sellimg);
				i++;
			}
		}

		SellerWriteProService service = new SellerWriteProService();
		boolean isWriteSuccess = service.registArticle(seller, sellimglist);

		if (!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글쓰기 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("MainPage.pr");
			forward.setRedirect(true);
		}

		return forward;
	}
}
