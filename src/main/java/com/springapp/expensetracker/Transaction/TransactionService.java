package com.springapp.expensetracker.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class TransactionService {
	
	private double expense;
	private double income;
	private double balance;
	
	private List<Transaction> transactions = new ArrayList<>(Arrays.asList(
			new Transaction(1, "Electric Bill", "Expense", "electric bill", 23.00),
			new Transaction(2, "income", "Income", "salary", 43.00)
			));

	public List<Transaction> getAllTransactions() {
		return transactions;
	}

	public Transaction getTransaction(long id) {
		return transactions.stream().filter(
				t -> t.getId() == id).findFirst().get();
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		
		if(transaction.getType().equals("Income")) 
			setIncome(transaction.getAmount());
		if(transaction.getType().equals("Expense"))
			setExpense(transaction.getAmount());
		
		System.out.println("Income: "+ this.income);
		System.out.println("Expense: "+ this.expense);
		System.out.println("Balance: "+ (this.income - this.expense));
		
	}

	public void updateTransaction(long id, Transaction transaction) {
		for(int i=0; i< transactions.size(); i++) {
			Transaction t = transactions.get(i);
			if(t.getId() == id) {
				transactions.set(i, transaction);
				return;
			}
		}
		
	}

	public void deleteTransaction(long id) {
		transactions.removeIf(t -> t.getId() == id);
		
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense + getExpense();
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income + getIncome();
	}

	public double getBalance() {
		return getIncome() - getExpense();
	}
	
	

	
	
	
	
	

}
