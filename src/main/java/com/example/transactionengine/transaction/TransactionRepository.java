package com.example.transactionengine.transaction;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

  List<TransactionEntity> findByCustomerId(int customerId);
}
