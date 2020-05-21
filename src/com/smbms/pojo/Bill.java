package com.smbms.pojo;

import java.util.Date;


public class Bill {
	private Integer id;
	private String billCode;
	private String productName;
	private String productDesc;
	private String productUnit;
	private int productCount;
	private double totalPrice;
	private int createBy;
	private Date creationDate;
	private int isPayment;
	private int providerId;
	private Provider provider;
	
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", billCode=" + billCode + ", productName="
				+ productName + ", productDesc=" + productDesc
				+ ", productUnit=" + productUnit + ", productCount="
				+ productCount + ", totalPrice=" + totalPrice + ", createBy="
				+ createBy + ", creationDate=" + creationDate + ", isPayment="
				+ isPayment + ", providerId=" + providerId + ", provider="
				+ provider + "]";
	}
}
