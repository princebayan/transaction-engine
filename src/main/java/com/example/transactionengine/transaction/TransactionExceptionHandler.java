package com.example.transactionengine.transaction;

import com.example.transactionengine.shared.ResponseBody;
import com.example.transactionengine.transaction.exception.SameSourceDestinationAccountException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
@AllArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class TransactionExceptionHandler {


  @ExceptionHandler(SameSourceDestinationAccountException.class)
  public ResponseEntity<?> handleSameSourceDestinationAccountException(
      SameSourceDestinationAccountException ex) {
    log.warn("handle CustomerNotFoundException", ex);
    /*
    Prepare the response
     */
    ResponseBody<?> responseBody = ResponseBody
        .failedError(HttpStatus.UNPROCESSABLE_ENTITY, "same source and destination account");
    /*
    Return Unprocessable entity response.
     */
    return new ResponseEntity<>(responseBody, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
