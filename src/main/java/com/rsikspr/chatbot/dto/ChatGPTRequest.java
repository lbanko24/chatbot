package com.rsikspr.chatbot.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<ChatGPTMessage> messages;
    private Integer max_tokens;
}
