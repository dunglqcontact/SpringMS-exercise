package com.dunglq.ums.annotation;

import com.dunglq.ums.validate.CustomNotBlankValidator;
import org.springframework.http.HttpStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CustomNotBlankValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(CustomNotBlank.List.class)

public @interface CustomNotBlank {
    String message() default "{javax.validation.constraints.NotBlank.message}";
    int statusCode() default 400;
    HttpStatus httpStatus() default HttpStatus.BAD_REQUEST;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        /**
         * Value custom not blank [ ].
         *
         * @return the custom not blank [ ]
         */
        CustomNotBlank[] value();
    }
}
