package com.example.transactionengine.transaction;

import com.example.transactionengine.transaction.request.ExecuteRequest;
import com.example.transactionengine.transaction.response.ExecuteResponse;
import com.example.transactionengine.transaction.response.Transaction;
import com.example.transactionengine.transaction.response.TransactionsResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "transaction")
@Validated
@Tag(name = "Transaction Controller")
@Log4j2
@AllArgsConstructor
public class TransactionController {

  private static final ModelMapper modelMapper = new ModelMapper();

  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<ExecuteResponse> execute(
      @RequestBody @Valid ExecuteRequest executeRequest) {
    log.info("Invoke execute. request {}", executeRequest);

    int transactionId = transactionService.executeTransaction(
        executeRequest.getCustomerId(),
        executeRequest.getSourceAccount(),
        executeRequest.getDestinationAccount(),
        executeRequest.getAmount(),
        executeRequest.getTotalDebitedFromSource(),
        executeRequest.getTotalCreditedToDestination(),
        executeRequest.getSourceCurrency(),
        executeRequest.getDestinationCurrency()
    );
    ExecuteResponse response = new ExecuteResponse();
    response.setTransactionId(transactionId);
    /*
    returning the response as 200 OK
     */
    log.info("Exiting execute. response {}", response);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


  @GetMapping
  public ResponseEntity getTransaction(
      @RequestParam @Positive int customerId) {

    List<TransactionEntity> transactionEntities = transactionService.getTransactions(customerId);
    List<Transaction> transactions = transactionEntities.parallelStream()
        .map(transactionEntity -> {
          Transaction transaction = new Transaction();
          transaction.setId(transactionEntity.getId());
          transaction.setCustomerId(transactionEntity.getCustomerId());
          transaction.setAmount(transactionEntity.getAmount());
          transaction.setSourceAccount(transactionEntity.getSourceAccount());
          transaction.setSourceCurrency(transactionEntity.getSourceCurrency().getCode());
          transaction.setDestinationAccount(transactionEntity.getDestinationAccount());
          transaction.setDestinationCurrency(transactionEntity.getDestinationCurrency().getCode());
          transaction.setUpdatedDate(transactionEntity.getUpdatedDate());
          transaction.setCreatedDate(transactionEntity.getCreatedDate());
          transaction
              .setTotalCreditedToDestination(transactionEntity.getTotalCreditedToDestination());
          transaction.setTotalDebitedFromSource(transactionEntity.getTotalDebitedFromSource());
          return transaction;
        })
        .collect(Collectors.toList());
    TransactionsResponse response = new TransactionsResponse();
    response.setTransactions(transactions);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


}
