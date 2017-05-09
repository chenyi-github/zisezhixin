package com.zisezhixin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;

/**
 * @author chenyi
 * @ref http://blog.csdn.net/yangrunkangbla/article/details/50222063
 */
public class MathFormulaUtil {
    
    /**
     * 根据用户输入的数学算式返回表达式对象
     * 
     * @param expressionString
     *            用户输入 数学算式
     * @param params
     *            按照变量的位置为变量输入[对应]的值
     * @return 计算结果
     * @throws Exception
     *             note:在英文输入法的状态下输入
     */
    public static Double getExpressionResult(String expressionString,
            double... params) throws Exception {
        // ( ) + - * / 0-9是数学中的表达式 其余的部分是变量 x y z 等等
        // 注意消除空格
        // 第一步 去除空格
        expressionString.trim().replace(" ", "");
        // 第二步 获取每个字符串的每个字符 后面主要做过滤
        String[] expressArgs = expressionString.split("");
        // 创建过滤器 过滤出来除 ( ) + - * / 数字 0-9 之外的其余字符
        List<String> filters = Arrays.asList("(", ")", "+", "-", "*", "/", "0",
                "1", "2", "3", "4", "5", "6", "7", "8", "9");
        // 过滤出结果 即过滤出用户输入的变量
        List<String> vars = new ArrayList<String>();// 存放过滤结果
        int count = -1;
        String p = "";
        for (int i = 0; i != expressArgs.length; i++) {
            count++; // 计数 从0
            if (!filters.contains(expressArgs[i])) { // 字符不在工具类中
                p = p + expressArgs[i];
                if (!expressArgs[i].equals("")) { // 空格不能加入其中
                    // 处理重复变量的问题
                    if (vars.contains(expressArgs[i])) { // 如果已经过滤的结果中包含了当前循环的编号
                                                         // 追加随机数
                        // 避免让key值不一样，这样不会发生替换
                        String argRandom = expressArgs[i] + getRandom(); // f(x,x12)
                        // 中变了,还得让 =
                        // (x+x2)中对应的变量也变
                        // 处理方法 count
                        // 替换原表达式,即等号后面的部分
                        String oldChar = expressionString.substring(count - 1); // 等号
                                                                                // 替换循环位置后面的部分
                                                                                // 后面原字符
                        // 不能直接替换 这样会都替换掉 我只是替换循环位置后面的部分
                        String repStrLater = oldChar
                                .replace(oldChar, argRandom);
                        // 更新等号后面的表达式 截掉旧的替换新的
                        expressionString = expressionString.substring(0,
                                count - 1)
                                + repStrLater
                                + expressionString.substring(count);
                        vars.add(argRandom);
                        count = count + 32; // 补充随机字符串的剩余长度，，以保证坐标准确
                    } else { // 不包含 正常添加
                        // vars.add(expressArgs[i]);
                    }
                }
            } else {
                // 处理表达式中变量包含多个字母的问题
                if (!p.equals("") && !vars.contains(p)) {
                    vars.add(p);
                }
                p = "";
            }
        }
        // for (String arg : expressArgs) {
        // count++; // 计数 从0
        // if (!filters.contains(arg)) { // 字符不在工具类中
        // if (!arg.equals("")) { // 空格不能加入其中
        // // 处理重复变量的问题
        // if (vars.contains(arg)) { // 如果已经过滤的结果中包含了当前循环的编号 追加随机数
        // // 避免让key值不一样，这样不会发生替换
        // String argRandom = arg + getRandom(); // f(x,x12)
        // // 中变了,还得让 =
        // // (x+x2)中对应的变量也变
        // // 处理方法 count
        // // 替换原表达式,即等号后面的部分
        // String oldChar = expressionString.substring(count - 1); // 等号
        // // 替换循环位置后面的部分
        // // 后面原字符
        // // 不能直接替换 这样会都替换掉 我只是替换循环位置后面的部分
        // String repStrLater = oldChar
        // .replace(oldChar, argRandom);
        // // 更新等号后面的表达式 截掉旧的替换新的
        // expressionString = expressionString.substring(0,
        // count - 1)
        // + repStrLater
        // + expressionString.substring(count);
        // vars.add(argRandom);
        // count = count + 32; // 补充随机字符串的剩余长度，，以保证坐标准确
        // } else { // 不包含 正常添加
        // vars.add(arg);
        // }
        // }
        // }
        // }
        // 构建表达式 "f(x,y,z,..)="
        StringBuilder expressionHead = new StringBuilder("f(");
        for (String v : vars) {
            expressionHead.append(v + ",");
        }
        // 将最后一个 , 去掉
        expressionHead.setLength(expressionHead.length() - 1);
        expressionHead.append(")=");
        // 完整的表达式
        String expressionAll = expressionHead + expressionString;
        // 创建表达式对象
        Expression expression = ExpressionFactory
                .createExpression(expressionAll);
        
        // 为参数赋值放入List中 顺序不能乱
        List<MathematicalElement> mes = new ArrayList<MathematicalElement>();
        
        // 输入的参数 params
        // 构建数学元素
        for (int i = 0; i < params.length; i++) {
            mes.add(NumberFactory.createReal(params[i]));
        }
        
        // 从表达式工厂创建一个空的参数
        Parameters parameters = ExpressionFactory.createParameters();
        // 对应参数赋值
        for (int i = 0; i < vars.size(); i++) {
            // 这边是键值的形式存储的，所以第二个，传过来的第二个x的值,或由于键是一样的，而替换掉第一次键对应的值，及2--->4,键还是那个键
            // x
            // 解决方法 键的区分 ： 过滤用户表达式中的变量时加一个方法 即
            // 与已经过滤出来结果再此比较，如果在出现一样的键,重新命名采用追加随机数的方式
            parameters.addParameter(vars.get(i), mes.get(i));// vars 变量 mes
                                                             // 变量对应的值
        }
        // 返回结果
        return expression.evaluate(parameters).getRealValue();
    }
    
    /**
     * 支持纯数字计算
     * 
     * @param expressionString
     *            用户输入 数学算式
     * @return 计算结果
     * @throws Exception
     *             异常
     */
    public static Double getNumberResult(String expressionString)
            throws Exception {
        Expression expression = ExpressionFactory.createExpression("f()="
                + expressionString);
        return expression.evaluate(null).getRealValue();
    }
    
    /**
     * 生成随机数
     */
    public static String getRandom() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
    }
    
    public static void main(String[] args) {
        String express = "(((x+y)/(z+x)*bb)+100-99)*565";
        // 输入参数
        double[] param = new double[] { 45, 89, 200, 45, 66 };
        Double result = null;
        try {
            result = MathFormulaUtil.getExpressionResult(express, param);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result); // 结果:20960.346938775518
    }
    
}
