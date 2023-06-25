package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Conversation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConversationService {

    List<Conversation> getAll();

    Optional<Conversation> getById(Long id);

    void close(Conversation conversation);

    Conversation initConversation(String initiator);

    List<Conversation> getActive();

    List<Conversation> getByInitiator(String initiator);

    List<Conversation> getActiveByInitiator(String initiator);

    List<Conversation> getBetween(Date start, Date end);
}
