package com.example.transactionengine.transaction.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SameSourceDestinationAccountException extends RuntimeException {

  private String sourceAccount;
  private String destinationAccount;
}
