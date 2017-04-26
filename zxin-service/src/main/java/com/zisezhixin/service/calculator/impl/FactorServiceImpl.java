package com.zisezhixin.service.calculator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zisezhixin.dao.calculator.FactorMapper;
import com.zisezhixin.model.calculator.Factor;
import com.zisezhixin.service.calculator.FactorService;

@Service
public class FactorServiceImpl implements FactorService {
    @Autowired
    private FactorMapper factorMapper;
    
    @Override
    public List<Factor> getFactorList(String id) {
        
        return factorMapper.getFactorList(id);
    }
}
