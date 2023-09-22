package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Bill")
@SequenceGenerator(name = "generator5", sequenceName = "gen5", initialValue = 101)
public class Bill 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator5")
	@Column(name = "bill_Id")
	private long billId;
	
	@Column(name="total_price")
	private  double totalPrice;
		
	@Column(name = "order_id", unique = true)
	private Long orderId;
	
	@Column(name="name_on_card") 	
	@NotEmpty
	@Size(min=2 , message="name must contain atleast 2 characters")
	private String nameOnCard;

	@Column(name="card_number")
	@NotEmpty
	@Size(min=16 , max=16,message="cardNumber must contain 16 digits")
	private String cardNumber;

	@Column(name="exp_year")
	private String expYear;

	@Column(name="cvv")
	@NotNull
	private int cvv;

	@Column(name = "paid_date")
	private LocalDate PaidDate;

	@Column(name = "paid_amount", nullable = false)
	private double paidAmount;
	
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
//	@ManyToOne( cascade=CascadeType.MERGE)
//	@JoinColumn(name="order_id")
//
//	private TakeOrder takeOrder;

	public Bill()
	{
		
	}

	public Bill(long billId, double totalPrice,long orderId,
			@NotEmpty @Size(min = 2, message = "name must contain atleast 2 characters") String nameOnCard,
			@NotEmpty @Size(min = 16, max = 16, message = "cardNumber must contain 16 digits") String cardNumber,
			String expYear, @NotNull int cvv, LocalDate paidDate, double paidAmount, Customer customer) 
	{
		super();
		this.billId = billId;
		this.totalPrice = totalPrice;
		this.orderId = orderId;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expYear = expYear;
		this.cvv = cvv;
		PaidDate = paidDate;
		this.paidAmount = paidAmount;
		this.customer = customer;
		//this.takeOrder=takeOrder;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

//	public TakeOrder getTakeOrder() {
//		return takeOrder;
//	}
//
//	public void setTakeOrder(TakeOrder takeOrder) {
//		this.takeOrder = takeOrder;
//	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public LocalDate getPaidDate() {
		return PaidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		PaidDate = paidDate;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}	