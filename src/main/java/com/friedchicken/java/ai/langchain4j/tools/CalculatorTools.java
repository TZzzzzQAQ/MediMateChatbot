package com.friedchicken.java.ai.langchain4j.tools;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.MemoryId;

@Component
public class CalculatorTools {
  @Tool(name = "add", value = "Add two numbers")
  double sum(@MemoryId ObjectId id, double a, double b) {
    System.out.println("sum: " + a + " + " + b + id);
    return a + b;
  }

  @Tool(name = "square", value = "Square a number")
  double squareRoot(@MemoryId ObjectId id, double a) {
    System.out.println("squareRoot: " + a + id);
    return Math.sqrt(a);
  }
}
