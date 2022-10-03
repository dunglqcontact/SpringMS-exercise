package com.dunglq.ums.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends Exception{
    private static final long serialVersionUID = 6959388629961794442L;
    protected final String message;
    protected final Integer statusCode;
    protected final HttpStatus httpStatus;
    protected final Object[] args = null;
    public BaseException(Integer statusCode, String message, HttpStatus httpStatus) {
        this.message = message;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
    }
}
