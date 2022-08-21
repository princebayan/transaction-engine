package com.example.transactionengine.transaction.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExecuteRequest {

  @Positive
  private int customerId;
  @NotBlank
  private String sourceAccount;
  @NotBlank
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
}
