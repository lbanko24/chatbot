package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Message;

public interface ChatbotService {
    String respond(Message message);

}
