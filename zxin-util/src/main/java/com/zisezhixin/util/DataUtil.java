package com.zisezhixin.util;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zisezhixin.model.Result;

/**
 * 数据处理
 */
public class DataUtil {

    private static Logger logger = LogManager.getLogger(DataUtil.class);

    /**
     * <h2>处理异常反回结果</h2>
     *
     * @Title: handleResultMap
     * @Description: 处理异常反回结果
     * @param @param resultMap
     * @param @param exceptionEnum
     * @return void 返回类型
     */

    /**
     * 验证码相关提示信息
     *
     * @param resultMap
     * @param dynamicCodeTips
     */
    public static void handleResultMap(Map<String, Object> resultMap, Result result) {
        resultMap.put("resultSuccess", result.isSuccess());
        resultMap.put("resultCode", result.getCode());
        resultMap.put("resultDesc", result.getMsg());
    }

    public static void handleResultMap(Map<String, Object> resultMap, Result result, String para1) {
        resultMap.put("resultSuccess", result.isSuccess());
        resultMap.put("resultCode", result.getCode());
        resultMap.put("resultDesc", String.format(result.getMsg(), para1));
    }

    public static void handleResultMap(Map<String, Object> resultMap, Result result, Exception e) {
        resultMap.put("resultSuccess", result.isSuccess());
        resultMap.put("resultCode", result.getCode());
        resultMap.put("resultDesc", result.getMsg());
        resultMap.put("errorMessage", e.getMessage());
    }

    public static void handleResultMap(Map<String, Object> resultMap, Result result, String para1, String para2) {
        resultMap.put("resultSuccess", result.isSuccess());
        resultMap.put("resultCode", result.getCode());
        resultMap.put("resultDesc", String.format(result.getMsg(), para1, para2));
    }

    public static void handleSuccess(Map<String, Object> resultMap, Result result) {
        resultMap.put("resultSuccess", true);
        resultMap.put("resultCode", result.getCode());
        resultMap.put("resultDesc", result.getMsg());
    }

    public static void handleFail(Map<String, Object> resultMap, Result result) {
        resultMap.put("resultSuccess", false);
        resultMap.put("resultCode", result.getCode());
        resultMap.put("resultDesc", result.getMsg());
    }
    public static void handleFail(Map<String, Object> resultMap, Exception result) {
        resultMap.put("resultSuccess", false);
        resultMap.put("resultDesc", result.getMessage());
    }

    /**
     * 处理理赔计算赔款
     *
     * @param map 计算参数
     * @param resultMap 返回结果
     * @param resultSuccess 成功的Result
     * @param resultFail 失败的Result
     */
    public static void handleReparation(Map<String, Object> map, Map<String, Object> resultMap, Result resultSuccess, Result resultFail) {
        if (map.get("reparation") == null) {
            resultMap.put("reparation", 0);
            resultMap.put("resultSuccess", resultFail.isSuccess());
            resultMap.put("resultCode", resultFail.getCode());
            resultMap.put("resultDesc", resultFail.getMsg());
            logger.info(resultFail);
        } else {
            BigDecimal reparation = new BigDecimal(map.get("reparation").toString());
            resultMap.put("reparation", reparation);
            resultMap.put("resultSuccess", resultSuccess.isSuccess());
            resultMap.put("resultCode", resultSuccess.getCode());
            resultMap.put("resultDesc", resultSuccess.getMsg());
            logger.info(resultSuccess);
        }
    }

}
