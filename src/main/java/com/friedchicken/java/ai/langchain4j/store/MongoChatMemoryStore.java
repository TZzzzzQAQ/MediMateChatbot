package com.friedchicken.java.ai.langchain4j.store;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.friedchicken.java.ai.langchain4j.bean.ChatMessages;
import org.springframework.data.mongodb.core.query.Criteria;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

@Component
public class MongoChatMemoryStore implements ChatMemoryStore {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<ChatMessage> getMessages(Object memoryId) {
    Criteria criteria = Criteria.where("messageId").is(memoryId);
    Query query = new Query(criteria);
    ChatMessages chatMessages = mongoTemplate.findOne(query, ChatMessages.class);
    if (chatMessages == null) {
      return new LinkedList<>();
    }
    String contentJson = chatMessages.getContent();
    return ChatMessageDeserializer.messagesFromJson(contentJson);
  }

  @Override
  public void updateMessages(Object memoryId, List<ChatMessage> messages) {
    Criteria criteria = Criteria.where("messageId").is(memoryId);
    Query query = new Query(criteria);
    Update update = new Update();
    update.set("content", ChatMessageSerializer.messagesToJson(messages));
    mongoTemplate.upsert(query, update, ChatMessages.class);
  }

  @Override
  public void deleteMessages(Object memoryId) {
    Criteria criteria = Criteria.where("messageId").is(memoryId);
    Query query = new Query(criteria);
    mongoTemplate.remove(query, ChatMessages.class);
  }

}
