package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionHistoryRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repository;
	@Autowired
	TransactionHistoryRepository historyRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Account addMoney(Account account) {
		return repository.save(account);
	}

	@Override
	public Account fetchBalaceByAccountNumber(long accountNumber) {
		return repository.findByAccountNumber(accountNumber);
	}

	@Override
	public Account debitMoney(long accountNumber, String branch, Account account) {
		Account account3 = repository.findByAccountNumber(accountNumber);
		if (account3 != null) {
			Account account2 = repository.findByAccountNumberAndBranchName(accountNumber, branch);
			if (account2 != null) {
				double availableBalance = account2.getBalance();
				double withdrawBalance = account.getBalance();
				if (availableBalance - withdrawBalance >= 2000) {
					account2.setBalance(availableBalance - withdrawBalance);
					long now = System.currentTimeMillis();
					Date sqlDate = new Date(now);
					String transactionTime = new SimpleDateFormat("hh:mm:ss").format(sqlDate);
					TransactionHistory history = new TransactionHistory();
					history.setAccountNumber(accountNumber);
					history.setAmount(account.getBalance());
					history.setCredit("No");
					history.setDebit("Yes");
					history.setDate(sqlDate);
					history.setTimeTwo(transactionTime);
					historyRepository.save(history);
					return repository.save(account2);
				}
			}
		}
		return null;
	}

	@Override
	public Account creditMoney(long accountNumber, String branch, Account account) {
		Customer customer = customerRepository.findByAccountNumberAndBranch(accountNumber, branch);
		Account account3 = repository.findByAccountNumber(accountNumber);
		if (account3 != null) {
			Account account2 = repository.findByAccountNumberAndBranchName(accountNumber, branch);
			if (account2 != null) {
				long now = System.currentTimeMillis();
				Date sqlDate = new Date(now);
				String transactionTime = new SimpleDateFormat("hh:mm:ss").format(sqlDate);
				TransactionHistory history = new TransactionHistory();
				history.setAccountNumber(accountNumber);
				history.setAmount(account.getBalance());
				history.setCredit("Yes");
				history.setDebit("No");
				history.setDate(sqlDate);
				history.setTimeTwo(transactionTime);
				historyRepository.save(history);
				double availableBalance = account2.getBalance();
				double withdrawBalance = account.getBalance();
				account2.setBalance(availableBalance + withdrawBalance);
				return repository.save(account2);
			}
		} else if (customer != null) {
			TransactionHistory history = new TransactionHistory();
			history.setAccountNumber(accountNumber);
			history.setAmount(account.getBalance());
			history.setCredit("Yes");
			history.setDebit("No");
			historyRepository.save(history);
			return repository.save(account);
		}
		return null;
	}

	@Override
	public long countAllUser() {
		return repository.count();
	}
}
