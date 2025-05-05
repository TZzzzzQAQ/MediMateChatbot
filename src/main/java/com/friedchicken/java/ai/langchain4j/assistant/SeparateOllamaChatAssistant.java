package com.friedchicken.java.ai.langchain4j.assistant;

import org.bson.types.ObjectId;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "ollamaChatModel", chatMemoryProvider = "chatMemoryProvider")
public interface SeparateOllamaChatAssistant {
  String chat(@MemoryId ObjectId memoryId, @UserMessage String userMessage); // Use String for memoryId
}
