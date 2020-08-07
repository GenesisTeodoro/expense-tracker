package com.springapp.expensetracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.springapp.expensetracker.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	List<Transaction> findById(long id);

}
