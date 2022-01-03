package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.LoanDetails;
import com.example.demo.repository.LoanDetailsRepository;
import com.example.demo.repository.LoanRepository;

@Service
public class LoanDetailsServiceImpl implements LoanDetailsService {
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	LoanDetailsRepository loanDetailsRepository;

	@Override
	public LoanDetails giveLoan(Loan loan2) {
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December", "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December", "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October", "November", "December", "January", "February",
				"March", "April", "May", "June", "July", "August", "September", "October", "November", "December",
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
				"November", "December", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December", "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December", "January", "February", "March",
				"April", "May", "June", "July", "August", "September", "October", "November", "December", "January",
				"February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
				"December" };
		Calendar cal = Calendar.getInstance();
		String m = new SimpleDateFormat("MM").format(cal.getTime());
		String y = new SimpleDateFormat("yyyy").format(cal.getTime());
		int month = Integer.parseInt(m) - 1;
		int year = Integer.parseInt(y);

		long now = System.currentTimeMillis();
		Date sqlDate = new Date(now);
		Loan loan = loanRepository.findById(loan2.getId()).get();
		loan.setApproveDate(sqlDate);
		loan.setStatus("Approved");
		double duration = loan.getDurarion();
		double payMonthDouble = duration * 12;
		int payMonth = (int) payMonthDouble;
		loanRepository.save(loan);
		for (int i = month; i < month + payMonth; i++) {
			if (months[i] == "January") {
				year += 1;
			}
			LoanDetails loanDetails = new LoanDetails();
			loanDetails.setAccountNumber(loan.getAccountNumber());
			loanDetails.setAmount(loan.getAmount());
			loanDetails.setApproveDate(sqlDate);
			loanDetails.setCustomerName(loan.getCustomerName());
			loanDetails.setDueDate(months[i] + ", " + year);
			loanDetails.setDurarion(loan.getDurarion());
			loanDetails.setInterestRate(loan.getInterestRate());
			loanDetails.setLoanType(loan.getLoanType());
			loanDetails.setMonthlyPay(loan.getMonthlyPay());
			loanDetails.setProfession(loan.getProfession());
			loanDetails.setRequestDate(loan.getRequestDate());
			loanDetails.setStatus("Unpaid");
			loanDetails.setTotalPayable(loan.getTotalPayable());
			loanDetailsRepository.save(loanDetails);
			System.out.println(months[i] + ", " + year);
		}
		return null;
	}

	@Override
	public List<LoanDetails> fetchLoan(long accountNumber) {
		List<LoanDetails> details = loanDetailsRepository.findByAccountNumber(accountNumber);
		if (details != null) {
			return details;
		}
		return null;
	}

	@Override
	public List<LoanDetails> fetchLoanByAccountNumber(long accountNumber) {
		return loanDetailsRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public LoanDetails fetchById(int id) {
		return loanDetailsRepository.findById(id).get();
	}

	@Override
	public LoanDetails payLoan(int id, LoanDetails loanDetails) {
		LoanDetails details = loanDetailsRepository.findById(id).get();
		details.setStatus("Paid");
		return loanDetailsRepository.save(details);
	}

	@Override
	public double loanRepay() {
		double sum = 0;
		List<LoanDetails> details = loanDetailsRepository.findByStatus("Paid");
		for (LoanDetails detail : details) {
			sum += detail.getMonthlyPay();
		}
		return sum;
	}

}
