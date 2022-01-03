package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	TransactionHistoryRepository historyRepository;

	@Override
	public List<TransactionHistory> fetchByAccountNumberAndDebit(long accountNumber, String debit) {
		return historyRepository.findByAccountNumberAndDebitOrderByDateDesc(accountNumber, debit);
	}

	@Override
	public List<TransactionHistory> fetchByAccountNumberAndCredit(long accountNumber, String credit) {
		return historyRepository.findByAccountNumberAndCreditOrderByDateDesc(accountNumber, credit);
	}

	@Override
	public double countAllCredit() {
		List<TransactionHistory> list = historyRepository.findByCredit("Yes");
		double sum = 0;
		for (TransactionHistory l : list) {
			double x = l.getAmount();
			sum += x;
		}
		return sum;
	}

	@Override
	public double countAllDebit() {
		List<TransactionHistory> list = historyRepository.findByDebit("Yes");
		double sum = 0;
		for (TransactionHistory l : list) {
			double x = l.getAmount();
			sum += x;
		}
		return sum;
	}

	@Override
	public double creditThisMonth() {
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
		List<TransactionHistory> histories = historyRepository.findByCreditIsAndDateBetween("Yes", firstSqlDate,
				lastSqlDate);
		for (TransactionHistory history : histories) {
			sum += history.getAmount();
		}
		return sum;
	}

	@Override
	public double debitThisMonth() {
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
		List<TransactionHistory> histories = historyRepository.findByDebitIsAndDateBetween("Yes", firstSqlDate,
				lastSqlDate);
		for (TransactionHistory history : histories) {
			sum += history.getAmount();
		}
		return sum;
	}

	@Override
	public double countAllCreditIndiVis(long accountNumber) {
		List<TransactionHistory> list = historyRepository.findByAccountNumberAndCredit(accountNumber, "Yes");
		double sum = 0;
		for (TransactionHistory l : list) {
			double x = l.getAmount();
			sum += x;
		}
		return sum;
	}

	@Override
	public double countAllDebitIndiVis(long accountNumber) {
		List<TransactionHistory> list = historyRepository.findByAccountNumberAndDebit(accountNumber, "Yes");
		double sum = 0;
		for (TransactionHistory l : list) {
			double x = l.getAmount();
			sum += x;
		}
		return sum;
	}

	@Override
	public double countTransaction(long accountNumber) {
		List<TransactionHistory> list = historyRepository.findByAccountNumber(accountNumber);
		double sum = 0;
		for (TransactionHistory l : list) {
			double x = l.getAmount();
			sum += x;
		}
		return sum;
	}

	@Override
	public double countAllCreditThisMonthIndiVis(long accountNumber) {
		double sum = 0;
		long now = System.currentTimeMillis();
		Date sqlDate = new Date(now);
		String sqlDateString = sqlDate.toString();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(sqlDateString, dateFormat);
		LocalDate firstDate = date.withDayOfMonth(1);
		LocalDate lastDate = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
		String firstDateString = firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastDateString = lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date firstSqlDate = Date.valueOf(firstDateString);
		Date lastSqlDate = Date.valueOf(lastDateString);
		List<TransactionHistory> histories = historyRepository
				.findByAccountNumberIsAndCreditIsAndDateBetween(accountNumber, "Yes", firstSqlDate, lastSqlDate);
		for (TransactionHistory history : histories) {
			sum += history.getAmount();
		}
		return sum;
	}

	@Override
	public double countAllDebitThisMonthIndiVis(long accountNumber) {
		double sum = 0;
		long now = System.currentTimeMillis();
		Date sqlDate = new Date(now);
		String sqlDateString = sqlDate.toString();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(sqlDateString, dateFormat);
		LocalDate firstDate = date.withDayOfMonth(1);
		LocalDate lastDate = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
		String firstDateString = firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastDateString = lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date firstSqlDate = Date.valueOf(firstDateString);
		Date lastSqlDate = Date.valueOf(lastDateString);
		List<TransactionHistory> histories = historyRepository
				.findByAccountNumberIsAndDebitIsAndDateBetween(accountNumber, "Yes", firstSqlDate, lastSqlDate);
		for (TransactionHistory history : histories) {
			sum += history.getAmount();
		}
		return sum;
	}

	@Override
	public List<TransactionHistory> fetchByBetweenDate(Date dob, Date joiningDate) {
		return historyRepository.findByDateBetween(dob, joiningDate);
	}

	@Override
	public List<TransactionHistory> fetchByBetweenDateAndAccount(Date dob, Date joiningDate, long nid) {
		return historyRepository.findByDateBetweenAndAccountNumberIs(dob, joiningDate, nid);
	}

	@Override
	public List<TransactionHistory> fetchByAccountNumber(long accountNumber) {
		return historyRepository.findByAccountNumber(accountNumber);
	}

}
