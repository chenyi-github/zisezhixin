package com.zisezhixin.dao.calculator;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zisezhixin.model.calculator.User;
import com.zisezhixin.model.calculator.UserExample;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int countByExample(UserExample example);
    
    List<User> getUserList();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int deleteByExample(UserExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int insert(User record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int insertSelective(User record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    List<User> selectByExample(UserExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(String id);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") User record,
            @Param("example") UserExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") User record,
            @Param("example") UserExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);
}