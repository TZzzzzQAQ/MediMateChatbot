package com.friedchicken.java.ai.langchain4j;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.friedchicken.java.ai.langchain4j.assistant.SeparateOllamaChatAssistant;

@SpringBootTest
public class PromptTest {
  @Autowired
  private SeparateOllamaChatAssistant separateOllamaChatAssistant;

  @Test
  public void testSystemMessage() {
    String chat = separateOllamaChatAssistant.chat(new ObjectId("6816e5be5071f33b68a3cc81"), "Hi I am Jack!");
    System.out.println(chat);
  }

  @Test
  public void testSystemMessage2() {
    String chat = separateOllamaChatAssistant.chat2(new ObjectId("6816e5be5071f33b68a3cc85"), "Hi I am Jack!", "Jack",
        18);
    System.out.println(chat);
  }

}
