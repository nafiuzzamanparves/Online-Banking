package com.example.demo.service;

import com.example.demo.entity.Manager;

public interface ManagerService {

	public Manager addManager(Manager manager);

	public Manager checkLogin(String managerId, String password);

	public Manager fetchByManagerId(String managerId);

	public Manager updatePassword(String managerId, String oldPass, String newPass);

}
