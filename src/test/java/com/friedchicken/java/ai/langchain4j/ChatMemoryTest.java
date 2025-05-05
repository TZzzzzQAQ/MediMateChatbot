package com.friedchicken.java.ai.langchain4j;

import com.friedchicken.java.ai.langchain4j.assistant.OllamaAiAssistant;
import com.friedchicken.java.ai.langchain4j.assistant.OpenAiAssistant;
import com.friedchicken.java.ai.langchain4j.assistant.SeparateOllamaChatAssistant;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
  @Autowired
  private OllamaAiAssistant ollamaAiAssistant;
  @Autowired
  private OpenAiAssistant openAiAssistant;

  @Autowired
  private OpenAiChatModel openAiChatModel;
  @Autowired
  private OllamaChatModel ollamaChatModel;

  @Test
  public void testChatMemory() {
    String chat = ollamaAiAssistant.chat("我是Jack");
    System.out.println(chat);
    String ans = ollamaAiAssistant.chat("你知道我是谁吗");
    System.out.println(ans);
  }

  @Test
  public void testChatMemory2() {
    UserMessage userMessage1 = UserMessage.userMessage("我是Jack");
    ChatResponse chatResponse1 = openAiChatModel.chat(userMessage1);
    AiMessage aiMessage1 = chatResponse1.aiMessage();
    System.out.println(aiMessage1);

    UserMessage userMessage2 = UserMessage.userMessage("我是谁？");
    ChatResponse chatResponse2 = openAiChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2));
    AiMessage aiMessage2 = chatResponse2.aiMessage();
    System.out.println(aiMessage2);
  }

  @Test
  public void testChatMemory3() {
    MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.withMaxMessages(10);
    OpenAiAssistant openAiAssistant = AiServices.builder(OpenAiAssistant.class).chatLanguageModel(openAiChatModel)
        .chatMemory(messageWindowChatMemory).build();
    OllamaAiAssistant ollamaAiAssistant = AiServices.builder(OllamaAiAssistant.class).chatLanguageModel(ollamaChatModel)
        .chatMemory(messageWindowChatMemory).build();

    // String chat = openAiAssistant.chat("我是Jack");
    // System.out.println(chat);
    // String ans = openAiAssistant.chat("你知道我是谁吗");
    // System.out.println(ans);

    String chat = ollamaAiAssistant.chat("我是Jack");
    System.out.println(chat);
    String ans = ollamaAiAssistant.chat("我上一句话说的什么");
    System.out.println(ans);
  }

  @Test
  public void testChatMemory4() {
    String chat = ollamaAiAssistant.chat("我是Jack");
    System.out.println(chat);
    String ans = ollamaAiAssistant.chat("我上一句话说的什么");
    System.out.println(ans);

    // String chat = openAiAssistant.chat("我是Jack");
    // System.out.println(chat);
    // String ans = openAiAssistant.chat("我上一句话说的什么");
    // System.out.println(ans);
  }

  @Autowired
  private SeparateOllamaChatAssistant separateOllamaChatAssistant;

  @Test
  public void testChatMemory5() {
    String ans1 = separateOllamaChatAssistant.chat(new ObjectId("6816e5be5071f57b68a3cc8a"), "I am Jack!");
    System.out.println(ans1);
    String ans2 = separateOllamaChatAssistant.chat(new ObjectId("6816e5be5071f57b68a3cc8a"),
        "tell me my name?");
    System.out.println(ans2);
    String ans3 = separateOllamaChatAssistant.chat(new ObjectId("6816e5be6071f57b68a3cc8a"), "I am Xiao!");
    System.out.println(ans3);
    String ans4 = separateOllamaChatAssistant.chat(new ObjectId("6816e5be6071f57b68a3cc8a"), "tell me my name?");
    System.out.println(ans4);
  }

}
