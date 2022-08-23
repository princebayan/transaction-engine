package com.example.transactionengine.transaction;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.transactionengine.transaction.request.ExecuteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Transaction Controller Test")
public class TransactionControllerTest {

  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Autowired
  private MockMvc mvc;

  @Test
  void initialize() {
    /*
    Set Allure Environment
     */
    allureEnvironmentWriter(
        ImmutableMap.<String, String>builder()
            .build(), "build/allure-results/");

  }

  @Test
  @DisplayName("Test success execution of a transaction")
  @Description("First initialize the request then send it to the controller "
      + "Acceptance Criteria = 200 Success")
  public void testExecute() throws Exception {

    /*
    Fill the execute request.
     */
    ExecuteRequest executeRequest = new ExecuteRequest();
    executeRequest.setCustomerId(1);
    executeRequest.setDestinationAccount("1338508001840");
    executeRequest.setDestinationCurrency("USD");
    executeRequest.setSourceAccount("1234567001840");
    executeRequest.setSourceCurrency("USD");
    executeRequest.setTotalDebitedFromSource(new BigDecimal(10));
    executeRequest.setTotalCreditedToDestination(new BigDecimal(10));
    executeRequest.setAmount(new BigDecimal(10));

    long executionTime = System.currentTimeMillis();

    MvcResult executeResponse = mvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content(executeRequest.toJson()))
        .andExpect(status().isOk())
        .andReturn();

    executionTime = System.currentTimeMillis() - executionTime;

    /*
    Extract response content & Assure != null
     */
    String executeContent = executeResponse.getResponse().getContentAsString();
    JsonElement executeOutput = JsonParser.parseString(executeContent);

    Allure.addAttachment("Execute Transaction Response:", gson.toJson(executeOutput));
    Allure.addAttachment("Execution Time in ms", String.valueOf(executionTime));
  }

  @Test
  @DisplayName("Test account length not 13 for execute request")
  @Description("Test account length not 13 for execute request"
      + "Acceptance Criteria = 400 Bad Request")
  public void testCustomerIdNotFoundForExecute() throws Exception {

    /*
    Fill the execute request.
     */
    ExecuteRequest executeRequest = new ExecuteRequest();
    executeRequest.setCustomerId(1);
    executeRequest.setDestinationAccount("1338508001");
    executeRequest.setDestinationCurrency("USD");
    executeRequest.setSourceAccount("1338508001");
    executeRequest.setSourceCurrency("USD");
    executeRequest.setTotalDebitedFromSource(new BigDecimal(10));
    executeRequest.setTotalCreditedToDestination(new BigDecimal(10));
    executeRequest.setAmount(new BigDecimal(10));

    long executionTime = System.currentTimeMillis();

    MvcResult executeResponse = mvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content(executeRequest.toJson()))
        .andExpect(status().isBadRequest())
        .andReturn();

    executionTime = System.currentTimeMillis() - executionTime;

    /*
    Extract response content & Assure != null
     */
    String executeContent = executeResponse.getResponse().getContentAsString();
    JsonElement executeOutput = JsonParser.parseString(executeContent);

    Allure.addAttachment("Execute Transaction Response:", gson.toJson(executeOutput));
    Allure.addAttachment("Execution Time in ms", String.valueOf(executionTime));
  }

  @Test
  @DisplayName("Test amount is negative execute request")
  @Description("Test amount is negative execute request"
      + "Acceptance Criteria = 400 Bad Request")
  public void testAmountNegativeForExecute() throws Exception {

    /*
    Fill the execute request.
     */
    ExecuteRequest executeRequest = new ExecuteRequest();
    executeRequest.setCustomerId(1);
    executeRequest.setDestinationAccount("1338508001840");
    executeRequest.setDestinationCurrency("USD");
    executeRequest.setSourceAccount("1234567001840");
    executeRequest.setSourceCurrency("USD");
    executeRequest.setTotalDebitedFromSource(new BigDecimal(10));
    executeRequest.setTotalCreditedToDestination(new BigDecimal(10));
    executeRequest.setAmount(new BigDecimal(-10));

    long executionTime = System.currentTimeMillis();

    MvcResult executeResponse = mvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content(executeRequest.toJson()))
        .andExpect(status().isBadRequest())
        .andReturn();

    executionTime = System.currentTimeMillis() - executionTime;

    /*
    Extract response content & Assure != null
     */
    String executeContent = executeResponse.getResponse().getContentAsString();
    JsonElement executeOutput = JsonParser.parseString(executeContent);

    Allure.addAttachment("Execute Transaction Response:", gson.toJson(executeOutput));
    Allure.addAttachment("Execution Time in ms", String.valueOf(executionTime));
  }


  @Test
  @DisplayName("Test Same destination source account for execute request")
  @Description("Test Same destination source account for execute request"
      + "Acceptance Criteria = 422 Bad Request")
  public void testSameSourceDestinationAccountForExecute() throws Exception {

    /*
    Fill the execute request.
     */
    ExecuteRequest executeRequest = new ExecuteRequest();
    executeRequest.setCustomerId(1);
    executeRequest.setDestinationAccount("1338508001840");
    executeRequest.setDestinationCurrency("USD");
    executeRequest.setSourceAccount("1338508001840");
    executeRequest.setSourceCurrency("USD");
    executeRequest.setTotalDebitedFromSource(new BigDecimal(10));
    executeRequest.setTotalCreditedToDestination(new BigDecimal(10));
    executeRequest.setAmount(new BigDecimal(10));

    long executionTime = System.currentTimeMillis();

    MvcResult executeResponse = mvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content(executeRequest.toJson()))
        .andExpect(status().isUnprocessableEntity())
        .andReturn();

    executionTime = System.currentTimeMillis() - executionTime;

    /*
    Extract response content & Assure != null
     */
    String executeContent = executeResponse.getResponse().getContentAsString();
    JsonElement executeOutput = JsonParser.parseString(executeContent);

    Allure.addAttachment("Execute Transaction Response:", gson.toJson(executeOutput));
    Allure.addAttachment("Execution Time in ms", String.valueOf(executionTime));
  }
}
