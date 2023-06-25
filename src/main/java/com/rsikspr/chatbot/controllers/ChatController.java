package com.rsikspr.chatbot.controllers;

import com.rsikspr.chatbot.dto.Content;
import com.rsikspr.chatbot.dto.MessageV1;
import com.rsikspr.chatbot.dto.MessageV2;
import com.rsikspr.chatbot.entity.Message;
import com.rsikspr.chatbot.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class ChatController {
    @Value("${chat.delay}")
    private long waitDuration;

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/v1/chat")
    public MessageV1 chat(@RequestBody MessageV1 message) {
        log.info("Got message from " + message.getFrom() + ": " + message.getText());

        long start = System.currentTimeMillis();
        Message response = messageService.chat(message.getText(), message.getFrom(), message.getTo());
        delay(waitDuration, start);

        return new MessageV1(response.getFrom(), message.getFrom(), response.getText());
    }

    @PostMapping("/v2/chat")
    public MessageV2 chat(@RequestBody MessageV2 message) {
        log.info("Got message from " + message.getFrom() + ": " + message.getContent().getText());

        long start = System.currentTimeMillis();
        Message response = messageService.chat(message.getContent().getText(), message.getFrom(), message.getTo());
        delay(waitDuration, start);

        return new MessageV2(response.getFrom(), message.getFrom(), new Content(response.getText()), response.getFrom());
    }

    private static void delay(long duration, long start) {
        long elapsed = System.currentTimeMillis() - start;
        long targetDuration = duration - elapsed;

        if (targetDuration >= 0) {
            try {
                Thread.sleep(targetDuration);
            } catch (InterruptedException exception) {
            }
        }
    }
}
