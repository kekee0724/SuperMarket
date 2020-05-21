package org.kekee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.kekee.service.BillService;
import org.kekee.mapper.BillMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.kekee.entity.Bill;

/**
 * @author cocoa
 */
@Service
@Transactional
public class BillServiceImpl implements BillService {
    @Resource
    BillMapper billMapper;

    @Override
    public List<Bill> findAddBill() {
        // TODO Auto-generated method stub

        return billMapper.findAllBill();
    }

    @Override
    public Bill findBillById(int id) {
        // TODO Auto-generated method stub
        return billMapper.findBillById(id);
    }

    @Override
    public boolean billAdd(Bill b) {
        // TODO Auto-generated method stub
        if (billMapper.billAdd(b) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean billModify(Bill b) {
        // TODO Auto-generated method stub
        if (billMapper.billModify(b) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String billDel(int id) {
        // TODO Auto-generated method stub
        if (billMapper.findBillById(id) != null) {
            if (billMapper.billDel(id) > 0) {
                return "true";
            } else {
                return "false";
            }
        }
        return "notexist";
    }

    @Override
    public List<Bill> findByNameIdAndIsPayment(String productName,
                                               Integer providerId, Integer isPayment) {
        // TODO Auto-generated method stub
        return billMapper.findByNameIdAndIsPayment(productName, providerId, isPayment);
    }

}
