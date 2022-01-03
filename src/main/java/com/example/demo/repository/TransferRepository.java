package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	List<Transfer> findByFromAccountNumber(long accountNumber);

	List<Transfer> findByFromAccountNumberOrderByDateDesc(long accountNumber);

	List<Transfer> findByFromAccountNumberAndDateBetween(long accountNumber, Date firstSqlDate, Date lastSqlDate);

}
