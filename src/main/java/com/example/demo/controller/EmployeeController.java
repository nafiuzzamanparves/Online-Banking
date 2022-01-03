package com.example.demo.controller;

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
import com.example.demo.entity.Employee;
import com.example.demo.entity.Loan;
import com.example.demo.entity.LoanDetails;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LoanDetailsService;
import com.example.demo.service.LoanService;
import com.example.demo.service.TransactionHistoryService;

@Controller
public class EmployeeController {
	int empId = 1263014;
	String updateSuccessfullMsg = "";
	String updateUnsuccessfullMsg = "";
	String name = "Parves";

	@Autowired
	EmployeeService employeeService;
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;
	@Autowired
	TransactionHistoryRepository historyRepository;
	@Autowired
	TransactionHistoryService historyService;
	@Autowired
	LoanService loanService;
	@Autowired
	LoanDetailsService loanDetailsService;

	@PostMapping("employee/addEmployee")
	@ResponseBody
	public String addEmployee(@ModelAttribute Employee employee) {
		employeeService.addEmployee(employee);
		return "Employee Added Successfully";
	}

	@PostMapping("employee/dashboard")
	public String goToEmployeeDashboard(@ModelAttribute Employee employee, Model model) {
		empId = employee.getEmpId();
		String password = employee.getPassword();
		Employee Check = employeeService.checkLogin(empId, password);
		if (Check != null) {
			Employee employee2 = employeeService.fetchByEmpId(empId);
			name = employee2.getName();
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
			return "dahsboard/employee/dashboard";
		}
		return "errorLogin";
	}

	@GetMapping("employee/dashboard")
	public String goToEmployeeDashboardWithGetMapping(Model model) {
		Employee employee2 = employeeService.fetchByEmpId(empId);
		name = employee2.getName();
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
		return "dahsboard/employee/dashboard";
	}

	@RequestMapping("employee/allUsers")
	public String goToAllUsersPage(@ModelAttribute Customer customer, Model model) {
		List<Customer> cusList = customerService.fetchAllCustomer();
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/employee/allUsers";
	}

	@RequestMapping("employee/rejectedUsers")
	public String goToApprovedUsersPage(@ModelAttribute Customer customer, Model model) {
		String rejected = "Rejected";
		List<Customer> cusList = customerService.fetchRejectedCustomer(rejected);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/employee/rejectedUsers";
	}

	@RequestMapping("employee/pendingUsers")
	public String goToPendingUsersPage(@ModelAttribute Customer customer, Model model) {
		String pending = "pending";
		List<Customer> cusList = customerService.fetchPendingCustomers(pending);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/employee/pendingUsers";
	}

	@RequestMapping("employee/approvedUsers")
	public String goToRejectedUsersPage(@ModelAttribute Customer customer, Model model) {
		String approved = "Approved";
		List<Customer> cusList = customerService.fetchApprovedCustomer(approved);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/employee/approvedUsers";
	}

	@PostMapping("employee/statusApprove")
	public String statusApprove(@ModelAttribute Customer customer, Model model) {
		System.out.println(customer.getId());
		int id = customer.getId();
		customerService.statusApprove(id, customer);
		String approved = "Approved";
		List<Customer> cusList = customerService.fetchApprovedCustomer(approved);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/employee/approvedUsers";
	}

	@PostMapping("employee/statusReject")
	public String statusReject(@ModelAttribute Customer customer, Model model) {
		System.out.println(customer.getId());
		int id = customer.getId();
		String rejected = "Rejected";
		customerService.statusReject(id, customer);
		List<Customer> cusList = customerService.fetchRejectedCustomer(rejected);
		model.addAttribute("cusList", cusList);
		model.addAttribute("name", name);
		return "/dahsboard/employee/rejectedUsers";
	}

