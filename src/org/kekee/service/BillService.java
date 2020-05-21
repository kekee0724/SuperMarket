package org.kekee.service;

import java.util.List;

import org.kekee.entity.Bill;

/**
 * @author cocoa
 */
public interface BillService {
	List<Bill> findAddBill();
	Bill findBillById(int id);
	boolean billAdd(Bill b);
	boolean billModify(Bill b);
	String billDel(int id);
	List<Bill> findByNameIdAndIsPayment(String productName,Integer providerId,Integer isPayment);
}
