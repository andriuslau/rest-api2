package com.juniorjavadeveloper.restapidemo.validation.validator;

import com.juniorjavadeveloper.restapidemo.validation.UserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !value.contains(" ");
    }
}
