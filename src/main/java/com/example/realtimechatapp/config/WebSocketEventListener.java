package com.example.realtimechatapp.config;


import com.example.realtimechatapp.chat.ChatMessage;
import com.example.realtimechatapp.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    public final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    ) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username");

            if (username != null) {
                log.info("User disconnected: " + username);
                var chatMessage = ChatMessage.builder()
                        .type(MessageType.LEAVE)
                        .date(LocalDate.now())
                        .sender(username)
                        .build();
                messagingTemplate.convertAndSend("/topic/public", chatMessage);
            }
    }
}
