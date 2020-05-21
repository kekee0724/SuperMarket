package org.kekee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author cocoa
 */
@Data
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
}
