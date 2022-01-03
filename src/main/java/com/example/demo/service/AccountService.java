package com.example.demo.service;

import com.example.demo.entity.Account;

public interface AccountService {

	public Account addMoney(Account account);

	public Account fetchBalaceByAccountNumber(long accountNumber);

	public Account debitMoney(long accountNumber, String branch, Account account);

	public Account creditMoney(long accountNumber, String branch, Account account);

	public long countAllUser();

}
