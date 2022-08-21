package com.example.transactionengine.transaction.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {

  CurrencyEntity findByCode(String code);
}
