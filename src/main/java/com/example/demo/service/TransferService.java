package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Transfer;

public interface TransferService {

	public Transfer sendMoney(Transfer transfer);

	public List<Transfer> fetchByFromAccountNumber(long accountNumber);

	public double countTotalSend(long accountNumber);

	public double countTotalSendThisMonth(long accountNumber);

}
