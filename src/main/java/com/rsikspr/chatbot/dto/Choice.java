package com.rsikspr.chatbot.dto;

import lombok.Data;

@Data
public class Choice {
    public int index;
    public ChatGPTMessage message;
    public String finish_reason;
}
