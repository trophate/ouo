package com.trophate.ouo.framework.exception;

import com.trophate.ouo.framework.commons.utils.LogUtils;
import com.trophate.ouo.framework.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionController {

    @ExceptionHandler(ResultException.class)
    public Result exceptionHandler(ResultException e) {
        LogUtils.getLogger(this).debug(e.getMessage());
        return Result.fail(e);
    }
}
