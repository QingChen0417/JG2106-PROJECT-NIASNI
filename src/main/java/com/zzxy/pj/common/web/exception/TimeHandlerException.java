package com.zzxy.pj.common.web.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;

/*@RestControllerAdvice*/
public class TimeHandlerException extends RuntimeException implements Serializable {
    public TimeHandlerException() {
        super();
    }

    public TimeHandlerException(String message) {
        super(message);
    }

    public TimeHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeHandlerException(Throwable cause) {
        super(cause);
    }

    protected TimeHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
