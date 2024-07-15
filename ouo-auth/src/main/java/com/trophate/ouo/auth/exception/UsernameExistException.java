package com.trophate.ouo.auth.exception;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResException;

/**
 * 用户名已经存在
 */
public class UsernameExistException extends ResException {

    public UsernameExistException() {
        super(ExceptionEnum.USERNAME_EXIST);
    }
}
