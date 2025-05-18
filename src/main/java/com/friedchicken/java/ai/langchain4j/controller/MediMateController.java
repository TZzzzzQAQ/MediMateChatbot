package com.friedchicken.java.ai.langchain4j.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friedchicken.java.ai.langchain4j.assistant.MediMateChatAssistant;
import com.friedchicken.java.ai.langchain4j.bean.ChatForm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;

@Tag(name = "MediMateController", description = "MediMate Controller")
@RestController
@RequestMapping("/medimate")
public class MediMateController {
  @Autowired
  private MediMateChatAssistant mediMateChatAssistant;

  @Operation(summary = "Chat with MediMate", description = "Chat with MediMate")
  @PostMapping("/chat")
  public Flux<String> chat(@RequestBody ChatForm chatForm) {
    return mediMateChatAssistant.chat(new ObjectId(chatForm.getMemoryId()), chatForm.getMessage());
  }
}
