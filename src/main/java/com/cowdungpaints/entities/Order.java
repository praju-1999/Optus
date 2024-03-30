package com.cowdungpaints.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	@ManyToOne(cascade= CascadeType.ALL)
	private Customer customer;
	
	@OneToOne(cascade= CascadeType.ALL)
	private OrderDetails orderDetails;

	@OneToOne(cascade= CascadeType.ALL)
	private Address address;

	@Column(name = "unit_price")
	private long unitPrice;

	@Column(name = "total_price")
	private long totalPrice;

	@Column(name = "quantity")
	private int quatity;

	@Column(name = "isActive")
	private String isActive;

	@Column(name = "isDelivered")
	private String isDelivered;

	@Column(name = "stage")
	private String stage;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "delivered_date")
	private Date deliveredDate;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderDetails getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(String isDelivered) {
		this.isDelivered = isDelivered;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public String toString() {
		return "Order [Id=" + Id + ", customer=" + customer + ", orderDetails=" + orderDetails + ", address=" + address
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", quatity=" + quatity + ", isActive="
				+ isActive + ", isDelivered=" + isDelivered + ", stage=" + stage + ", createdDate=" + createdDate
				+ ", deliveredDate=" + deliveredDate + "]";
	}

	
}
