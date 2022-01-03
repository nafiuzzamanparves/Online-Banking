package com.example.demo.service;

import com.example.demo.entity.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public Employee checkLogin(int empId, String password);

	public Employee fetchByEmpId(int empId);

	public Employee updatePassword(int empId, String oldPass, String newPass);

	public long countAllEmployee();

}
