package com.smbms.service.provider;

import java.util.List;

import com.smbms.pojo.Provider;

public interface ProviderService {
	List<Provider> findAllProvider();
	Provider findProvider(int id);
	boolean proModify(Provider pro);
	String proDelete(int id);
	boolean proAdd(Provider pro);
	List<Provider> findByCodeOrName(String proCode,String proName);
}
