package com.rsikspr.chatbot.repository;

import com.rsikspr.chatbot.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> getByInitiator(String initiator);

    List<Conversation> getByInitiatorAndActiveIsTrue(String initiator);

    List<Conversation> getByActiveIsTrue();

    List<Conversation> getByTimestampBetween(Date start, Date end);
}
