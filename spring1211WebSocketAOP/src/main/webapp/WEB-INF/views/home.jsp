<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹 소켓과 AOP</title>
<style>
	#chatarea{
		width:200px;
		height:100px;
		overflow-y:auto;
		border:1px solid black;
	}
</style>
</head>
<body>
	<input type="button" value="ECHO 테스트" id="btn" /><br/>
	
	<div>
		<h2>채팅</h2>
		이름<input type="text" id="name"/><br/>
		<br/>
		메시지<input type="text" id="msg" />
		<input type="button" value="전송" id="sendbtn"/>
		<br/><br/>
		<h3>채팅 내용</h3>
		<div id="chatarea">
			<div id="chatmessagearea"></div>
		</div>
	</div>
	
</body>
	<!-- 채팅 관련 스크립트 -->
	<script>http:
		//웹 소켓 연결 
		var socket = new WebSocket("ws://localhost:8080/chatting/chatting-ws");
		//채팅 내용을 출력할 영역
		var chatmessagearea = document.getElementById("chatmessagearea");
		//웹 소켓 서버에 연결되었을 때 호출될 메소드
		socket.addEventListener("open", function(e){
			chatmessagearea.innerHTML = 
				"웹 소켓에 접속</br>" + chatmessagearea.innerHTML
		});
		//웹 소켓 서버에서 해제되었을 때 호출될 메소드 
		socket.addEventListener("close", function(e){
			chatmessagearea.innerHTML = 
				"웹 소켓에 접속</br>" + chatmessagearea.innerHTML
		});
		//웹 소켓 서버에서 메시지가 왔을 때 호출될 메소드 
		socket.addEventListener("message", function(e){
			chatmessagearea.innerHTML = 
				e.data +"</br>" + chatmessagearea.innerHTML
		});
		
		//전송 버튼을 눌렀을 때 수행할 내용 
		document.getElementById("sendbtn").addEventListener(
				'click', function(e){
			//메시지 만들기
			var msg = '';
			//이름 가져오기
			msg = document.getElementById("name").value;
			//메시지 결합
			msg = msg + ":" + document.getElementById("msg").value;
			document.getElementById("msg").value='';
			//메시지 전송
			socket.send(msg);
		});
	</script>

	<!-- 에코 관련 스크립트 -->
	<script>
		var webdocket;
		
		document.getElementById('btn').addEventListener('click', function(e){
			//웹 소켓 서버에 연결 
			websocket = new WebSocket(
				'ws://localhost:8080/chatting/echo-ws');	
			
			//웹 소켓이 연결되면 
			websocket.addEventListener("open", function(e){
				//데이터를 전송 
				websocket.send('안녕하세요');
			});
			
			//메시지를 받으면 호출되는 콜백 함수 
			websocket.addEventListener("message", function(e){
				//서버가 보내준 데이터 읽기
				alert(e.data);
				//연결 해제 
				websocket.close();
			});
		});
	</script>
</html>