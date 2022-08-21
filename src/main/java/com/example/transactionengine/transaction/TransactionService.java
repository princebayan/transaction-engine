package com.example.transactionengine.transaction;

import com.example.transactionengine.transaction.currency.CurrencyEntity;
import com.example.transactionengine.transaction.currency.CurrencyRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final CurrencyRepository currencyRepository;

  public int executeTransaction(
      int customerId,
      String sourceAccount,
      String destinationAccount,
      BigDecimal amount,
      BigDecimal totalDebitedFromSource,
      BigDecimal totalCreditedToDestination,
      String sourceCurrency,
      String destinationCurrency) {
    /*
    Create the transaction entity object
     */
    TransactionEntity transaction = new TransactionEntity();
    transaction.setCustomerId(customerId);
    transaction.setSourceAccount(sourceAccount);
    transaction.setDestinationAccount(destinationAccount);
    transaction.setAmount(amount);
    transaction.setTotalDebitedFromSource(totalDebitedFromSource);
    transaction.setTotalCreditedToDestination(totalCreditedToDestination);
    transaction.setCreatedDate(new Date());
    transaction.setUpdatedDate(new Date());
    transaction.setSourceCurrency(currencyRepository.findByCode(sourceCurrency));
    transaction.setDestinationCurrency(currencyRepository.findByCode(destinationCurrency));
    /*
    Save in database
     */
    transaction = transactionRepository.save(transaction);

    return transaction.getId();
  }

  public List<TransactionEntity> getTransactions(int customerId) {

    List<TransactionEntity> transactions = transactionRepository.findByCustomerId(customerId);
    return transactions;
  }
}
