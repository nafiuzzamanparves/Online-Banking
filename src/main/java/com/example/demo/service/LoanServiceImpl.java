package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;

	@Override
	public Loan requestForLoan(Loan loan) {
		long now = System.currentTimeMillis();
		Date sqlDate = new Date(now);
		loan.setStatus("Pending");
		loan.setRequestDate(sqlDate);
		return loanRepository.save(loan);
	}

	@Override
	public List<Loan> fetchAllLoan() {
		return loanRepository.findAll();
	}

	@Override
	public Loan fetchByLoanId(int loanId) {
		return loanRepository.findById(loanId).get();
	}

	@Override
	public List<Loan> fetchApprovedLoan(String approve) {
		return loanRepository.findByStatus(approve);
	}

	@Override
	public List<Loan> fetchPendingLoan(String pending) {
		return loanRepository.findByStatus(pending);
	}

	@Override
	public Loan rejectLoan(int id, Loan loan) {
		Loan loan2 = loanRepository.findById(id).get();
		loan2.setStatus("Rejected");
		return loanRepository.save(loan2);
	}

	@Override
	public List<Loan> fetchRejectedLoan(String reject) {
		return loanRepository.findByStatus(reject);
	}

	@Override
	public double countTotalLoan() {
		List<Loan> list = loanRepository.findByStatus("Approved");
		double sum = 0;
		for (Loan l : list) {
			double x = l.getAmount();
			sum += x;
		}
		return sum;
	}

}
