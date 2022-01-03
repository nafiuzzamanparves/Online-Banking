package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepository managerRepository;

	@Override
	public Manager addManager(Manager manager) {
		return managerRepository.save(manager);
	}

	@Override
	public Manager checkLogin(String managerId, String password) {
		return managerRepository.findByManagerIdAndPassword(managerId, password);
	}

	@Override
	public Manager fetchByManagerId(String managerId) {
		return managerRepository.findByManagerId(managerId);
	}

	@Override
	public Manager updatePassword(String managerId, String oldPass, String newPass) {
		Manager manager = managerRepository.findByManagerIdAndPassword(managerId, oldPass);
		if (manager != null) {
			manager.setPassword(newPass);
			return managerRepository.save(manager);
		}
		return null;
	}

}
