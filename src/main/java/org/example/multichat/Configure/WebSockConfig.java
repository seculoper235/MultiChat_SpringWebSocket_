package org.example.multichat.Configure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/*
 * WebAppconfig.xml에 작성한 내용을 여기다 적어준다(viewResolver 제외)
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSockConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/chat")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
