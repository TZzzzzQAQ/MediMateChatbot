package com.friedchicken.java.ai.langchain4j;

import com.friedchicken.java.ai.langchain4j.assistant.OpenAiAssistant;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {
  @Autowired
  private OllamaChatModel ollamaChatModel;

  @Test
  public void testAIService() {
    OpenAiAssistant assistant = AiServices.create(OpenAiAssistant.class, ollamaChatModel);
    String chat = assistant.chat("你是谁？");
    System.out.println(chat);
  }

  @Autowired
  private OpenAiAssistant assistant;

  @Test
  public void testAssistant() {
    String chat = assistant.chat("who are you?");
    System.out.println(chat);
  }
}
