package com.juniorjavadeveloper.restapidemo.validation;

import com.juniorjavadeveloper.restapidemo.validation.validator.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Size(min = 3, max = 255, message = "Username length 3-255 symbols.")
@Constraint(validatedBy = UserNameValidator.class)
public @interface UserName {
    String message() default "Username can't contain any spaces.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
