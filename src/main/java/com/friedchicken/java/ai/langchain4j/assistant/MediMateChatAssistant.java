package com.friedchicken.java.ai.langchain4j.assistant;

import org.bson.types.ObjectId;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "openAiChatModel", chatMemoryProvider = "mediMateChatMemoryProvider", tools = "appointmentTools")
public interface MediMateChatAssistant {
  @SystemMessage(fromResource = "medimate-chatbot-prompt.txt")
  String chat(@MemoryId ObjectId memoryId, @UserMessage String userMessage);
}
