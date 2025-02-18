package com.trophate.ouo.framework.security.exceptions;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResultException;

/**
 * 无效的账号或密码
 */
public class InvalidUsernameOrPasswordException extends ResultException {

    public InvalidUsernameOrPasswordException() {
        super(ExceptionEnum.INVALID_USERNAME_OR_PASSWORD);
    }
}
