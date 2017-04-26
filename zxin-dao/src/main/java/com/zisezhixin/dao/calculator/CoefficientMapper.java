package com.zisezhixin.dao.calculator;

import com.zisezhixin.model.calculator.Coefficient;

public interface CoefficientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coefficient record);

    int insertSelective(Coefficient record);

    Coefficient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Coefficient record);

    int updateByPrimaryKey(Coefficient record);
}