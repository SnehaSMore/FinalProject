package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Bill;

public interface BillService 
{
	//Bill addBill(Bill bill,long orderId,long customerId);
	Bill addBill(Bill bill,long orderId,long customerId);

	List<Bill> getAllBills();
	public Optional<Bill> findBillById(long id);
	void deleteBill(long billId);
	List<Bill> getBillsByOrderId(long orderId);
	public void deleteBillsByOrderId(long orderId);
	public List<Bill> findBillsByCustomerId(long customerId);	 
}
