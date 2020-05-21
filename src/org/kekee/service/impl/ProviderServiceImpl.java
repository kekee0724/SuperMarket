package org.kekee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.kekee.service.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.kekee.mapper.ProviderMapper;
import org.kekee.entity.Provider;

/**
 * @author cocoa
 */
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
    @Resource
    ProviderMapper providerMapper;

    @Override
    public List<Provider> findAllProvider() {
        // TODO Auto-generated method stub
        List<Provider> list = providerMapper.findAllProvider();
        return list;
    }

    @Override
    public Provider findProvider(int id) {
        // TODO Auto-generated method stub
        return providerMapper.findProvider(id);
    }

    @Override
    public boolean proModify(Provider pro) {
        // TODO Auto-generated method stub
        if (providerMapper.proModify(pro) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String proDelete(int id) {
        // TODO Auto-generated method stub
        if (providerMapper.findBill(id) == 0) {
            if (providerMapper.proDelete(id) > 0) {
                return "true";
            } else {
                return "false";
            }
        } else {
            return String.valueOf(providerMapper.findBill(id));
        }
    }

    @Override
    public boolean proAdd(Provider pro) {
        // TODO Auto-generated method stub
        if (providerMapper.proAdd(pro) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Provider> findByCodeOrName(String proCode, String proName) {
        // TODO Auto-generated method stub
        List<Provider> list = providerMapper.findByProCodeOrProName(proCode, proName);
        return list;
    }
}
