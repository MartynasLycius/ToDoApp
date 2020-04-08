package com.murad.todoapp.formvalidator;

import com.murad.todoapp.domain.User;
import com.murad.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Component
public class MyUserFormValidator implements Validator {

    @Autowired
    UserService userService;

    private static String editEmail="";

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.countByEmail(user.getEmail()) != 0 && !editEmail.equals(user.getEmail())) {
            errors.rejectValue("email","user.user.exist");
        }

    }

    public static void setEditEmail(String editEmail) {
        MyUserFormValidator.editEmail = editEmail;
    }
}
