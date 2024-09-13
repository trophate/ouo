package com.trophate.ouo.framework.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    /**
     * 获取一个logger对象
     *
     * @param obj 当前对象（this）
     */
    public static <T> Logger getLogger(T obj) {
        return LoggerFactory.getLogger(obj.getClass());
    }
}
