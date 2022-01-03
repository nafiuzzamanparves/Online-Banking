package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

	List<TransactionHistory> findByAccountNumber(long accountNumber);

	List<TransactionHistory> findByAccountNumberAndDebit(long accountNumber, String debit);

	List<TransactionHistory> findByAccountNumberAndCredit(long accountNumber, String credit);

	List<TransactionHistory> findByAccountNumberAndCreditOrderByDateDesc(long accountNumber, String credit);

	List<TransactionHistory> findByAccountNumberAndDebitOrderByDateDesc(long accountNumber, String debit);

	List<TransactionHistory> findByCredit(String string);

	List<TransactionHistory> findByDebit(String string);

	List<TransactionHistory> findByCreditIsAndDateBetween(String string, Date firstSqlDate, Date lastSqlDate);

	List<TransactionHistory> findByDebitIsAndDateBetween(String string, Date firstSqlDate, Date lastSqlDate);

	List<TransactionHistory> findByAccountNumberIsAndCreditIsAndDateBetween(long accountNumber, String string,
			Date firstSqlDate, Date lastSqlDate);

	List<TransactionHistory> findByAccountNumberIsAndDebitIsAndDateBetween(long accountNumber, String string,
			Date firstSqlDate, Date lastSqlDate);

	List<TransactionHistory> findByDateBetween(Date dob, Date joiningDate);

	List<TransactionHistory> findByDateBetweenAndAccountNumberIs(Date dob, Date joiningDate, long nid);

}
