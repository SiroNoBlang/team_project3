package action;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

		String uploadPath = "Upload";
		int fileSize =1024 * 1024 * 10;

		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(uploadPath);
//		System.out.println(realPath);

		MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
	
		String communityType = multi.getParameter("communityType");
		System.out.println(communityType); //커뮤니티 카테고리 확인 작업
		
		CommunityBoardWriteProService service = new CommunityBoardWriteProService();
		boolean isWriteSuccess = false;
		
		
		String fileElement = "";
		String board_real_file = "";
		String board_file = "";
		File file = null;

		
		NoticeBean notice = null;
		NoticeImgFileBean noticeImg = null;
		ArrayList<NoticeImgFileBean> noticeImgList =  new ArrayList<NoticeImgFileBean>();
		
		EventBean event = null;
		EventImgFileBean eventImg =  null;
		ArrayList<EventImgFileBean> eventImgList = new ArrayList<EventImgFileBean>();
		
		Enumeration files = multi.getFileNames();
		
		//공지사항 글쓰기
		if(communityType.equals("notice")) {
			
			//공지사항 중에 첨부파일을 빼고 담을 NoticeBean
			notice = new NoticeBean();
			
			notice.setAdmin_notice_title(multi.getParameter("board_title"));
			notice.setAdmin_notice_nickname(multi.getParameter("board_nickname"));
			notice.setAdmin_notice_content(multi.getParameter("board_content"));

			
			// 첨부파일을 담을 noticeImg
			while (files.hasMoreElements()) {
				fileElement = files.nextElement().toString();
				file = multi.getFile(fileElement);
				
				if(file != null) { // 넘어오는 파일이 없으면

					board_real_file = multi.getFilesystemName(fileElement);
					board_file = multi.getOriginalFileName(fileElement);
					
					
					String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
					
			        String realFileName = now + board_real_file.substring(board_real_file.length());  //현재시간과 확장자 합치기
			        
			        System.out.println(realFileName);
			        
			        File oldFile = new File(realPath + board_real_file);
			        File newFile = new File(realPath + realFileName);
			       
			        oldFile.renameTo(newFile); // 파일명 변경
			        
			        
			        
					noticeImg = new NoticeImgFileBean();
					noticeImg.setNotice_img_file_name(board_file);
					noticeImg.setNotice_img_file_real_name(realFileName);
					
					noticeImgList.add(noticeImg);
					
					Path p1 = Paths.get(realPath, board_real_file); //원래 저장 폴더
					Path p2 = Paths.get(realPath, "admin_notice_img", realFileName); // 이동할 폴더 경로
					Files.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);
				}
			}
			
			isWriteSuccess = service.noticeRegistArticle(notice, noticeImgList);
			
		//이벤트 글쓰기	
		} else if (communityType.equals("event")){
			//이벤트 테이블 중에 첨부파일을 빼고 담을 NoticeBean
			event = new EventBean();
			
			event.setAdmin_event_title(multi.getParameter("board_title"));
			event.setAdmin_event_nickname(multi.getParameter("board_nickname"));
			event.setAdmin_event_content(multi.getParameter("board_content"));
			
			// 첨부파일을 담을 eventImg
			while (files.hasMoreElements()) {
				fileElement = files.nextElement().toString();
				file = multi.getFile(fileElement);
				
				if(file != null) {
				board_real_file = multi.getFilesystemName(fileElement);
				board_file = multi.getOriginalFileName(fileElement);
				
				eventImg = new EventImgFileBean();
				eventImg.setEvent_img_file_name(board_file);
				eventImg.setEvent_img_file_real_name(board_real_file);
				
				eventImgList.add(eventImg);
				
				Path p1 = Paths.get(realPath, board_real_file); 
				Path p2 = Paths.get(realPath, "admin_event_img", board_real_file);
				Files.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);
				
				
				}
			}
			
			isWriteSuccess = service.eventRegistArticle(event, eventImgList);
		}
		
		if (!isWriteSuccess) { // 실패시
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('다시 작성해주세요!')");
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
