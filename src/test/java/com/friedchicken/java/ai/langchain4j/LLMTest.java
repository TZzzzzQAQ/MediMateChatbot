package com.friedchicken.java.ai.langchain4j;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {
  @Test
  public void testGPTDemo() {
    OpenAiChatModel model = OpenAiChatModel.builder()
        .baseUrl("http://langchain4j.dev/demo/openai/v1")
        .apiKey("demo")
        .modelName("gpt-4o-mini")
        .build();
    String hello = model.chat("whats your name?");
    System.out.println(hello);
  }

  @Autowired
  private OpenAiChatModel openAiChatModel;

  @Test
  public void testSpringBoot() {
    String chat = openAiChatModel.chat("who am i?");
    System.out.println(chat);
  }

  @Autowired
  private OllamaChatModel ollamaChatModel;

  @Test
  public void testOllamaChatModel() {
    String answer = ollamaChatModel.chat("who am i?");
    System.out.println(answer);
  }
}
