package com.trophate.ouo.auth.exception;

import com.trophate.ouo.framework.exception.ExceptionEnum;
import com.trophate.ouo.framework.exception.ResultException;

public class UsernameExistException extends ResultException {

    public UsernameExistException() {
        super(ExceptionEnum.USERNAME_EXIST);
    }
}
