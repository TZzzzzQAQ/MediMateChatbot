package com.friedchicken.java.ai.langchain4j.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT, chatModel = "ollamaChatModel", chatMemory = "chatMemory")
public interface OllamaAiAssistant {
  String chat(String userMessage);
}
