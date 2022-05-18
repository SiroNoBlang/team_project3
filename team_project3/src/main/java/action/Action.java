package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

//FrontController로 요청이 전달되면 Action클래스로 이동
//Action 클래스의 메서드를 호출하여 requset.response객체를 전달
//요청받은 작업을 진행한 후 작업 결과를 공통의 ActionForward 객체 형태로 리턴해야함
//=>Action 클래스에서 동일한 형태로 수행해야하므로 코드의 통일성 필요
//=>하나의 추상메서드로 제공하기 위해
//=>공통 항목인 execute() 메서드를 추상메서드로 정의한 뒤
//=>Action 인터페이스를 상속받아 execute()메서드를 구현하면
// 코드의 통일성이 향상. 다형성 활용도 가능

public interface Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception; 
		
	
}
