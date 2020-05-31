package com.todo.user.service;

import com.todo.utils.Utils;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.function.SerializableFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEmailValidation implements Validator<String>{
    private static final Logger logger= LogManager.getLogger(UserEmailValidation.class);

    private final UserSerive userService;

    private String errMsg="";
    private static final String regexp = "^([a-zA-Z0-9_\\.\\-+])+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9-]{2,}$";
    private static final Pattern pattern=Pattern.compile(regexp);
    private transient Matcher matcher;
    private final String oldEmail;

    private SerializableFunction<String, String> messageProvider;
    private boolean isValid;
    private ValidationResult validationResult;

    public UserEmailValidation(UserSerive userSerive){
        this.oldEmail=null;
        this.matcher=null;
        this.userService=userSerive;
    }

    public UserEmailValidation(UserSerive userSerive, String currentValue){
        this.oldEmail=currentValue;
        this.matcher=null;
        this.userService=userSerive;
    }

    @Override
    public ValidationResult apply(String email, ValueContext context) {
        boolean validity= isValid(email);
        return this.toResult(email, validity);
    }

    private ValidationResult toResult(String errMsg, boolean isValid) {
        return isValid ? ValidationResult.ok() : ValidationResult.error(errMsg);
    }

    private boolean isValid(String newEmail){
        return isValidEmail(newEmail) && isUnique(newEmail);
    }

    private boolean isUnique(String newEmail){
        if(!userService.isUniqueEmail(newEmail, oldEmail)){
            errMsg="already used, try with another one";
            return false;
        }else return true;
    }

    private boolean isValidEmail(String email) {
        if (Utils.isEmpty(email)) {
            return true;
        } else {
            if (getMatcher(email).matches()){
                return true;
            }else {
                errMsg="Invalid email address";
                return false;
            }
        }
    }

    private Matcher getMatcher(String value) {
        if (this.matcher == null) {
            this.matcher = pattern.matcher(value);
        } else {
            this.matcher.reset(value);
        }

        return this.matcher;
    }
}
