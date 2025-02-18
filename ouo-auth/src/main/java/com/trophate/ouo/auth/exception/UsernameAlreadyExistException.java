package com.trophate.ouo.auth.exception;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResultException;

/**
 * 用户名已经存在
 */
public class UsernameAlreadyExistException extends ResultException {

    public UsernameAlreadyExistException() {
        super(ExceptionEnum.USERNAME_ALREADY_EXIST);
    }
}
