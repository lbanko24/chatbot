package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Message;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message save(Message message);

    List<Message> getAll();

    List<Message> getBetween(Date start, Date end);

    Optional<Message> getById(Long id);

    Message chat(String text, String sender, String destination);
}
