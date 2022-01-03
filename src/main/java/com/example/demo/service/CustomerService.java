package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer);

	public List<Customer> fetchAllCustomer();

	public List<Customer> fetchApprovedCustomer(String approved);

	public List<Customer> fetchPendingCustomers(String pending);

	public Customer confirmAccount(int customerId, Customer customer);

	public Customer checkLogin(long accountNumber, String password);

	public Customer statusApprove(int id, Customer customer);

	public Customer statusReject(int id, Customer customer);

	public List<Customer> fetchRejectedCustomer(String rejected);

	public List<Customer> fetchApprovedCustomerWithNoAccount(String approved, long accountNumber);

	public List<Customer> fetchApprovedCustomerWithAccount(String approved, long accountNumber);

	public Customer fetchCustomreByAccountNumber(long accountNumber);

	public Customer updatePassword(long accountNumber, String oldPass, String newPass);

}
