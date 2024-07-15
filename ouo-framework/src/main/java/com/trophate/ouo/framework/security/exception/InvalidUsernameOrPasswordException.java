package com.trophate.ouo.framework.security.exception;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResException;

/**
 * 无效的账号密码
 */
public class InvalidUsernameOrPasswordException extends ResException {

    public InvalidUsernameOrPasswordException() {
        super(ExceptionEnum.INVALID_USERNAME_OR_PASSWORD);
    }
}
