package com.trophate.ouo.framework.exception;

import com.trophate.ouo.framework.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionController {

    @ExceptionHandler(ResException.class)
    public Result exceptionHandler(ResException e) {
        return Result.fail(e);
    }
}
