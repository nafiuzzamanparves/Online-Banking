package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	List<Loan> findByStatus(String approve);

}
