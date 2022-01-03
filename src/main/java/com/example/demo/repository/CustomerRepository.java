package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByAccountNumberAndPassword(long accountNumber, String password);

	List<Customer> findByEmpApproveStatusEquals(String approved);

	List<Customer> findByEmpApproveStatus(String pending);

	List<Customer> findByEmpApproveStatusAndAccountNumber(String approved, long accountNumber);

	List<Customer> findByEmpApproveStatusEqualsAndAccountNumberNot(String approved, long accountNumber);

	Customer findByAccountNumber(long accountNumber);

	Customer findByAccountNumberAndBranch(long accountNumber, String branch);

}
