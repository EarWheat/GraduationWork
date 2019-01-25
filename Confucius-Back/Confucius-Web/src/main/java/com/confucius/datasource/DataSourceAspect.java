package com.confucius.datasource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;

/**
 * 定义数据源的AOP切面，通过该Service的方法名判断是应该走读库还是写库
 *
 * @author yinjianbo
 * @version create_time：2017年5月16日 下午3:24:54
 */
public class DataSourceAspect {

    /**
     * 在进入Service方法之前执行
     *
     * @param point 切面对象
     */
    public void before(JoinPoint point) {
        // 获取到当前执行的方法名
        String methodName = point.getSignature().getName();
        if (isSlave(methodName)) {
            DynamicDataSourceHolder.outsideData();
        } else if (isPunishDriver(methodName)) {
            DynamicDataSourceHolder.slave02();
        } else if (isRecallQiedan(methodName)) {
            DynamicDataSourceHolder.slave03();
        } else if (isGpassenger(methodName)) {
            DynamicDataSourceHolder.slave04();
        } else if (isZhenshen(methodName)) {
            DynamicDataSourceHolder.slave05();
        } else if (isQiedanCheat(methodName)) { //
            DynamicDataSourceHolder.slave06();
        } else if (isPassengerFee(methodName)){
            DynamicDataSourceHolder.slave07();
        } else {
            DynamicDataSourceHolder.markMaster();
        }
    }

    /**
     * 判断读库
     *
     * @param methodName
     * @return
     */
    private Boolean isSlave(String methodName) {

        return StringUtils.startsWithAny(methodName, "spamQuery", "spamSave");
    }

    private Boolean isPunishDriver(String methodName) {
        return StringUtils.startsWithAny(methodName, "delSilence", "updateSuspectSpam");
    }


    private Boolean isRecallQiedan(String methodName) {
        return StringUtils.startsWithAny(methodName, "delQiedan");
    }


    private Boolean isGpassenger(String methodName) {
        return StringUtils.startsWithAny(methodName, "getGpassenger");
    }


    private Boolean isZhenshen(String methodName) {
        return StringUtils.startsWithAny(methodName, "getIdentity");
    }

    private Boolean isQiedanCheat(String methodName) {
        return StringUtils.startsWithAny(methodName, "qiedanQuery");
    }


    private Boolean isPassengerFee(String methodName) {
        return StringUtils.startsWithAny(methodName,"passengerFee");
    }
}
