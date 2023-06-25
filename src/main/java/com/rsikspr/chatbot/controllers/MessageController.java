package com.rsikspr.chatbot.controllers;

import com.rsikspr.chatbot.dto.TimestampRangeQuery;
import com.rsikspr.chatbot.entity.Message;
import com.rsikspr.chatbot.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAll();
    }

    @GetMapping("/between")
    public List<Message> getMessagesBetween(@RequestBody TimestampRangeQuery range) {
        return messageService.getBetween(range.getStart(), range.getEnd());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> foundMessage = messageService.getById(id);

        if (foundMessage.isPresent()) {
            return ResponseEntity.ok(foundMessage.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
