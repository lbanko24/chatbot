package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageRouterServiceImpl implements MessageRouterService {

    @Autowired
    @Qualifier("aliceBot")
    private ChatbotService alicebotService;

    @Autowired
    @Qualifier("chatGPT")
    private ChatbotService chatgptService;

    @Override
    public Message forward(Message message, String destination) {
        Message response = new Message();

        switch (destination.toLowerCase()) {
            case "alice" -> {
                response.setText(alicebotService.respond(message));
                response.setFrom("Alice");
            }
            case "chatgpt" -> {
                response.setText(chatgptService.respond(message));
                response.setFrom("ChatGPT");
            }
            default -> {
                response.setText(alicebotService.respond(message));
                response.setFrom("Alice");

                if (doesntKnow(response.getText())) {
                    log.info("Alice didn't know, asking ChatGPT.");
                    response.setText(chatgptService.respond(message));
                    response.setFrom("ChatGPT");
                }
            }
        }

        return response;
    }

    private static boolean doesntKnow(String text) {
        return text.contains("didn't know")
                || text.contains("don't know")
                || text.contains("Google")
                || text.contains("<search>");
    }
}
