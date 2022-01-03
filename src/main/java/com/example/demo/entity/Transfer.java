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
public class Transfer {

	@Id
	@SequenceGenerator(name = "transfer_sequence", sequenceName = "transfer_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_sequence")
	private int id;
	private long fromAccountNumber;
	private long toAccountNumber;
	private double transferAmount;
	private Date date;
	private String time;
	private String branchName;

}
