package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Loan;
import com.example.demo.entity.Manager;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LoanDetailsService;
import com.example.demo.service.LoanService;
import com.example.demo.service.ManagerService;
import com.example.demo.service.TransactionHistoryService;

@Controller
public class ManagerController {
	String managerId = "MR-01";
	String updateSuccessfullMsg = "";
	String updateUnsuccessfullMsg = "";
	String name = "Rokon";

	@Autowired
	ManagerService managerService;
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;
	@Autowired
	LoanService loanService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	TransactionHistoryService historyService;
	@Autowired
	LoanDetailsService loanDetailsService;
	@Autowired
	TransactionHistoryRepository historyRepository;

	@PostMapping("manager/addManager")
	@ResponseBody
	public String addManager(@ModelAttribute Manager manager) {
		managerService.addManager(manager);
		return "Manager Added Successfully";
	}

	@PostMapping("manager/dashboard")
	public String goToManagerDashboard(@ModelAttribute Manager manager, Model model) {
		managerId = manager.getManagerId();
		System.out.println(managerId);
		String password = manager.getPassword();
		System.out.println(password);
		String approved = "Approved";
		Manager Check = managerService.checkLogin(managerId, password);
		if (Check != null) {
			Manager manager2 = managerService.fetchByManagerId(managerId);
			name = manager2.getName();
			List<Customer> approvesCusList = customerService.fetchApprovedCustomer(approved);
			long allUser = accountService.countAllUser();
			long totalEmployee = employeeService.countAllEmployee();
			double totalCredit = historyService.countAllCredit();
			double totalDebit = historyService.countAllDebit();
			double totalLoan = loanService.countTotalLoan();
			double cteditThisMonth = historyService.creditThisMonth();
			double debitThisMonth = historyService.debitThisMonth();
			double loanRepay = loanDetailsService.loanRepay();
			model.addAttribute("allUser", allUser);
			model.addAttribute("totalEmployee", totalEmployee);
			model.addAttribute("totalCredit", totalCredit);
			model.addAttribute("totalDebit", totalDebit);
			model.addAttribute("totalLoan", totalLoan);
			model.addAttribute("cteditThisMonth", cteditThisMonth);
			model.addAttribute("debitThisMonth", debitThisMonth);
			model.addAttribute("loanRepay", loanRepay);
			model.addAttribute("name", name);
			model.addAttribute("cusList", approvesCusList);
			return "dahsboard/manager/dashboard";
		}
		model.addAttribute("name", name);
		return "errorLogin";
	}

	@GetMapping("manager/dashboard")
	public String goToManagerDashboardWithGet(@ModelAttribute Manager manager, Model model) {
		Manager manager2 = managerService.fetchByManagerId(managerId);
		name = manager2.getName();
		long allUser = accountService.countAllUser();
		long totalEmployee = employeeService.countAllEmployee();
		double totalCredit = historyService.countAllCredit();
		double totalDebit = historyService.countAllDebit();
		double totalLoan = loanService.countTotalLoan();
		double cteditThisMonth = historyService.creditThisMonth();
		double debitThisMonth = historyService.debitThisMonth();
		double loanRepay = loanDetailsService.loanRepay();
		model.addAttribute("allUser", allUser);
		model.addAttribute("totalEmployee", totalEmployee);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("totalLoan", totalLoan);
		model.addAttribute("cteditThisMonth", cteditThisMonth);
		model.addAttribute("debitThisMonth", debitThisMonth);
		model.addAttribute("loanRepay", loanRepay);
		model.addAttribute("name", name);
		return "dahsboard/manager/dashboard";
	}

	@RequestMapping("manager/generateId")
	public String goToGenerateAccountPage(@ModelAttribute Customer customer, Model model) {
		int id = customer.getId();
		model.addAttribute("customerId", id);
		model.addAttribute("name", name);
		return "dahsboard/manager/generateAccount";
	}

