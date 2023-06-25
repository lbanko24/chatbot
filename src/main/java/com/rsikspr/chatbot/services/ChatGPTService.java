package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.dto.ChatGPTMessage;
import com.rsikspr.chatbot.dto.ChatGPTRequest;
import com.rsikspr.chatbot.dto.ChatGPTResponse;
import com.rsikspr.chatbot.entity.Conversation;
import com.rsikspr.chatbot.entity.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("chatGPT")
public class ChatGPTService implements ChatbotService {
    private static final Integer MAX_TOKENS = 70;
    private static final String OPEN_AI_CHAT_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public String respond(Message message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setModel("gpt-3.5-turbo");

        List<ChatGPTMessage> messages = getHistory(message.getConversation());
        messages.add(new ChatGPTMessage("user", message.getText()));

        chatGPTRequest.setMessages(messages);
        chatGPTRequest.setMax_tokens(MAX_TOKENS);

        HttpEntity<ChatGPTRequest> request = new HttpEntity<>(chatGPTRequest, headers);

        ChatGPTResponse response = restTemplate.postForObject(OPEN_AI_CHAT_ENDPOINT, request, ChatGPTResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
    }

    private static List<ChatGPTMessage> getHistory(Conversation conversation) {
        List<ChatGPTMessage> history = new ArrayList<>();

        for (Message m : conversation.getMessages()) {
            if (m.getFrom().equalsIgnoreCase("ChatGPT")) {
                history.add(new ChatGPTMessage("assistant", m.getText()));
            } else if (m.getFrom().equals(conversation.getInitiator())) {
                history.add(new ChatGPTMessage("user", m.getText()));
            }
        }

        return history;
    }
}
