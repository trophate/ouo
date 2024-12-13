package com.trophate.ouo.framework.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    /**
     * 获取一个logger对象。
     *
     * @param obj 目标对象，如果是当前对象就传this。
     * @return Logger
     */
    public static <T> Logger getLogger(T obj) {
        return LoggerFactory.getLogger(obj.getClass());
    }
}
