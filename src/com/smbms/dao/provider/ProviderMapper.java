package com.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.Provider;

public interface ProviderMapper {
	List<Provider> findAllProvider();//获取全部供应商，未使用
	Provider findProvider(int id);//根据id查询供应商
	int proModify(Provider pro);//修改供应商信息
	int	proDelete(int id);//删除单个供应商
	int findBill(int id);//查询供应商的订单
	int proAdd(Provider pro);//添加供应商
	//查询供应商列表
	List<Provider> findByProCodeOrProName(@Param("proCode")String proCode,@Param("proName")String proName);
}
