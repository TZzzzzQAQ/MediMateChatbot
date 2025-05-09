package com.friedchicken.java.ai.langchain4j.assistant;

import org.bson.types.ObjectId;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "openAiChatModel", chatMemoryProvider = "chatMemoryProvider")
public interface SeparateOllamaChatAssistant {

  // @SystemMessage("Use Chinese to answer questions. Today is {{current_date}}.")
  @SystemMessage(fromResource = "my-prompt-template.txt")
  String chat(@MemoryId ObjectId memoryId, @UserMessage String userMessage);

  @SystemMessage(fromResource = "my-prompt-template2.txt")
  String chat2(@MemoryId ObjectId memoryId, @UserMessage String userMessage, @V("userName") String userName,
      @V("age") int age);
}
