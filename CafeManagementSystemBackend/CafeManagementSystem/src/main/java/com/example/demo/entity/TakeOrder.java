package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="TakeOrder")
public class TakeOrder {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "order_id", unique = true)
	  private long orderId;
	
	@ManyToOne(targetEntity = Customer.class, cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", referencedColumnName = "customer_id")
    private Customer customer;
 
	  @Column(name = "order_date")
	  @Temporal(TemporalType.DATE)
	  private Date orderDate;

	  @Column(name = "order_time")
	  @Temporal(TemporalType.TIME)
      private Date orderTime;
	  
	  @ManyToOne(targetEntity = Product.class, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	  @JoinColumn(name = "productId", referencedColumnName = "productId")
	  private Product product;
	

	  @Column(name = "quantity")
	  private int quantity;
	  
	  @Column(name = "total_price")
	  private double totalPrice;
	
	
	 @NotBlank(message = "Order status cannot be blank")
	 @Column(name = "status")
     private String status;

	public TakeOrder()
	{
		
	}

	public TakeOrder(long orderId, Customer customer, Date orderDate, Date orderTime, Product product, int quantity, String status)
	{
		super();
		this.orderId = orderId;
		this.customer =customer;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.product = product;
		this.quantity = quantity;
		this.status = status;
		calculateTotalPrice(); 
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	// Calculate total price based on menu item price and quantity
		public void calculateTotalPrice() {
			if (product != null) {
				totalPrice = product.getProductPrice() * quantity;
			} else {
				totalPrice = 0.0; 
			}
		}
}	