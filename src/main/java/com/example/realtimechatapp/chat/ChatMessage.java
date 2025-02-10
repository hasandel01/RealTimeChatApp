package com.example.realtimechatapp.chat;

import lombok.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;
    private LocalDate date;

}
