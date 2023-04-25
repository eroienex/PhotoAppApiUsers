package com.appsdeveloperblog.photoapp.api.users.validators;

import com.appsdeveloperblog.photoapp.api.users.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.data.UsersRepository;
import com.appsdeveloperblog.photoapp.api.users.shared.ParameterMap;
import com.appsdeveloperblog.photoapp.api.users.shared.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends Validator<ParameterMap> {

    public UserValidator() {

    }

    @Override
    protected ParameterMap doValidate(ParameterMap body) {

        UserEntity user = new UserEntity();

        String id = body.validate("id").getValue(); id = id==null?"0":id;
        String firstName =  body
                .validate("firstName")
                .required()
                .condition(value -> value==null, "firstName cannot be null")
                .condition(value -> value.length()<2, "firstName must be at least 2 characters")
                .minLength(2)
                .getValue();

        String lastName =  body
                .validate("lastName")
                .required()
                .condition(value -> value==null, "lastName cannot be null")
                .condition(value -> value.length()<2, "lastName must be at least 2 characters")
                .minLength(2)
                .getValue();

        String password =  body
                .validate("password")
                .required()
                .condition(value -> value==null, "password cannot be null")
                .minLength(8,"must be at least 8 characters")
                .maxLength(16,"maximum is 16 characters")
                .getValue();

        String email =  body
                .validate("email")
                .required()
                .condition(value -> value==null, "email cannot be null")
                .isEmail("invalid email format")
                .getValue();

        /**user.setId(Integer.parseInt(id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setEncryptedPassword(password);

        return user;*/
        if (body.hasErrors()) {
            return body;
        }else {
            return null;
        }

    }
}