	@PostMapping("manager/confirmAccount")
	public String confirmAccount(@ModelAttribute Customer customer, Model model) {
		int customerId = customer.getId();
		customerService.confirmAccount(customerId, customer);
		String approved = "Approved";
		long accountNumber = 0;
		List<Customer> cusList = customerService.fetchApprovedCustomerWithAccount(approved, accountNumber);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/manager/approvedUsers";
	}

	@PostMapping("manager/statusReject")
	public String statusReject(@ModelAttribute Customer customer, Model model) {
		System.out.println(customer.getId());
		int id = customer.getId();
		String rejected = "Rejected";
		customerService.statusReject(id, customer);
		List<Customer> cusList = customerService.fetchRejectedCustomer(rejected);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/manager/rejectedUsers";
	}

	@RequestMapping("manager/allUsers")
	public String goToAllUsersPage(@ModelAttribute Customer customer, Model model) {
		List<Customer> cusList = customerService.fetchAllCustomer();
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/manager/allUsers";
	}

	@RequestMapping("manager/rejectedUsers")
	public String goToApprovedUsersPage(@ModelAttribute Customer customer, Model model) {
		String rejected = "Rejected";
		List<Customer> cusList = customerService.fetchRejectedCustomer(rejected);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/manager/rejectedUsers";
	}

	@RequestMapping("manager/pendingUsers")
	public String goToPendingUsersPage(@ModelAttribute Customer customer, Model model) {
		String approved = "Approved";
		long accountNumber = 0;
		List<Customer> cusList = customerService.fetchApprovedCustomerWithNoAccount(approved, accountNumber);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/manager/pendingUsers";
	}

	@RequestMapping("manager/approvedUsers")
	public String goToRejectedUsersPage(@ModelAttribute Customer customer, Model model) {
		String approved = "Approved";
		long accountNumber = 0;
		List<Customer> cusList = customerService.fetchApprovedCustomerWithAccount(approved, accountNumber);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/manager/approvedUsers";
	}

