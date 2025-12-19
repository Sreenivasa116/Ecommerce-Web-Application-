package com.application.controllers;


import com.application.exception.UserNotFoundException;
import com.application.services.UserService;


import com.example.api.UserApi;
import com.example.model.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;


import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserController implements UserApi {

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<UserResponse> createUser
            (@Valid @RequestBody UserCreateRequest userCreateRequest) {
        UserResponse response = userService.createUser(userCreateRequest);
        if(response != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById (Integer id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @Override
    public ResponseEntity<UserResponse> getUserByEmail(String email) {

        UserResponse user = userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> list = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @Override
    public ResponseEntity<UserResponse> updateUserById
            (Integer id, UserUpdateRequest userUpdateRequest){

        UserResponse response = userService.updateUserByIdService(id,userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<String> toUpdatePassword(Integer id, ToUpdatePasswordRequest toUpdatePasswordRequest) {

       String res = userService.toUpdatePassword(id,toUpdatePasswordRequest);
       if(res != null && res != "" && res.contains("User Password Updated Succesfully"))
           return ResponseEntity.status(HttpStatus.OK).body(res);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Integer id) {
        String res = userService.deleteUserById(id);
        if (res.contains("Deleted User By Id") && res != null && res != "") {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
