package com.zisezhixin.service.calculator;

import java.util.List;

import com.zisezhixin.model.calculator.Factor;

public interface FactorService {
    /**
     * <h2>索赔人员发送索赔清单至客户</h2>
     *
     * @Title: selectClaimsstatement
     * @param id
     * @return
     */
    public List<Factor> getFactorList(String id);
}
