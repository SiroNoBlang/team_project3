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

import svc.CommunityModifyProService;
import vo.ActionForward;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeModifyProAction");
		ActionForward forward = null;
		
		String uploadPath = "Upload";
		int fileSize =1024 * 1024 * 10;
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(uploadPath);
//		System.out.println(realPath);
		
		MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		int notice_num = Integer.parseInt(multi.getParameter("notice_num"));
		int event_num = Integer.parseInt(multi.getParameter("notice_num"));

		String communityType = multi.getParameter("communityType");
		System.out.println(communityType); //커뮤니티 카테고리 확인 작업
		
		CommunityModifyProService service = new CommunityModifyProService();
		boolean isNoticeModifySuccess = false;
		boolean isEventModifySuccess = false;
		
		
		String fileElement = "";
		String board_real_file = "";
		String board_file = "";
		File file = null;

		
		String now = "";  //현재시간
	    int format = 0;
        String realFileName = "";  //현재시간과 확장자 합치기
        
//        System.out.println(realFileName);
        
        File oldFile = new File(realPath + board_real_file);
        File newFile = new File(realPath + realFileName);
       
        oldFile.renameTo(newFile); // 파일명 변경
        
		
		NoticeBean notice = null;
		NoticeImgFileBean noticeImg = null;
		ArrayList<NoticeImgFileBean> noticeImgList =  new ArrayList<NoticeImgFileBean>();
		ArrayList<NoticeImgFileBean> isNoticeImgExist =  new ArrayList<NoticeImgFileBean>();
		
		EventBean event = null;
		EventImgFileBean eventImg =  null;
		ArrayList<EventImgFileBean> eventImgList = new ArrayList<EventImgFileBean>();
		ArrayList<EventImgFileBean> isEventImgExist =  new ArrayList<EventImgFileBean>();
		
		Enumeration files = multi.getFileNames();
		
		//공지사항 수정
		if(communityType.equals("notice")) {
			
			//공지사항 중에 첨부파일을 빼고 담을 NoticeBean
			notice = new NoticeBean();
			
			notice.setNotice_title(multi.getParameter("board_title"));
			notice.setNotice_content(multi.getParameter("board_content"));

			// 업로된 사진이 있는 지 확인
			isNoticeImgExist = service.isImgExist(notice_num);
//			System.out.println("이건 리턴값이요"+isNoticeImgExist);
			
			
			if(!isNoticeImgExist.isEmpty()) {
				for(NoticeImgFileBean beforeImg:isNoticeImgExist) {
					
					// 삭제될 실제 사진을 가져오기
					String deleteImgName = realPath +"\\admin_notice_img\\" + beforeImg.getNotice_img_file_real_name();
//					System.out.println(deleteImgName);
					File deleteImg = new File (deleteImgName);
					
					//경로에 업로드된 사진이 있다면 삭제
			    	if (deleteImg.exists() && deleteImg.isFile()){
			    	    deleteImg.delete();// 사진 삭제
//			    	    System.out.println("삭제된 사진 : "+deleteImgName);
				    } 
				}
			} //if문 끝
	
	    	
			// 첨부파일을 담을 noticeImg
			while (files.hasMoreElements()) {
				fileElement = files.nextElement().toString();
				file = multi.getFile(fileElement);
				
				if(file != null) { // 넘어오는 파일이 null 아닐때만 저장

					board_real_file = multi.getFilesystemName(fileElement);
					board_file = multi.getOriginalFileName(fileElement);
					
					now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
				    format = board_real_file.lastIndexOf("."); // 파일 확장자 위치
			        realFileName = now + board_real_file.substring(format,board_real_file.length());  //현재시간과 확장자 합치기
			        
			        oldFile = new File(realPath + board_real_file);
			        newFile = new File(realPath + realFileName);
			       
			        oldFile.renameTo(newFile); // 파일명 변경
			        
					noticeImg = new NoticeImgFileBean();
					noticeImg.setNotice_img_file_name(board_file);
					noticeImg.setNotice_img_file_real_name(realFileName);
					
					noticeImgList.add(noticeImg);
					
//					System.out.println(noticeImgList);
					
					Path p1 = Paths.get(realPath, board_real_file); //원래 저장 폴더
					Path p2 = Paths.get(realPath, "admin_notice_img", realFileName); // 이동할 폴더 경로
					Files.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);
				}
			}
			
			isNoticeModifySuccess = service.noticeModifyArticle(notice_num,notice, noticeImgList);
			
		//이벤트 수정	
		} else if (communityType.equals("event")){
			//이벤트 테이블 중에 첨부파일을 빼고 담을 NoticeBean
			event = new EventBean();
			
			event.setEvent_title(multi.getParameter("board_title"));
			event.setEvent_content(multi.getParameter("board_content"));
			
			
			// 업로된 사진이 있는 지 확인
			isEventImgExist = service.isEventImgExist(event_num);
//			System.out.println("이건 리턴값이요"+isEventImgExist);
			
			
			if(!isEventImgExist.isEmpty()) {
				for(EventImgFileBean beforeImg:isEventImgExist) {
					
					// 삭제될 실제 사진을 가져오기
					String deleteImgName = realPath +"\\admin_event_img\\" + beforeImg.getEvent_img_file_real_name();
//					System.out.println(deleteImgName);
					File deleteImg = new File (deleteImgName);
					
					//경로에 업로드된 사진이 있다면 삭제
			    	if (deleteImg.exists() && deleteImg.isFile()){
			    	    deleteImg.delete();// 사진 삭제
//			    	    System.out.println("삭제된 사진 : "+deleteImgName);
				    } 
				}
			} //if문 끝
			
			
			// 첨부파일을 담을 eventImg
			while (files.hasMoreElements()) {
				fileElement = files.nextElement().toString();
				file = multi.getFile(fileElement);
				
				if(file != null) {
					board_real_file = multi.getFilesystemName(fileElement);
					board_file = multi.getOriginalFileName(fileElement);
					
					
					now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
				    format = board_real_file.lastIndexOf("."); // 파일 확장자 위치
			        realFileName = now + board_real_file.substring(format,board_real_file.length());  //현재시간과 확장자 합치기
			        
			        
			        oldFile = new File(realPath + board_real_file);
			        newFile = new File(realPath + realFileName);
			       
			        oldFile.renameTo(newFile); // 파일명 변경
	
			        
					eventImg = new EventImgFileBean();
					eventImg.setEvent_img_file_name(board_file);
					eventImg.setEvent_img_file_real_name(realFileName);
					
					eventImgList.add(eventImg);
					
					
					Path p1 = Paths.get(realPath, board_real_file); 
					Path p2 = Paths.get(realPath, "admin_event_img", realFileName);
					Files.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);
					
				}
			}
			
			isEventModifySuccess = service.eventModifyArticle(event_num, event, eventImgList);
		}
		
		
		if (isNoticeModifySuccess) { 
			forward = new ActionForward();
			forward.setPath("NoticeList.co?page=" + multi.getParameter("page"));
			forward.setRedirect(true);
		} else if (isEventModifySuccess) {
			forward = new ActionForward();
			forward.setPath("EventList.co?page=" + multi.getParameter("page"));
			forward.setRedirect(true);
		} else {// 실패시
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패 하셨습니다!!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}