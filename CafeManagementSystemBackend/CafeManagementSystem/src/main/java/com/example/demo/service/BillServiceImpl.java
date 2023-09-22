package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BillRepository;
import com.example.demo.dao.TakeOrderRepository;
import com.example.demo.entity.Bill;
import com.example.demo.entity.Customer;
import com.example.demo.entity.TakeOrder;

@Service
public class BillServiceImpl implements BillService 
{

	@Autowired
	private BillRepository billRespository;
	
	@Autowired 
	private TakeOrderRepository takeOrderRepository;
	
	@Autowired 
	private CustomerService customerService;
	
	public BillServiceImpl(BillRepository billRepository,CustomerService customerService)
	{
		super();
		this.billRespository= billRepository;
		this.customerService=customerService;
	}
	
	
	/*
	 * @Override public Bill addBill(Bill bill, long orderId, long customerId) { //
	 * TODO Auto-generated method stub TakeOrder takeOrder =
	 * takeOrderRepository.getById(orderId); bill.setTakeOrder(takeOrder);
	 * bill.setTotalPrice(takeOrder.getTotalPrice());
	 * bill.setPaidDate(LocalDate.now());
	 * bill.setPaidAmount(takeOrder.getTotalPrice()); if (bill.getTotalPrice() ==
	 * bill.getPaidAmount()) { takeOrder.setStatus("PAID"); } else {
	 * 
	 * takeOrder.setStatus("NOT-PAID"); } Customer customer =
	 * customerService.findUserById(customerId).orElse(null);
	 * bill.setCustomer(customer); return billRespository.save(bill); }
	 */	@Override
	public Bill addBill(Bill bill, long orderId, long customerId) {
		// TODO Auto-generated method stub
		TakeOrder takeOrder = takeOrderRepository.getById(orderId);
		bill.setOrderId(takeOrder.getOrderId());
		bill.setTotalPrice(takeOrder.getTotalPrice());
		bill.setBillId(customerId);
		bill.setCardNumber(bill.getCardNumber());
		bill.setCvv(bill.getCvv());
		bill.setNameOnCard(bill.getNameOnCard());
		bill.setPaidDate(LocalDate.now());
		bill.setPaidAmount(takeOrder.getTotalPrice());
		if (bill.getTotalPrice() == bill.getPaidAmount()) {
			takeOrder.setStatus("PAID");
		} else {

			takeOrder.setStatus("NOT-PAID");
		}
		Customer customer = customerService.findUserById(customerId).orElse(null);
		bill.setCustomer(customer);
    	return billRespository.save(bill);
	}



	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		return billRespository.findAll();
	
	}

	@Override
	public Optional<Bill> findBillById(long id) {
		// TODO Auto-generated method stub
		return this.billRespository.findById(id);
	}

	@Override
	public void deleteBill(long billId) {
		// TODO Auto-generated method stub
		billRespository.findById(billId);		
		if (billRespository.existsById(billId)) {
			billRespository.deleteById(billId);
		}
	}

	@Override
	public List<Bill> getBillsByOrderId(long orderId) {
		// TODO Auto-generated method stub
		return billRespository.findByOrderId(orderId);
	}

	@Override
	public void deleteBillsByOrderId(long orderId) {
		// TODO Auto-generated method stub
		billRespository.findByOrderId(orderId).forEach(bill -> billRespository.delete(bill));
		
	}

	@Override
	public List<Bill> findBillsByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return billRespository.findByCustomerCustomerId(customerId);
	}
	
	
}
