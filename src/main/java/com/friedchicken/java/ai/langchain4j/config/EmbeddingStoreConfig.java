package com.friedchicken.java.ai.langchain4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;

@Configuration
public class EmbeddingStoreConfig {
  @Autowired
  private EmbeddingModel embeddingModel;

  @Bean
  public EmbeddingStore<TextSegment> embeddingStore() {
    EmbeddingStore<TextSegment> embeddingStore = PineconeEmbeddingStore.builder()
        .apiKey(System.getenv("PINECONE_API_KEY"))
        .index("medimate-chatbot-index")
        .nameSpace("medimate-chatbot-namespace")
        .createIndex(PineconeServerlessIndexConfig.builder()
            .cloud("AWS")
            .region("us-east-1")
            .dimension(embeddingModel.dimension())
            .build())
        .build();
    return embeddingStore;
  }
}
