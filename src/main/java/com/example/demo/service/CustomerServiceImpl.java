package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> fetchAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> fetchApprovedCustomer(String approved) {
		return customerRepository.findByEmpApproveStatusEquals(approved);
	}

	@Override
	public List<Customer> fetchPendingCustomers(String pending) {
		return customerRepository.findByEmpApproveStatus(pending);
	}

	@Override
	public List<Customer> fetchRejectedCustomer(String rejected) {
		return customerRepository.findByEmpApproveStatusEquals(rejected);
	}

	@Override
	public Customer confirmAccount(int customerId, Customer customer) {
		Customer customer2 = customerRepository.findById(customerId).get();
		customer2.setAccountNumber(customer.getAccountNumber());
		customer2.setPassword(customer.getPassword());
		Account account = new Account();
		account.setAccountNumber(customer2.getAccountNumber());
		account.setBranchName(customer2.getBranch());
		account.setBalance(0.0);
		accountRepository.save(account);
		return customerRepository.save(customer2);
	}

	@Override
	public Customer checkLogin(long accountNumber, String password) {
		return customerRepository.findByAccountNumberAndPassword(accountNumber, password);
	}

	@Override
	public Customer statusApprove(int id, Customer customer) {
		Customer customer2 = customerRepository.findById(id).get();
		customer2.setEmpApproveStatus("Approved");
		return customerRepository.save(customer2);
	}

	@Override
	public Customer statusReject(int id, Customer customer) {
		Customer customer2 = customerRepository.findById(id).get();
		customer2.setEmpApproveStatus("Rejected");
		return customerRepository.save(customer2);
	}

	@Override
	public List<Customer> fetchApprovedCustomerWithNoAccount(String approved, long accountNumber) {
		return customerRepository.findByEmpApproveStatusAndAccountNumber(approved, accountNumber);
	}

	@Override
	public List<Customer> fetchApprovedCustomerWithAccount(String approved, long accountNumber) {
		return customerRepository.findByEmpApproveStatusEqualsAndAccountNumberNot(approved, accountNumber);
	}

	@Override
	public Customer fetchCustomreByAccountNumber(long accountNumber) {
		return customerRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public Customer updatePassword(long accountNumber, String oldPass, String newPass) {
		Customer customer = customerRepository.findByAccountNumberAndPassword(accountNumber, oldPass);
		if (customer != null) {
			customer.setPassword(newPass);
			return customerRepository.save(customer);
		}
		return null;
	}

}
