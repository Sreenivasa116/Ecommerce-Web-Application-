package com.application.services;


import com.application.entities.UserEntity;

import com.application.exception.UserNotFoundException;
import com.application.mapper.UserMapper;
import com.application.repositories.UserRespository;
import com.example.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRespository userRespository;

    public UserResponse createUser( UserCreateRequest UserCreateRequest){
        try {
            UserEntity userEntity = userMapper.toUserEntity(UserCreateRequest);
            userEntity  = userRespository.save(userEntity);
            UserResponse userResponse = userMapper.toDtoUserResponse(userEntity);
           return userResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  UserResponse getUserById(Integer id){
         UserEntity ue = userRespository.findById(id).
                 orElseThrow(() -> {throw  new UserNotFoundException("user not found by id : "+id);});
         UserResponse user = userMapper.toDtoUserResponse(ue);
         return  user;
    }

    public UserResponse getUserByEmail(String email){
        UserEntity userEntity = userRespository.findByEmail(email).orElseThrow(
                ()->  new UserNotFoundException("No user by email id "+email));
        return userMapper.toDtoUserResponse(userEntity);
    }

    public List<UserResponse> getAllUsers(){

        List<UserResponse> list = userMapper.toDtoListResponse(userRespository.findAll());
        if(list == null || list.isEmpty())
            throw new UserNotFoundException("No Users Found In DataBase ");
       return list;
    }

    public UserResponse updateUserByIdService
            (Integer id, UserUpdateRequest dto){
        UserEntity userEntity;
        try{
            userEntity = userRespository.findById(id).
                   orElseThrow(()-> {
                       throw new UserNotFoundException("No user found by user contact Admin for support");});
            userEntity = userMapper.toEntityForUpdation(dto,userEntity);
            userEntity = userRespository.save(userEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return userMapper.toDtoUserResponse(userEntity);
    }

    public String toUpdatePassword(Integer id, ToUpdatePasswordRequest dto){

        UserEntity userEntity = userRespository.findById(id)
                .orElseThrow(()->{throw new UserNotFoundException("No User Found By ID: "+id);});

        if(!userEntity.getPassword().equals(dto.getOldPassword()))
            return "Please enter your old password correctly";
        else if (userEntity.getPassword().equals(dto.getNewPassword()))
            return "New password cannot be same as old password";

        userEntity.setPassword(dto.getNewPassword());
        userEntity.setUpdatedAt(java.time.LocalDateTime.now());
        userRespository.save(userEntity);
        return "User Password Updated Succesfully";

    }

    public String deleteUserById(Integer id){

        if(!userRespository.existsById(id))
            throw new UserNotFoundException("User does not exist by id : "+id);
        else
            userRespository.deleteById(id);
            return "Deleted User By Id: "+id;
    }

}
