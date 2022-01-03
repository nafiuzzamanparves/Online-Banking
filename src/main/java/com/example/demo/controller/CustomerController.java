package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Loan;
import com.example.demo.entity.LoanDetails;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.Transfer;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.LoanDetailsService;
import com.example.demo.service.LoanService;
import com.example.demo.service.TransactionHistoryService;
import com.example.demo.service.TransferService;

@Controller
public class CustomerController {
	long accountNumber = 123456789;
	String updateSuccessfullMsg = "";
	String updateUnsuccessfullMsg = "";
	String name = "";

	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;
	@Autowired
	TransferService transferService;
	@Autowired
	LoanService loanService;
	@Autowired
	LoanDetailsService loanDetailsService;
	@Autowired
	TransactionHistoryService historyService;

	@PostMapping("customer/registrationMassage")
	public String registrationMassage(@ModelAttribute Customer customer) {
		customerService.saveCustomer(customer);
		return "registrationMassage";
	}

	@PostMapping("customer/dashboard")
	public String goToEmployeeDashboard(@ModelAttribute Customer customer, Model model) {
		accountNumber = customer.getAccountNumber();
		String password = customer.getPassword();
		Customer Check = customerService.checkLogin(accountNumber, password);
		if (Check != null) {
			Account acc = accountService.fetchBalaceByAccountNumber(accountNumber);
			Customer customer2 = customerService.fetchCustomreByAccountNumber(accountNumber);
			name = customer2.getName();
			double balance = acc.getBalance();
			double totalCredit = historyService.countAllCreditIndiVis(accountNumber);
			double totalDebit = historyService.countAllDebitIndiVis(accountNumber);
			double totalSend = transferService.countTotalSend(accountNumber);
			double transaction = historyService.countTransaction(accountNumber);
			double creditThisMonth = historyService.countAllCreditThisMonthIndiVis(accountNumber);
			double debitThisMonth = historyService.countAllDebitThisMonthIndiVis(accountNumber);
			double sendThisMonth = transferService.countTotalSendThisMonth(accountNumber);
			model.addAttribute("balance", balance);
			model.addAttribute("totalCredit", totalCredit);
			model.addAttribute("totalDebit", totalDebit);
			model.addAttribute("totalSend", totalSend);
			model.addAttribute("transaction", transaction);
			model.addAttribute("creditThisMonth", creditThisMonth);
			model.addAttribute("debitThisMonth", debitThisMonth);
			model.addAttribute("sendThisMonth", sendThisMonth);
			model.addAttribute("name", name);
			return "/dahsboard/customer/dashboard";
		}
		return "errorLogin";
	}

	@GetMapping("customer/dashboard")
	public String goToEmployeeDashboardWithGet(Model model) {
		if (accountNumber == 0) {
			accountNumber = 123456789;
		}
		Account acc = accountService.fetchBalaceByAccountNumber(accountNumber);
		Customer customer2 = customerService.fetchCustomreByAccountNumber(accountNumber);
		name = customer2.getName();
		double balance = acc.getBalance();
		double totalCredit = historyService.countAllCreditIndiVis(accountNumber);
		double totalDebit = historyService.countAllDebitIndiVis(accountNumber);
		double totalSend = transferService.countTotalSend(accountNumber);
		double transaction = historyService.countTransaction(accountNumber);
		double creditThisMonth = historyService.countAllCreditThisMonthIndiVis(accountNumber);
		double debitThisMonth = historyService.countAllDebitThisMonthIndiVis(accountNumber);
		double sendThisMonth = transferService.countTotalSendThisMonth(accountNumber);
		model.addAttribute("balance", balance);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("totalSend", totalSend);
		model.addAttribute("transaction", transaction);
		model.addAttribute("creditThisMonth", creditThisMonth);
		model.addAttribute("debitThisMonth", debitThisMonth);
		model.addAttribute("sendThisMonth", sendThisMonth);
		model.addAttribute("name", name);
		return "/dahsboard/customer/dashboard";
	}

