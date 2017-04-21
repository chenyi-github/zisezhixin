package com.zisezhixin.dialect;

public interface Dialect {
    /**
     * 
     * @return
     */
    public boolean supportsLimit();

    /**
     * 
     * @return
     */
    public boolean supportOffsetLimit();

    /**
     * 
     * @param sql 
     * @param offset 
     * @param maxRow 
     * @return
     */
    public String getLimitString(String sql, int offset, int maxRow);
}
