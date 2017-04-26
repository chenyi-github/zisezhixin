package com.zisezhixin.model.calculator;

import lombok.Getter;
import lombok.Setter;

import com.zisezhixin.enums.calculator.FactorType;

@Getter
@Setter
public class Factor {
    private String     id;
    private String     insurance_products_id;
    private String     adjustment_type;
    private String     item;
    private String     sort;
    private String     content;
    private String     description;
    private FactorType factorType;
    private String     range_type;
    private String     need_calculate;
    private String     depend_on_factor_id;
    private String     depend_on_coefficient_id;
    
}
