package com.zisezhixin.service.calculator;

import java.util.List;

import com.zisezhixin.model.calculator.Factor;
import com.zisezhixin.model.calculator.User;

public interface FactorService {
    /**
     * <h2>索赔人员发送索赔清单至客户</h2>
     *
     * @Title: selectClaimsstatement
     * @param id
     * @return
     */
    public List<Factor> getFactorList(String id);
    
    List<User> getClaimUserList(String id);
    
    List<User> getSaleUserList(String id);
}
