package action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vo.ActionForward;

public class ChatAction extends JFrame implements Action {

	private JLabel lblStatus;
	private JTextArea ta;
	private JTextField tf;
	
	public ChatAction() {
		showFrame();
	}

	public void showFrame() {
		setTitle("1:1 채팅"); // 프레임 제목 표시줄
		setBounds(400, 400, 500, 300); // x좌표, y 좌표, 가로 크기, 세로크기
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 닫기버튼 기본 기능 설정
		
		// 프레임(채팅창)의 상단 (BorderLayout의 NORTH 영역) 에 상태표시창 표시
		lblStatus = new JLabel("클라이언트 연결 상태 : 없음");
		add(lblStatus, BorderLayout.NORTH);
		
		// 채팅 메세지를 표시할 JTextArea
		ta = new JTextArea();
		ta.setEditable(false); // 텍스트 입력 금지 설정
		ta.setBackground(Color.LIGHT_GRAY);
		add(ta, BorderLayout.CENTER);
		
		// 채팅 메세지를 입력할 JTextField 생성 및 SOUTH 영역에 표시
		tf = new JTextField();
		add(tf, BorderLayout.SOUTH);
		
		// 텍스트 필드에서 엔터키 입력 시 동작하는 이벤트 - ActionListener 활용
		tf.addActionListener(e -> sendMessage());
		
		
		
		
		setVisible(true); // 프레임 화면에 표시
		
		tf.requestFocus(); // 텍스트 필드에 커서 요청
	}
	
	// 입력 받은 메세지 전송을 수행하는 sendMessage() 메서드 정의
	public void sendMessage() {
		// 입력받은 메세지 가져오기
		String msg = tf.getText();
		System.out.println(msg);
		
		// 텍스트필드 입력값 초기화 및 커서 요청
		tf.setText("");
		tf.requestFocus();
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

}
