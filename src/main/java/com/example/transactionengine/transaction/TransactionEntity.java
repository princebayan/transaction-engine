package com.example.transactionengine.transaction;

import com.example.transactionengine.transaction.currency.CurrencyEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "customer_id")
  private int customerId;
  @Column(name = "source_account", nullable = false, length = 100)
  private String sourceAccount;
  @Column(name = "destination_account", nullable = false, length = 100)
  private String destinationAccount;
  @Column(name = "created_date")
  private Date createdDate;
  @Column(name = "updated_date")
  private Date updatedDate;
  @Column(name = "amount")
  private BigDecimal amount;
  @Column(name = "total_debited_from_source")
  private BigDecimal totalDebitedFromSource;
  @Column(name = "total_credited_to_destination")
  private BigDecimal totalCreditedToDestination;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "source_currency_id")
  private CurrencyEntity sourceCurrency;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "destination_currency_id")
  private CurrencyEntity destinationCurrency;

}
