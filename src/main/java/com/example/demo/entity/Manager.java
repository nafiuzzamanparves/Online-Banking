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
public class Manager {

	@Id
	@SequenceGenerator(name = "manager_sequence", sequenceName = "manager_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_sequence")
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String phoneNo;
	private String address;
	@Column(unique = true)
	private long nid;
	private Date dob;
	private Date joiningDate;
	@Column(unique = true)
	private String managerId;
	private String password;

}
