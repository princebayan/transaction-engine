package com.example.transactionengine;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionEngineApplicationTests {

  @Test
  void initialize() {
    /*
    Set Allure Environment
     */
    allureEnvironmentWriter(
        ImmutableMap.<String, String>builder()
            .build(), "build/allure-results/");

  }

}
