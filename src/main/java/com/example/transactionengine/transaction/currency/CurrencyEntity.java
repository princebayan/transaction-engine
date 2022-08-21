package com.example.transactionengine.transaction.currency;

import com.example.transactionengine.transaction.TransactionEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter
@Setter
public class CurrencyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name ="code", nullable = false, length = 3)
  private String code;

  @OneToMany(mappedBy = "sourceCurrency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<TransactionEntity> sourceCurrencyTransaction = new HashSet<>();

  @OneToMany(mappedBy = "destinationCurrency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<TransactionEntity> destinationCurrencyTransaction = new HashSet<>();
}
