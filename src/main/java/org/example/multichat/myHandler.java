package org.example.multichat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class myHandler extends TextWebSocketHandler {
    private static List<WebSocketSession> webSocketSessionList = new ArrayList<>();

    // 커넥션 맺은 후(세션 리스트에 추가)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionList.add(session);

        // 세션이 존재하지 않음! 오류!!! --> 스프링 시큐리티를 사용할 것!
        System.out.println(session.getId() + " 님이 입장하셨습니다.");
        for (WebSocketSession wsSession : webSocketSessionList) {
            wsSession.sendMessage(new TextMessage(session.getId() + " 님이 입장하셨습니다.\n"));
        }
    }

    // 메시지 다루기
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession wsSession : webSocketSessionList) {
            wsSession.sendMessage(new TextMessage(session.getId() + "> " +message.getPayload() + "\n"));
        }
    }

    // 에러 다루기
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    // 연결이 끊긴 후(채팅방을 나갔으므로, 세션 리스트에서 삭제)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        /*for (WebSocketSession wsSession : webSocketSessionList) {
            wsSession.sendMessage(new TextMessage(session.getId() + " 님이 퇴장하셨습니다.\n"));
        }*/
        super.afterConnectionClosed(session, status);
        webSocketSessionList.remove(session);
    }
}
