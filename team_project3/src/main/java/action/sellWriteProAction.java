package action;

import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.sellerWriteProService;
import vo.ActionForward;
import vo.SellerDTO;

public class sellWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("sellWriteProAction");
		ActionForward forward = null;
		

				String uploadPath = "Upload/sell_img";
				
				int fileSize = 1024 * 1024 * 10; // byte(1) -> KB(1024Byte) -> MB(1024KB) -> 10MB 단위로 변환

				// 3. 현재 프로젝트(서블릿)를 처리하는 객체인 서블릿 컨텍스트 객체 얻어오기
				// => request 객체의 getServletContext() 메서드를 호출
				ServletContext context = request.getServletContext();
				
				// 4. 업로드 할 파일이 저장되는 실제 경로를 얻어오기
				// => ServletContext 객체의 getRealPath() 메서드를 호출
				String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달
			  	System.out.println(realPath);
			  	
				// C:\Userseodnd\Desktop\workspace_team\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\team1Project\\/upload
				// => 실제 업로드 될 폴더 위치(워크스페이스 내의 프로젝트 폴더에 있는 upload 폴더는 가상 폴더)
				
				// 5. 작성된 게시물 정보는 폼 파라미터 형태로 전달되어 request 객체에 저장되어 있으므로
				//    해당 파라미터를 가져와서 BoardDTO 객체에 저장해야함
				//    단, multipart/form-data 형식이므로 MultipartRequest 객체를 통해 가져와야 하며
				//    MultipartRequest 객체 생성 시 request 객체를 전달하여 데이터를 관리해야함
				//    (cos.jar 라이브러리 필수!)
			
			  	
			  	MultipartRequest multi = new MultipartRequest(
						request, // 1) 실제 요청 정보가 포함된 request 객체
						realPath, // 2) 실제 업로드 폴더 경로 
						fileSize, // 3) 업로드 파일 크기(10MB 제한)
						"UTF-8", // 4) 한글 파일명에 대한 인코딩 방식 
						new DefaultFileRenamePolicy()); // 5) 중복 파일명에 대한 이름 변경 처리를 담당하는 객체

				SellerDTO seller= new SellerDTO();
			
				seller.setSell_content(multi.getParameter("sell_content"));
			    seller.setSell_title(multi.getParameter("sell_title"));
			    seller.setSell_color(multi.getParameter("sell_color"));
				seller.setSell_size(multi.getParameter("sell_size"));
				seller.setSell_brand(multi.getParameter("sell_brand"));
				// 주의! 파일 정보는 getParameter() 메서드로 가져올 수 없으며 별도의 추가 작업 필요
				seller.setSell_price(Integer.parseInt(multi.getParameter("sell_price")));
				seller.setSell_category(multi.getParameter("sell_category"));
				seller.setSell_category_detail(multi.getParameter("sell_category_detail"));
				seller.setSell_list_item_status("판매중");  //글작업수행 후 검수중으로 바꿔줘야됨. 현재 관리자페이지 관여없어서 강제 판매중.
				
	
	
				String fileElement = multi.getFileNames().nextElement().toString();
				System.out.println("fileElement"+fileElement);
				

			
			
				String sell_img_name = multi.getOriginalFileName(fileElement); 
				String sell_img_real_name = multi.getFilesystemName(fileElement);
				//2.sell_sub_image_name / sell_real_sub_image_name
				
				//String sell_sub_image_name = multi.getOriginalFileName(fileElement); 
				//3.sell_just_image_name /sell_real_just_image_name  //현재 다중파일 넘기기 불가   -->파일 한개만 넘기고 작업 수행 합니다.
				//System.out.println("원본 파일명 : " + seller_file + ", 실제 파일명 : " + seller_real_file);
	
				seller.setSell_img_name(sell_img_name);
				seller.setSell_img_real_name(sell_img_real_name);
				//System.out.println(seller);
				
				String sell_member_code = request.getParameter("sell_member_code");
				System.out.println(sell_member_code);
				
				sellerWriteProService service = new sellerWriteProService();
				boolean isWriteSuccess = service.registArticle(seller, sell_member_code);
				
				if(!isWriteSuccess) {
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


