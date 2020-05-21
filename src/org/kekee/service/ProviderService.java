package org.kekee.service;

import java.util.List;

import org.kekee.entity.Provider;

/**
 * @author cocoa
 */
public interface ProviderService {
	List<Provider> findAllProvider();
	Provider findProvider(int id);
	boolean proModify(Provider pro);
	String proDelete(int id);
	boolean proAdd(Provider pro);
	List<Provider> findByCodeOrName(String proCode,String proName);
}
