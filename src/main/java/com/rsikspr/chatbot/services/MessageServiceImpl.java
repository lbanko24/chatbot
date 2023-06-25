package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Conversation;
import com.rsikspr.chatbot.entity.Message;
import com.rsikspr.chatbot.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final MessageRouterService routerService;

    public MessageServiceImpl(MessageRepository messageRepository, ConversationService conversationService,
                              MessageRouterService routerService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
        this.routerService = routerService;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getBetween(Date start, Date end) {
        return messageRepository.findByTimestampBetween(start, end);
    }

    @Override
    public Optional<Message> getById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message chat(String text, String sender, String destination) {
        Message message = new Message();
        message.setText(text);
        message.setFrom(sender);

        Conversation conversation = conversationService.initConversation(sender);

        message.setConversation(conversation);
        save(message);

        Message response = routerService.forward(message, destination);
        response.setConversation(conversation);
        return save(response);
    }
}
