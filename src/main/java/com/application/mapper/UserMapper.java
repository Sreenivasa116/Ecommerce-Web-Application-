package com.application.mapper;

import com.application.entities.UserEntity;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Autowired
//    protected PasswordEncoder passwordEncoder;

// User creating request
    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(target = "role",expression = "java(\"CUSTOMER\")")
    @Mapping(source = "email",target = "email")
    @Mapping(target = "createdDate",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    @Mapping(source = "address",target="address")
    UserEntity toUserEntity(UserCreateRequest user);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "role",target = "role")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "createdDate",target = "createdDate")
    @Mapping(source = "updatedAt",target = "updatedAt")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    UserResponse toDtoUserResponse(UserEntity userEntity);
    List<UserResponse> toDtoListResponse (List<UserEntity> userEntities);


    // User Update request
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
    UserEntity toEntityForUpdation
    (UserUpdateRequest dto,@MappingTarget UserEntity user);

    @AfterMapping
    default void updateEntity
            (UserUpdateRequest dto,@MappingTarget UserEntity userEntity/*,
             @Context PasswordEncoder passwordEncoder*/){
        if(dto.getPassword() != null)
            userEntity.setPassword(dto.getPassword());

    }


}
