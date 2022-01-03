package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transfer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransferRepository;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransferRepository transferRepository;

	@Transactional
	@Override
	public Transfer sendMoney(Transfer transfer) {
		long fromAccount = transfer.getFromAccountNumber();
		long toAccount = transfer.getToAccountNumber();
		double amount = transfer.getTransferAmount();
		Account formAccObj = accountRepository.findByAccountNumber(fromAccount);
		Account toAccObj = accountRepository.findByAccountNumber(toAccount);
		if (toAccObj != null) {
			double formAccBalance = formAccObj.getBalance();
			double toAccBalance = toAccObj.getBalance();
			double afterSendFormAccBalance = formAccBalance - amount;
			if (afterSendFormAccBalance <= 500.0) {
				transfer.setBranchName("notEnoughMoney");
				return transfer;
			} else if (!transfer.getBranchName().equals(toAccObj.getBranchName())) {
				transfer.setBranchName("unmatchedBranch");
				return transfer;
			}
			double afterSendToAccBalance = toAccBalance + amount;
			formAccObj.setBalance(afterSendFormAccBalance);
			accountRepository.save(formAccObj);
			toAccObj.setBalance(afterSendToAccBalance);
			accountRepository.save(toAccObj);
			long now = System.currentTimeMillis();
			Date sqlDate = new Date(now);
			String transactionTime = new SimpleDateFormat("hh:mm:ss").format(sqlDate);
			transfer.setDate(sqlDate);
			transfer.setTime(transactionTime);
			return transferRepository.save(transfer);
		} else {
			transfer.setBranchName("noAccount");
			return transfer;
		}
	}

	@Override
	public List<Transfer> fetchByFromAccountNumber(long accountNumber) {
		return transferRepository.findByFromAccountNumberOrderByDateDesc(accountNumber);
	}

	@Override
	public double countTotalSend(long accountNumber) {
		List<Transfer> list = transferRepository.findByFromAccountNumber(accountNumber);
		double sum = 0;
		for (Transfer l : list) {
			double x = l.getTransferAmount();
			sum += x;
		}
		return sum;
	}

	@Override
	public double countTotalSendThisMonth(long accountNumber) {
		double sum = 0;
		long now = System.currentTimeMillis();
		Date sqlDate = new Date(now);
		String sqlDateString = sqlDate.toString();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(sqlDateString, dateFormat);
		LocalDate firstDate = date.withDayOfMonth(1);
		System.out.println("first date of this month is : " + firstDate);
		LocalDate lastDate = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
		System.out.println("last date of this month is : " + lastDate);
		String firstDateString = firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastDateString = lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date firstSqlDate = Date.valueOf(firstDateString);
		Date lastSqlDate = Date.valueOf(lastDateString);
		List<Transfer> histories = transferRepository.findByFromAccountNumberAndDateBetween(accountNumber, firstSqlDate,
				lastSqlDate);
		for (Transfer history : histories) {
			sum += history.getTransferAmount();
		}
		return sum;
	}

}
