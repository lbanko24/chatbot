package com.rsikspr.chatbot.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatGPTResponse {
    public String id;
    public String object;
    public int created;
    public List<Choice> choices;
    public Usage usage;
}
