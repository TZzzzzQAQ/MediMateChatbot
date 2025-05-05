package com.friedchicken.java.ai.langchain4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.friedchicken.java.ai.langchain4j.bean.ChatMessages;
import org.springframework.data.mongodb.core.query.Criteria;

@SpringBootTest
public class MongoCrudTest {
  @Autowired
  private MongoTemplate mongoTemplate;

  // @Test
  // public void testInsert() {
  // mongoTemplate.insert(new ChatMessages(2L, "chathistory"));
  // }

  @Test
  public void testInsert2() {
    ChatMessages chatMessages = new ChatMessages();
    chatMessages.setContent("chathistory2");
    mongoTemplate.insert(chatMessages);
  }

  @Test
  public void testFindById() {
    ChatMessages chatMessages = mongoTemplate.findById(
        "6816e5be5071f57b68a3cc8a", ChatMessages.class);
    System.out.println(chatMessages);
  }

  @Test
  public void testUpdate() {
    Criteria criteria = Criteria.where("messageId").is("222");
    Query query = new Query(criteria);
    Update update = new Update();
    update.set("content", "chathistory3");
    mongoTemplate.upsert(query, update, ChatMessages.class);
  }

  @Test
  public void testDelete() {
    Criteria criteria = Criteria.where("messageId").is("222");
    Query query = new Query(criteria);
    mongoTemplate.remove(query, ChatMessages.class);
  }
}