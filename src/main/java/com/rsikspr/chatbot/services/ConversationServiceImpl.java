package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Conversation;
import com.rsikspr.chatbot.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {
    private final ConversationRepository conversationRepository;
    private final BillingService billingService;

    public ConversationServiceImpl(ConversationRepository conversationRepository, BillingService billingService) {
        this.conversationRepository = conversationRepository;
        this.billingService = billingService;
    }

    @Override
    public List<Conversation> getAll() {
        return conversationRepository.findAll();
    }

    @Override
    public Optional<Conversation> getById(Long id) {
        return conversationRepository.findById(id);
    }

    private Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation initConversation(String initiator) {
        Conversation conversation = new Conversation();
        List<Conversation> activeConversations = conversationRepository.getByInitiatorAndActiveIsTrue(initiator);

        if (activeConversations.isEmpty()) {
            conversation.setInitiator(initiator);
            conversation = save(conversation);
        } else {
            conversation = activeConversations.get(0);
        }

        return conversation;
    }

    @Override
    public List<Conversation> getActive() {
        return conversationRepository.getByActiveIsTrue();
    }

    @Override
    public List<Conversation> getByInitiator(String initiator) {
        return conversationRepository.getByInitiator(initiator);
    }

    @Override
    public List<Conversation> getActiveByInitiator(String initiator) {
        return conversationRepository.getByInitiatorAndActiveIsTrue(initiator);
    }

    @Override
    public List<Conversation> getBetween(Date start, Date end) {
        return conversationRepository.getByTimestampBetween(start, end);
    }

    @Override
    public void close(Conversation conversation) {
        conversation.setActive(false);
        billingService.createBilling(conversation.getInitiator());

        save(conversation);
    }
}
