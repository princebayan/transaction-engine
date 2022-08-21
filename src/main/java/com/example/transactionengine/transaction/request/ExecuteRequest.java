package com.example.transactionengine.transaction.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Validated
public class ExecuteRequest {

  @Positive
  private int customerId;
  @NotBlank
  @Length(min = 13, max = 13)
  private String sourceAccount;
  @NotBlank
  @Length(min = 13, max = 13)
  private String destinationAccount;
  @Positive
  private BigDecimal amount;
  @Positive
  private BigDecimal totalDebitedFromSource;
  @Positive
  private BigDecimal totalCreditedToDestination;
  @NotBlank
  private String sourceCurrency;
  @NotBlank
  private String destinationCurrency;

  public String toJson() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }
}
