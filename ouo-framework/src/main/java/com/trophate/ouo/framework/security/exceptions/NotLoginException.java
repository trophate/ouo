package com.trophate.ouo.framework.security.exceptions;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResultException;

/**
 * 未登录
 */
public class NotLoginException extends ResultException {

    public NotLoginException() {
        super(ExceptionEnum.NOT_LOGIN);
    }
}
