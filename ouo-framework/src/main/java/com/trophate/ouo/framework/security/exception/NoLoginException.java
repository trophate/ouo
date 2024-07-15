package com.trophate.ouo.framework.security.exception;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResException;

/**
 * 未登录
 */
public class NoLoginException extends ResException {

    public NoLoginException() {
        super(ExceptionEnum.NO_LOGIN);
    }
}
