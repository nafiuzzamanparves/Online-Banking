package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee checkLogin(int empId, String password) {
		return employeeRepository.findByEmpIdAndPassword(empId, password);
	}

	@Override
	public Employee fetchByEmpId(int empId) {
		return employeeRepository.findByEmpId(empId);
	}

	@Override
	public Employee updatePassword(int empId, String oldPass, String newPass) {
		Employee employee = employeeRepository.findByEmpIdAndPassword(empId, oldPass);
		if (employee != null) {
			employee.setPassword(newPass);
			return employeeRepository.save(employee);
		}
		return null;
	}

	@Override
	public long countAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.count();
	}

}
