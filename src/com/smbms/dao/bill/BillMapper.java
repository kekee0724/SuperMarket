package com.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.Bill;

public interface BillMapper {
	List<Bill> findAllBill();//获取全部订单，未使用
	Bill findBillById(int id);//根据id查询订单
	int billAdd(Bill b);//添加订单
	int billModify(Bill b);//修改订单信息
	int billDel(int id);//删除单个订单
	//获取订单列表
	List<Bill> findByNameIdAndIsPayment(@Param("productName")String productName,
			@Param("providerId")Integer providerId,@Param("isPayment") Integer isPayment);
}
