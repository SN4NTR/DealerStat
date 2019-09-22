package com.company.validator;

import com.company.model.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");

        List<User> users = userService.getAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                errors.rejectValue("email", "Duplicate.user.email");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required");

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.user.password");
        }
    }
}
