package com.springapp.expensetracker.Transaction;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("/transactions")
	public List<Transaction> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
	
	@RequestMapping("/transactions/{id}")
	public Transaction getTransaction(@PathVariable long id) {
		return transactionService.getTransaction(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/transactions")
	public void addTransaction(@RequestBody Transaction transaction) {
		transactionService.addTransaction(transaction);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/transactions/{id}")
	public void updateTransaction(@RequestBody Transaction transaction, @PathVariable long id) {
		transactionService.updateTransaction(id, transaction);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/transactions/{id}")
	public void deleteTransaction(@PathVariable long id) {
		transactionService.deleteTransaction(id);
	}
}