	@RequestMapping("employee/debit")
	public String goToDebitPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/employee/debit";
	}

	@RequestMapping("employee/credit")
	public String goToCreditPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/employee/credit";
	}

	@RequestMapping("employee/report")
	public String goToReportPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/employee/report";
	}

	@RequestMapping("employee/profile")
	public String goToProfilePage(Model model) {
		Employee employee = employeeService.fetchByEmpId(empId);
		model.addAttribute("employee", employee);
		model.addAttribute("name", name);
		return "/dahsboard/employee/profile";
	}

	@RequestMapping("/employee/changePass")
	public String goToChangePassPage(Model model) {
		model.addAttribute("name", name);
		return "/dahsboard/employee/changePass";
	}

	@RequestMapping("employee/approvedLoan")
	public String goToApprovedLoanPage(Model model) {
		List<Loan> loans = loanService.fetchApprovedLoan("Approved");
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/employee/approvedLoan";
	}

	@RequestMapping("/employee/loanDetails")
	public String goToLoanDetailsPage(@ModelAttribute Loan loan, Model model) {
		List<LoanDetails> loans = loanDetailsService.fetchLoanByAccountNumber(loan.getAccountNumber());
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/employee/loanDetails";
	}

	@RequestMapping("/employee/payLoan")
	public String payLoan(@ModelAttribute LoanDetails loanDetails, Model model) {
		LoanDetails payLoan = loanDetailsService.payLoan(loanDetails.getId(), loanDetails);
		List<LoanDetails> loans = loanDetailsService.fetchLoanByAccountNumber(payLoan.getAccountNumber());
		model.addAttribute("loans", loans);
		model.addAttribute("name", name);
		return "/dahsboard/employee/loanDetails";
	}

	@PostMapping("employee/creditMoney")
	private String creditMoneyToCustomer(@ModelAttribute Account account, Model model) {
		long accountNumber = account.getAccountNumber();
		String branch = account.getBranchName();
		Account account2 = accountService.creditMoney(accountNumber, branch, account);
		if (account2 != null) {
			model.addAttribute("name", name);
			model.addAttribute("sMsg", "Successfull");
			model.addAttribute("usMsg", "");
			return "dahsboard/employee/credit";
		}
		model.addAttribute("name", name);
		model.addAttribute("sMsg", "");
		model.addAttribute("usMsg", "Unsuccessfull");
		return "/dahsboard/employee/credit";
	}

	@PostMapping("employee/debitMoney")
	private String debitMoneyToCustomer(@ModelAttribute Account account, Model model) {
		long accountNumber = account.getAccountNumber();
		String branch = account.getBranchName();
		Account account2 = accountService.debitMoney(accountNumber, branch, account);
		if (account2 != null) {
			model.addAttribute("name", name);
			model.addAttribute("sMsg", "Successfull");
			model.addAttribute("usMsg", "");
			return "dahsboard/employee/debit";
		}
		model.addAttribute("name", name);
		model.addAttribute("sMsg", "");
		model.addAttribute("usMsg", "Unsuccessfull");
		return "/dahsboard/employee/debit";
	}

	@PostMapping("employee/changePassword")
	public String changePassword(@ModelAttribute Employee employee, Model model) {
		String oldPass = employee.getPassword();
		String newPass = employee.getName();
		System.out.println(oldPass);
		System.out.println(newPass);
		Employee employee2 = employeeService.updatePassword(empId, oldPass, newPass);
		if (employee2 != null) {
			updateSuccessfullMsg = "Updated Successfully";
			updateUnsuccessfullMsg = "";
		} else {
			updateSuccessfullMsg = "";
			updateUnsuccessfullMsg = "Account number and Password doesn't match";
		}
		model.addAttribute("updateSuccessfullMsg", updateSuccessfullMsg);
		model.addAttribute("updateUnsuccessfullMsg", updateUnsuccessfullMsg);
		model.addAttribute("name", name);
		return "/dahsboard/employee/changePass";
	}

}
