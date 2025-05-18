package com.friedchicken.java.ai.langchain4j.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.friedchicken.java.ai.langchain4j.store.MongoChatMemoryStore;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

@Configuration
public class MediMateAssistantConfig {
  @Autowired
  private MongoChatMemoryStore mongoChatMemoryStore;

  @Bean
  ChatMemoryProvider mediMateChatMemoryProvider() {
    return memoryId -> MessageWindowChatMemory.builder()
        .id(memoryId)
        .maxMessages(20)
        .chatMemoryStore(mongoChatMemoryStore)
        .build();
  }

  @Bean
  ContentRetriever contentRetrieverChatBot() {
    Document document1 = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/医院信息.md");
    Document document2 = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/医院科室信息.md");
    Document document3 = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/神经科室信息.md");

    List<Document> documents = Arrays.asList(document1, document2, document3);
    InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
    EmbeddingStoreIngestor.ingest(documents, embeddingStore);
    return EmbeddingStoreContentRetriever.from(embeddingStore);
  }

  @Autowired
  private EmbeddingStore embeddingStore;
  @Autowired
  private EmbeddingModel embeddingModel;

  @Bean
  ContentRetriever contentRetrieverMedimatePincone() {
    return EmbeddingStoreContentRetriever
        .builder()
        .embeddingModel(embeddingModel)
        .embeddingStore(embeddingStore)
        .maxResults(1)
        .minScore(0.8)
        .build();
  }
}
