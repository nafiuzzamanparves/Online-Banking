package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.TransactionHistory;

public interface TransactionHistoryService {

	public List<TransactionHistory> fetchByAccountNumberAndDebit(long accountNumber, String debit);

	public List<TransactionHistory> fetchByAccountNumberAndCredit(long accountNumber, String credit);

	public double countAllCredit();

	public double countAllDebit();

	public double creditThisMonth();

	public double debitThisMonth();

	public double countAllCreditIndiVis(long accountNumber);

	public double countAllDebitIndiVis(long accountNumber);

	public double countTransaction(long accountNumber);

	public double countAllCreditThisMonthIndiVis(long accountNumber);

	public double countAllDebitThisMonthIndiVis(long accountNumber);

	public List<TransactionHistory> fetchByBetweenDate(Date dob, Date joiningDate);

	public List<TransactionHistory> fetchByBetweenDateAndAccount(Date dob, Date joiningDate, long nid);

	public List<TransactionHistory> fetchByAccountNumber(long accountNumber);

}
