package com.rsikspr.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageV2 {
    private String from;
    private String to;
    private Content content;
    private String displayName;
}
