package com.example.transactionengine.transaction.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExecuteRequest {

  private int customerId;
  private String sourceAccount;
  private String destinationAccount;
  private BigDecimal amount;
  private BigDecimal totalDebitedFromSource;
  private BigDecimal totalCreditedToDestination;
  private String sourceCurrency;
  private String destinationCurrency;
}
