package com.friedchicken.java.ai.langchain4j;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;

@SpringBootTest
public class test {
  @Autowired
  private EmbeddingStore embeddingStore;
  @Autowired
  private EmbeddingModel embeddingModel;

  @Test
  public void testUploadKnowledgeLibrary() {
    Document document1 = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/医院信息.md");
    Document document2 = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/医院科室信息.md");
    Document document3 = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/神经科室信息.md");
    List<Document> documents = Arrays.asList(document1, document2, document3);
    EmbeddingStoreIngestor
        .builder()
        .embeddingStore(embeddingStore)
        .embeddingModel(embeddingModel)
        .build()
        .ingest(documents);
  }
}
