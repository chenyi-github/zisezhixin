package com.zisezhixin.dao.calculator;

import java.util.List;

import com.zisezhixin.model.calculator.Factor;

public interface FactorMapper {
    
    List<Factor> getFactorList(String id);
    
}
