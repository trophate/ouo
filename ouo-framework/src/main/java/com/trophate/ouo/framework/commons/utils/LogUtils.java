package com.trophate.ouo.framework.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    /**
     * 该方法获取一个Logger对象。
     *
     * @param obj 目标对象：该对象通常来说是当前对象（this）。
     * @return Logger
     */
    public static <T> Logger getLogger(T obj) {
        return LoggerFactory.getLogger(obj.getClass());
    }
}
