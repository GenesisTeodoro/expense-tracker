package com.springapp.expensetracker.Transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private double amount;
	
	public Transaction() {}
	
	public Transaction(long id, String category, String type, String description, double amount) {
		super();
		this.id = id;
		this.category = category;
		this.type = type;
		this.description = description;
		this.amount = amount;
	}
	
	
}
