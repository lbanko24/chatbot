package com.rsikspr.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		System.err.close();		//Hides Alicebot stack traces, Spring doesn't use System.err

		SpringApplication.run(ChatbotApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
