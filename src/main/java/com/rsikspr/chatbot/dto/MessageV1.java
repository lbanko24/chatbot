package com.rsikspr.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageV1 {
    private String from;
    private String to;
    private String text;
}
