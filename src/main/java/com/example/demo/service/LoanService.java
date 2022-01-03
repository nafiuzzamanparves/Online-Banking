package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Loan;

public interface LoanService {

	public Loan requestForLoan(Loan loan);

	public List<Loan> fetchAllLoan();

	public Loan fetchByLoanId(int loanId);

	public List<Loan> fetchApprovedLoan(String approve);

	public List<Loan> fetchPendingLoan(String pending);

	public Loan rejectLoan(int id, Loan loan);

	public List<Loan> fetchRejectedLoan(String reject);

	public double countTotalLoan();

}
