package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoanDetails;

@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {

	List<LoanDetails> findByAccountNumber(long accountNumber);

	List<LoanDetails> findByStatus(String string);

}