	@GetMapping("customer/gasBill")
	public String goToGasBillPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/customer/gasBill";
	}

	@GetMapping("customer/electricityBill")
	public String goToElectricityBillPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/customer/electricityBill";
	}

	@GetMapping("customer/billHistory")
	public String goToBillHistoryPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/customer/billHistory";
	}

	@GetMapping("customer/transfer")
	public String goToTransferPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/customer/transfer";
	}

	@GetMapping("customer/tranferHistory")
	public String goToTransferHistoryPage(Model model) {
		List<Transfer> transferList = transferService.fetchByFromAccountNumber(accountNumber);
		model.addAttribute("transList", transferList);
		model.addAttribute("name", name);
		return "/dahsboard/customer/tranferHistory";
	}

	@GetMapping("customer/debitHistory")
	public String goToDebitHistoryPage(Model model) {
		String debit = "Yes";
		List<TransactionHistory> transactionHistories = historyService.fetchByAccountNumberAndDebit(accountNumber,
				debit);
		model.addAttribute("trHistory", transactionHistories);
		model.addAttribute("name", name);
		return "/dahsboard/customer/debitHistory";
	}

	@GetMapping("customer/creditHistory")
	public String goToCreditHistoryPage(Model model) {
		String credit = "Yes";
		List<TransactionHistory> transactionHistories = historyService.fetchByAccountNumberAndCredit(accountNumber,
				credit);
		model.addAttribute("trHistory", transactionHistories);
		model.addAttribute("name", name);
		return "/dahsboard/customer/creditHistory";
	}

	@RequestMapping("/customer/profile")
	public String goToProfilePage(Model model) {
		Customer customer = customerService.fetchCustomreByAccountNumber(accountNumber);
		model.addAttribute("customer", customer);
		model.addAttribute("name", name);
		return "/dahsboard/customer/profile";
	}

	@GetMapping("customer/loanRequestForm")
	public String goToLoanRequestFormPage(Model model) {
		Customer customer = customerService.fetchCustomreByAccountNumber(accountNumber);
		String name = customer.getName();
		long account = customer.getAccountNumber();
		model.addAttribute("name", name);
		model.addAttribute("account", account);
		return "/dahsboard/customer/loanRequestForm";
	}

	@GetMapping("customer/loanDetails")
	public String goToLoanDetailsPage(Model model) {
		System.out.println(accountNumber);
		List<LoanDetails> loans = loanDetailsService.fetchLoan(accountNumber);
		String loanMsg = "You have not been given any loan";
		if (loans.size() != 0) {
			model.addAttribute("loans", loans);
		} else {
			model.addAttribute("loanMsg", loanMsg);
		}
		model.addAttribute("name", name);
		return "/dahsboard/customer/loanDetails";
	}

	@GetMapping("customer/changePass")
	public String goToChangePasswordPage(Model model) {
		updateSuccessfullMsg = "";
		updateUnsuccessfullMsg = "";
		model.addAttribute("updateSuccessfullMsg", updateSuccessfullMsg);
		model.addAttribute("updateUnsuccessfullMsg", updateUnsuccessfullMsg);
		model.addAttribute("name", name);
		return "/dahsboard/customer/changePass";
	}

	@PostMapping("customer/applyLoan")
	public String applyForLoan(@ModelAttribute Loan loan, Model model) {
		loanService.requestForLoan(loan);
		System.out.println(loan);
		model.addAttribute("name", name);
		model.addAttribute("msg", "Successfully Requested");
		return "/dahsboard/customer/loanRequestForm";
	}

	@PostMapping("customer/changePassword")
	public String changePassword(@ModelAttribute Customer customer, Model model) {
		String oldPass = customer.getPassword();
		String newPass = customer.getName();
		System.out.println(oldPass);
		System.out.println(newPass);
		Customer customer2 = customerService.updatePassword(accountNumber, oldPass, newPass);
		if (customer2 != null) {
			updateSuccessfullMsg = "Updated Successfully";
			updateUnsuccessfullMsg = "";
		} else {
			updateSuccessfullMsg = "";
			updateUnsuccessfullMsg = "Account number and Password doesn't match";
		}
		model.addAttribute("updateSuccessfullMsg", updateSuccessfullMsg);
		model.addAttribute("updateUnsuccessfullMsg", updateUnsuccessfullMsg);
		model.addAttribute("name", name);
		return "/dahsboard/customer/changePass";
	}

	@PostMapping("customer/sendMoney")
	public String sendMoney(@ModelAttribute Transfer transfer, Model model) {
		transfer.setFromAccountNumber(accountNumber);
		Transfer transfer2 = transferService.sendMoney(transfer);
		if (transfer2.getBranchName() == "notEnoughMoney") {
			model.addAttribute("sMsg", "");
			model.addAttribute("usMsg", "You have not enogh Money");
			return "/dahsboard/customer/transfer";
		} else if (transfer2.getBranchName() == "unmatchedBranch") {
			model.addAttribute("sMsg", "");
			model.addAttribute("usMsg", "We do not hvae account in specified branch");
			return "/dahsboard/customer/transfer";
		} else if (transfer2.getBranchName() == "noAccount") {
			model.addAttribute("sMsg", "");
			model.addAttribute("usMsg", "We do not hvae any account you sepcified");
			return "/dahsboard/customer/transfer";
		}
		Account acc = accountService.fetchBalaceByAccountNumber(accountNumber);
		double balance = acc.getBalance();
		model.addAttribute("balance", balance);
		model.addAttribute("name", name);
		model.addAttribute("sMsg", "Successfull");
		model.addAttribute("usMsg", "");
		return "/dahsboard/customer/transfer";
	}
}
