package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

import com.appsdeveloperblog.photoapp.api.users.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.shared.ParameterMap;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserResponseModel;
import com.appsdeveloperblog.photoapp.api.users.validators.UserValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	Environment environment;
	@Autowired
	UsersService usersService;

	@Autowired
	private final UserValidator userValidator;

	public UsersController(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + environment.getProperty("local.server.port");
	}
	@GetMapping("/sayhi")
	public String hi() {
		return "Hiya, working on port" + environment.getProperty("local.server.port");
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody CreateUserRequestModel userDetails) {

		ParameterMap ret = userValidator.validate(userDetails.toParameterMap());
		if(ret!=null) {
			return ResponseEntity.badRequest().body(ret.getErrors());
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = usersService.createUser(userDto);

		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

}
