package com.example.transactionengine.transaction.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Transaction {

  private int id;
  private int customerId;
  private String sourceAccount;
  private String destinationAccount;
  private Date createdDate;
  private Date updatedDate;
  private BigDecimal amount;
  private BigDecimal totalDebitedFromSource;
  private BigDecimal totalCreditedToDestination;
  private String sourceCurrency;
  private String destinationCurrency;
}