	@RequestMapping("manager/debit")
	public String goToDebitPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/manager/debit";
	}

	@RequestMapping("manager/credit")
	public String goToCreditPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/manager/credit";
	}

	@RequestMapping("manager/allLoan")
	public String goToAllLoanPage(Model model) {
		List<Loan> loans = loanService.fetchAllLoan();
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/manager/allLoan";
	}

	@RequestMapping("manager/approvedLoan")
	public String goToApprovedLoanPage(Model model) {
		String approve = "Approved";
		List<Loan> loans = loanService.fetchApprovedLoan(approve);
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/manager/approvedLoan";
	}

	@RequestMapping("manager/pendingLoan")
	public String goToPendingLoanPage(Model model) {
		String pending = "Pending";
		List<Loan> loans = loanService.fetchPendingLoan(pending);
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/manager/pendingLoan";
	}

	@RequestMapping("manager/rejectedLoan")
	public String goToRejectedLoanPage(Model model) {
		String reject = "Rejected";
		List<Loan> loans = loanService.fetchRejectedLoan(reject);
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/manager/rejectedLoan";
	}

	@RequestMapping("manager/loanDetails")
	public String goToLoanDetailsPage(@ModelAttribute Loan loan, Model model) {
		int loanId = loan.getId();
		Loan loanUser = loanService.fetchByLoanId(loanId);
		model.addAttribute("loanUser", loanUser);
		model.addAttribute("name", name);
		return "/dahsboard/manager/loanDetails";
	}

	@RequestMapping("manager/changePass")
	public String goToChangePassPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/manager/changePass";
	}

	@RequestMapping("manager/report")
	public String goToReportPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/manager/report";
	}

	@RequestMapping("manager/giveLoan")
	public String giveLoan(@ModelAttribute Loan loan, Model model) {
		String approve = "Approved";
		Loan loan2 = loanService.fetchByLoanId(loan.getId());
		loanDetailsService.giveLoan(loan2);
		System.out.println(loan2);
		List<Loan> loans = loanService.fetchApprovedLoan(approve);
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/manager/approvedLoan";
	}

	@RequestMapping("manager/rejectLoan")
	public String rejectLoan(@ModelAttribute Loan loan) {
		loanService.rejectLoan(loan.getId(), loan);
		return "/dahsboard/manager/rejectedLoan";
	}

	@RequestMapping("manager/custom")
	public String custom() {
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
		System.out.println(histories);
		return "/dahsboard/manager/dashboard";
	}

	@RequestMapping("manager/profile")
	public String goToProfilePage(Model model) {
		Manager manager = managerService.fetchByManagerId(managerId);
		model.addAttribute("manager", manager);
		model.addAttribute("name", name);
		return "/dahsboard/manager/profile";
	}

	@PostMapping("manager/creditMoney")
	private String creditMoneyToCustomer(@ModelAttribute Account account, Model model) {
		long accountNumber = account.getAccountNumber();
		String branch = account.getBranchName();
		Account account2 = accountService.creditMoney(accountNumber, branch, account);
		if (account2 != null) {
			model.addAttribute("name", name);
			model.addAttribute("sMsg", "Successfull");
			model.addAttribute("usMsg", "");
			return "dahsboard/manager/credit";
		}
		model.addAttribute("name", name);
		model.addAttribute("sMsg", "");
		model.addAttribute("usMsg", "Unsuccessfull");
		return "/dahsboard/manager/credit";
	}

	@PostMapping("manager/debitMoney")
	private String debitMoneyToCustomer(@ModelAttribute Account account, Model model) {
		long accountNumber = account.getAccountNumber();
		String branch = account.getBranchName();
		Account account2 = accountService.debitMoney(accountNumber, branch, account);
		if (account2 != null) {
			model.addAttribute("name", name);
			model.addAttribute("sMsg", "Successfull");
			model.addAttribute("usMsg", "");
			return "dahsboard/manager/debit";
		}
		model.addAttribute("name", name);
		model.addAttribute("sMsg", "");
		model.addAttribute("usMsg", "Unsuccessfull");
		return "/dahsboard/manager/debit";
	}

	@PostMapping("manager/changePassword")
	public String changePassword(@ModelAttribute Manager manager, Model model) {
		String oldPass = manager.getPassword();
		String newPass = manager.getName();
		System.out.println(oldPass);
		System.out.println(newPass);
		Manager manager2 = managerService.updatePassword(managerId, oldPass, newPass);
		if (manager2 != null) {
			updateSuccessfullMsg = "Updated Successfully";
			updateUnsuccessfullMsg = "";
		} else {
			updateSuccessfullMsg = "";
			updateUnsuccessfullMsg = "Account number and Password doesn't match";
		}
		model.addAttribute("updateSuccessfullMsg", updateSuccessfullMsg);
		model.addAttribute("updateUnsuccessfullMsg", updateUnsuccessfullMsg);
		model.addAttribute("name", name);
		return "/dahsboard/manager/changePass";
	}

	@PostMapping("manager/byBetweenDate")
	public String byBetweenDate(@ModelAttribute Manager manager, Model model) {
		List<TransactionHistory> histories = historyService.fetchByBetweenDate(manager.getDob(),
				manager.getJoiningDate());
		model.addAttribute("histories", histories);
		return "/dahsboard/manager/report";
	}

	@PostMapping("manager/byBetweenDateAndAccount")
	public String byBetweenDateAndAccount(@ModelAttribute Manager manager, Model model) {
		List<TransactionHistory> histories = historyService.fetchByBetweenDateAndAccount(manager.getDob(),
				manager.getJoiningDate(), manager.getNid());
		model.addAttribute("histories", histories);
		return "/dahsboard/manager/report";
	}

	@PostMapping("manager/byAccountNumber")
	public String byAccountNumber(@ModelAttribute TransactionHistory transactionHistory, Model model) {
		List<TransactionHistory> histories = historyService.fetchByAccountNumber(transactionHistory.getAccountNumber());
		model.addAttribute("histories", histories);
		return "/dahsboard/manager/report";
	}

}
