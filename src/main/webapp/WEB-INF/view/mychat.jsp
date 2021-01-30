<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 21. 1. 6.
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/mainchat.css" type="text/css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

    <title>Title</title>
</head>
<body>
<header>
    <h2>채팅방에 오신것을 환영합니다!</h2>
    <span>핑핑이의 채팅방</span>
</header>
<section>
    <div id="container">
        <div id="chatbox">
            <!-- 주고받은 메시지들 -->
            <textarea name="박스" id="messagebox" cols="30" rows="10">
              </textarea>
        </div>
        <div id="chatmenu">
            <input id="setting" type="button" value="설정">
            <input type="text" name="채팅창" id="inputmessage">
            <button type="submit" id="send">보내기</button>
        </div>
    </div>
</section>
<footer>
    <span>Copyright@ PingPings</span>
</footer>
<script type="text/javascript">
    var sock = new SockJS("/chat");
    var send_btn = $('#send');

    sock.onopen = onOpen;
    sock.onmessage = onMessage;
    sock.onclose = onClose;

    function onOpen() {
        $('#messagebox').append("연결 성공!\n");
    }
    function sendMessage() {
        sock.send($('#inputmessage').val());
        console.log('메세지가 보내짐');
    }
    send_btn.click(function () {
        sendMessage();
        $('#inputmessage').val('');
        console.log('클릭만 처리됨');
    })
    function onMessage(msg) {
       var data = msg.data;
        $('#messagebox').append(data+"</br>");
        console.log('메세지를 받음');
   }
    function onClose(e) {
        $('#messagebox').append("연결 종료\n");
    }
</script>
</body>
</html>
