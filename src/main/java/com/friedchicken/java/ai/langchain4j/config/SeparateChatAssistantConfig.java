package com.friedchicken.java.ai.langchain4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.friedchicken.java.ai.langchain4j.store.MongoChatMemoryStore;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

@Configuration
public class SeparateChatAssistantConfig {
  @Autowired
  private MongoChatMemoryStore mongoChatMemoryStore;

  @Bean
  public ChatMemoryProvider chatMemoryProvider() {
    return memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10)
        .chatMemoryStore(mongoChatMemoryStore).build();
  }
}
