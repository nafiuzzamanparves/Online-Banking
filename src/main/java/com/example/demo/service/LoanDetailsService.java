package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Loan;
import com.example.demo.entity.LoanDetails;

public interface LoanDetailsService {

	public LoanDetails giveLoan(Loan loan2);

	public List<LoanDetails> fetchLoan(long accountNumber);

	public List<LoanDetails> fetchLoanByAccountNumber(long accountNumber);

	public LoanDetails fetchById(int id);

	public LoanDetails payLoan(int id, LoanDetails loanDetails);

	public double loanRepay();

}
