package naver.yh_park.chatting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChattingHandler extends TextWebSocketHandler {
	//클라이언트들의 웹 소켓 세션을 저장할 List를 생성 
	private static List<WebSocketSession> list =
			new ArrayList<WebSocketSession>();
	//단순한 채팅이 아니고 그룹채팅이나 귓말 등이 가능하도록 하려면 
	//WebSocketSession 대신에 Map이나 DTO를 이용 
	
	@Override
	//클라이언트가 연결되었을 때 호출될 메소드 
	public void afterConnectionEstablished(
			WebSocketSession webSocketSession) {
		//클라이언트를 List에 저장 
		list.add(webSocketSession);
	}
	
	@Override
	//클라이언트의 접속이 종료되었을 때 호출될 메소드 
	public void afterConnectionClosed(
		WebSocketSession webSocketSession,
			CloseStatus status) {
		//List에서 제거 
		list.remove(webSocketSession);
	}
	
	@Override
	//클라이언트가 메시지를 전송했을 때 호출되는 메소드
	public void handleTextMessage(
			WebSocketSession webSocketSession,
			TextMessage message) {
		//전송된 메시지 가져오기 
		String msg = message.getPayload();
		//List의 모든 세션에게 메시지를 전송 
		try {
			for(WebSocketSession ses : list) {
				ses.sendMessage(new TextMessage(msg));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
