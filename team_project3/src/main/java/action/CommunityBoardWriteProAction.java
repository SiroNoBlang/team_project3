package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.CommunityBoardWriteProService;
import vo.ActionForward;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class CommunityBoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityBoardWriteProAction");
		ActionForward forward = null;

		String uploadPath = "upload";
		int fileSize = 1024 * 1024 * 10;

		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(uploadPath);
		System.out.println(realPath);

		MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Enumeration files = multi.getFileNames();
		
		System.out.println("-------------------");
		while (files.hasMoreElements()) {
			String fileElement = files.nextElement().toString();
			String board_real_file = multi.getFilesystemName(fileElement);
			String board_file = multi.getOriginalFileName(fileElement);
			
			System.out.println(fileElement);
			System.out.println(board_real_file);
			System.out.println(board_file);
		}
		System.out.println("-------------------");
		
		
		String communityType = multi.getParameter("communityType");
//		System.out.println(communityType); //커뮤니티 카테고리 확인 작업
		
		CommunityBoardWriteProService service = new CommunityBoardWriteProService();
		boolean isWriteSuccess = false;
		NoticeImgFileBean noticeImg = new NoticeImgFileBean();
		ArrayList<NoticeImgFileBean> noticeImgList = new ArrayList<NoticeImgFileBean>();
		
		
		if(communityType.equals("notice")) {
			//공지사항 중에 첨부파일을 빼고 담을 NoticeBean
			NoticeBean notice = new NoticeBean();
			notice.setAdmin_notice_title(multi.getParameter("board_title"));
			notice.setAdmin_notice_nickname(multi.getParameter("board_nickname"));
			notice.setAdmin_notice_content(multi.getParameter("board_content"));
			
			// 첨부파일을 담을 noticeImg

			Enumeration files2 = multi.getFileNames();
			
			while (files2.hasMoreElements()) {
				String fileElement = files2.nextElement().toString();
				String board_real_file = multi.getFilesystemName(fileElement);
				String board_file = multi.getOriginalFileName(fileElement);
				
				
				noticeImg = new NoticeImgFileBean();
				noticeImg.setNotice_img_file_name(board_file);
				noticeImg.setNotice_img_file_real_name(board_real_file);
				
				
//				noticeImgList.add(noticeImg);
				
				System.out.println(noticeImg);
				System.out.println(fileElement);
				System.out.println(board_real_file);
				System.out.println(board_file);
			}
			
//			System.out.println(noticeImgList);
			
		//확인작업			
		//		System.out.println("공지사항내용 : "+notice);
		//		System.out.println("공지사항내용 : "+noticeImg);
		
			isWriteSuccess = service.noticeRegistArticle(notice, noticeImg);
			
		} else if (communityType.equals("event")){
			
			//이벤트 테이블 중에 첨부파일을 빼고 담을 NoticeBean
			EventBean event = new EventBean();
			event.setAdmin_event_title(multi.getParameter("board_title"));
			event.setAdmin_event_nickname(multi.getParameter("board_nickname"));
			event.setAdmin_event_content(multi.getParameter("board_content"));
			
			// 첨부파일을 담을 eventImg
			EventImgFileBean eventImg = new EventImgFileBean();
			
			String fileElement = multi.getFileNames().nextElement().toString();
			String board_real_file = multi.getFilesystemName(fileElement);
			String board_file = multi.getOriginalFileName(fileElement);
			
			
			eventImg.setEvent_img_file_name(board_file);
			eventImg.setEvent_img_file_real_name(board_real_file);
			
			
			
			isWriteSuccess = service.eventRegistArticle(event, eventImg);
			
			//확인작업			
			//		System.out.println("공지사항내용 : "+event);
			//		System.out.println("공지사항내용 : "+eventImg);
			
		}
		
	
		

		if (!isWriteSuccess) { // 실패시
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글쓰기 실패..왜...아 지ㄴ..짜...! 싸우자...')");
			out.println("history.back()");
			out.println("</script>");

		} else {
			forward = new ActionForward();
			forward.setPath("NoticeList.co");
			forward.setRedirect(true);
		}

		return forward;

	}

}
