package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Message;

public interface MessageRouterService {
    Message forward(Message message, String destination);
}
