package com.friedchicken.java.ai.langchain4j.assistant;

import org.bson.types.ObjectId;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, streamingChatModel = "qwenStreamingChatModel", chatMemoryProvider = "mediMateChatMemoryProvider", tools = "appointmentTools", contentRetriever = "contentRetrieverMedimatePincone")
public interface MediMateChatAssistant {
  @SystemMessage(fromResource = "medimate-chatbot-prompt.txt")
  Flux<String> chat(@MemoryId ObjectId memoryId, @UserMessage String userMessage);
}
