package com.zisezhixin.service.calculator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zisezhixin.dao.calculator.FactorMapper;
import com.zisezhixin.dao.calculator.UserMapper;
import com.zisezhixin.dbdriver.MultipleDataSource;
import com.zisezhixin.model.calculator.Factor;
import com.zisezhixin.model.calculator.User;
import com.zisezhixin.service.calculator.FactorService;

@Service
public class FactorServiceImpl implements FactorService {
    @Autowired
    private FactorMapper factorMapper;
    @Autowired
    private UserMapper   userMapper;
    
    @Override
    public List<Factor> getFactorList(String id) {
        MultipleDataSource.setDataSourceKey("SlaveDataSource");
        return factorMapper.getFactorList(id);
    }
    
    @Override
    public List<User> getClaimUserList(String id) {
        MultipleDataSource.setDataSourceKey("MasterDataSource");
        return userMapper.getUserList();
    }
    
    @Override
    public List<User> getSaleUserList(String id) {
        MultipleDataSource.setDataSourceKey("SlaveDataSource");
        return userMapper.getUserList();
    }
}
