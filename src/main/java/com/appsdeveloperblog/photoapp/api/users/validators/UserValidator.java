package com.appsdeveloperblog.photoapp.api.users.validators;

import com.appsdeveloperblog.photoapp.api.users.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.data.UsersRepository;
import com.appsdeveloperblog.photoapp.api.users.shared.ParameterMap;
import com.appsdeveloperblog.photoapp.api.users.shared.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends Validator<UserEntity> {

    public UserValidator() {

    }

    @Override
    protected UserEntity doValidate(ParameterMap body) {

        UserEntity user = new UserEntity();

        String id = body.validate("id").getValue(); id = id==null?"0":id;
        String firstName = body.validate("name").getValue(); firstName = firstName==null?"":firstName;
        String lastName = body.validate("description").getValue(); lastName = lastName==null?"":lastName;
        String email = body.validate("catalogId").getValue(); email = email==null?"":email;
        String password = body.validate("channel").getValue(); password = password==null?"0":password;

        user.setId(Integer.parseInt(id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setEncryptedPassword(password);
        return user;

    }
}