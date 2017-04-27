package com.zisezhixin.dao.calculator;

import java.util.List;

import com.zisezhixin.model.calculator.Factor;
import com.zisezhixin.model.calculator.User;

public interface FactorMapper {
    
    List<Factor> getFactorList(String id);
    
    List<User> getUserList(String id);
    
}
