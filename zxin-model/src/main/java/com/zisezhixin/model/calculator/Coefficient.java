package com.zisezhixin.model.calculator;

import java.math.BigDecimal;

public class Coefficient {
    private Long id;

    private Long adjustmentFactorId;

    private String factorLevel;

    private String factor1;

    private String factor2;

    private String factor3;

    private String factor4;

    private String factor5;

    private BigDecimal rangeStart;

    private BigDecimal rangeEnd;

    private BigDecimal coefficient;

    private BigDecimal coefficientMin;

    private BigDecimal coefficientMax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdjustmentFactorId() {
        return adjustmentFactorId;
    }

    public void setAdjustmentFactorId(Long adjustmentFactorId) {
        this.adjustmentFactorId = adjustmentFactorId;
    }

    public String getFactorLevel() {
        return factorLevel;
    }

    public void setFactorLevel(String factorLevel) {
        this.factorLevel = factorLevel == null ? null : factorLevel.trim();
    }

    public String getFactor1() {
        return factor1;
    }

    public void setFactor1(String factor1) {
        this.factor1 = factor1 == null ? null : factor1.trim();
    }

    public String getFactor2() {
        return factor2;
    }

    public void setFactor2(String factor2) {
        this.factor2 = factor2 == null ? null : factor2.trim();
    }

    public String getFactor3() {
        return factor3;
    }

    public void setFactor3(String factor3) {
        this.factor3 = factor3 == null ? null : factor3.trim();
    }

    public String getFactor4() {
        return factor4;
    }

    public void setFactor4(String factor4) {
        this.factor4 = factor4 == null ? null : factor4.trim();
    }

    public String getFactor5() {
        return factor5;
    }

    public void setFactor5(String factor5) {
        this.factor5 = factor5 == null ? null : factor5.trim();
    }

    public BigDecimal getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(BigDecimal rangeStart) {
        this.rangeStart = rangeStart;
    }

    public BigDecimal getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(BigDecimal rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getCoefficientMin() {
        return coefficientMin;
    }

    public void setCoefficientMin(BigDecimal coefficientMin) {
        this.coefficientMin = coefficientMin;
    }

    public BigDecimal getCoefficientMax() {
        return coefficientMax;
    }

    public void setCoefficientMax(BigDecimal coefficientMax) {
        this.coefficientMax = coefficientMax;
    }
}