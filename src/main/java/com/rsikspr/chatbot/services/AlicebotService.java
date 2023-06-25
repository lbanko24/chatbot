package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Message;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicBooleans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@Qualifier("aliceBot")
public class AlicebotService implements ChatbotService {
    private final Bot bot;
    private final Map<String, Chat> sessions;

    public AlicebotService() {
        MagicBooleans.trace_mode = false;
        this.sessions = new HashMap<>();
        bot = new Bot("alice", getResourcesPath());
        bot.brain.nodeStats();
    }

    @Override
    public String respond(Message message) {
        String from = message.getConversation().getInitiator();

        if (!sessions.containsKey(from)) {
            sessions.put(from, new Chat(bot));
        }

        Chat chatSession = sessions.get(from);

        return chatSession.multisentenceRespond(message.getText());
    }

    private static String getResourcesPath() {
        File currDir = new File(".");

        return currDir.getAbsolutePath();
    }
}
