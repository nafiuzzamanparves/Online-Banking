package com.example.demo.entity;

import java.sql.Date;
import java.sql.Time;

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
public class TransactionHistory {

	@Id
	@SequenceGenerator(name = "tr_his_sequence", sequenceName = "tr_his_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tr_his_sequence")
	private int id;
	private long accountNumber;
	private double amount;
	private Date date;

	private Time time;
	private String timeTwo;
	private String debit;
	private String credit;

}
