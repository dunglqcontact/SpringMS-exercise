package com.dunglq.ums.validate;
import com.dunglq.ums.annotation.CustomNotBlank;
import com.dunglq.ums.exceptions.BaseException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomNotBlankValidator implements ConstraintValidator<CustomNotBlank, Object> {
    private String message;
    private Integer statusCode;
    private HttpStatus httpStatus;

    @Override
    public void initialize(CustomNotBlank customNotBlank) {
        message = customNotBlank.message();
        statusCode = customNotBlank.statusCode();
        httpStatus = customNotBlank.httpStatus();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            BaseException exception = new BaseException(statusCode, message, httpStatus);
            throw new BusinessValidationException(exception);
        }
        if (value instanceof String) {
            String string = (String) value;
            if (Strings.isBlank(string)) {
                BaseException exception = new BaseException(statusCode, message, httpStatus);
                throw new BusinessValidationException(exception);
            }
        }
        return true;
    }
}
