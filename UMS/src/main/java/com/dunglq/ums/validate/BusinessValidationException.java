package com.dunglq.ums.validate;

import com.dunglq.ums.exceptions.BaseException;
import lombok.Getter;

import javax.validation.ConstraintDeclarationException;
@Getter
public class BusinessValidationException extends ConstraintDeclarationException {
    private static final long serialVersionUID = -1724495539499702530L;
    private final BaseException exception;

    public BusinessValidationException(BaseException exception) {
        super();
        this.exception = exception;
    }

    public BusinessValidationException(BaseException exception, String message, Object... args) {
        super(String.format(message, args));
        this.exception = exception;
    }
}
