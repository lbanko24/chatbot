package com.rsikspr.chatbot.controllers;

import com.rsikspr.chatbot.dto.TimestampRangeQuery;
import com.rsikspr.chatbot.entity.Conversation;
import com.rsikspr.chatbot.services.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conversations")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    public List<Conversation> getAllConversations() {
        return conversationService.getAll();
    }

    @GetMapping("/active")
    public List<Conversation> getActiveConversations() {
        return conversationService.getActive();
    }

    @GetMapping("/by/{initiator}")
    public List<Conversation> getConversationsByInitiator(@PathVariable String initiator) {
        return conversationService.getByInitiator(initiator);
    }

    @GetMapping("/by/{initiator}/active")
    public List<Conversation> getActiveConversationsByInitiator(@PathVariable String initiator) {
        return conversationService.getActiveByInitiator(initiator);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversationById(@PathVariable Long id) {
        Optional<Conversation> foundConversation = conversationService.getById(id);

        if (foundConversation.isPresent()) {
            return ResponseEntity.ok(foundConversation.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Void> closeConversationById(@PathVariable Long id) {
        Optional<Conversation> foundConversation = conversationService.getById(id);

        if (foundConversation.isPresent()) {
            conversationService.close(foundConversation.get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/between")
    public List<Conversation> getConversationsBetween(@RequestBody TimestampRangeQuery range) {
        return conversationService.getBetween(range.getStart(), range.getEnd());
    }
}
