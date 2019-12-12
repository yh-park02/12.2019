package naver.yh_park.chatting.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
//Echo 전송을 위한 웹 소켓 클래스 

//객체를 자동으로 생성해주는 어노테이션 
//이 어노테이션이 없으면 객체 생성이 안되기 때문에 연결이 안된다. 
@Component
public class EchoHandler extends TextWebSocketHandler {

	@Override
	//클라이언트가 접속했을 때 호출되는 메소드
	public void afterConnectionEstablished(WebSocketSession session) {
		//접속한 클라이언트의 ID 출력 
		System.err.println("접속한 클라이언트:" + session.getId());
	}
	
	@Override
	//클라이언트가 접속해제되었을 때 호출되는 메소드
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.err.println("접속 해제한 클라이언트:" + session.getId());
	}
	
	@Override
	//클라이언트로부터 메시지가 왔을 때 호출되는 메소드
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//메시지 출력 
		System.err.println(session.getId() + ":" + message.getPayload());
		//메시지를 클라이언트에게 다시 전송 
		try {
			session.sendMessage(new TextMessage("반사:" + message.getPayload()));
		}catch(Exception e) {}
	}
}