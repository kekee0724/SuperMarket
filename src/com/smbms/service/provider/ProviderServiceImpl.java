package com.smbms.service.provider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smbms.dao.provider.ProviderMapper;
import com.smbms.pojo.Provider;
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
	@Resource
	ProviderMapper pm;
	@Override
	public List<Provider> findAllProvider() {
		// TODO Auto-generated method stub
		List<Provider> list=pm.findAllProvider();
		return list;
	}
	@Override
	public Provider findProvider(int id) {
		// TODO Auto-generated method stub
		return pm.findProvider(id);
	}
	@Override
	public boolean proModify(Provider pro) {
		// TODO Auto-generated method stub
		if(pm.proModify(pro)>0){
			return true;
		}
		return false;
	}
	@Override
	public String proDelete(int id) {
		// TODO Auto-generated method stub
		if(pm.findBill(id)==0){
			if(pm.proDelete(id)>0){
				return "true";
			}else{
				return "false";
			}
		}
		else{
			 return String.valueOf(pm.findBill(id));
		}
	}
	@Override
	public boolean proAdd(Provider pro) {
		// TODO Auto-generated method stub
		if(pm.proAdd(pro)>0){
			return true;
		}
		return false;
	}
	@Override
	public List<Provider> findByCodeOrName(String proCode,String proName) {
		// TODO Auto-generated method stub
		List<Provider>list=pm.findByProCodeOrProName(proCode, proName);
		return list;
	}
}
