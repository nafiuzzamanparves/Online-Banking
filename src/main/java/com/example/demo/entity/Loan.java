package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan {

	@Id
	@SequenceGenerator(name = "loan_sequence", sequenceName = "loan_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_sequence")
	private int id;
	private long accountNumber;
	private String customerName;
	private String loanType;
	private double amount;
	private double durarion;
	private double interestRate;
	private double monthlyPay;
	private double totalPayable;
	private Date requestDate;
	private Date approveDate;
	private String profession;
	private String status;
}
