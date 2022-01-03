package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
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
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String phoneNo;
	private String address;
	private long nid;
	private Date dob;
	private String profession;
	private String accountType;
	private String branch;
	@Column(name = "empApproveStatus")
	private String empApproveStatus = "Pending";
	private long accountNumber;
	private String password;

}
