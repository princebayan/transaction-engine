package com.example.transactionengine.transaction.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionsResponse {

  private List<Transaction> transactions;
}
