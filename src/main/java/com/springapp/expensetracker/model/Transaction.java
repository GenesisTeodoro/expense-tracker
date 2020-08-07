package com.springapp.expensetracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private double amount;
	
}
