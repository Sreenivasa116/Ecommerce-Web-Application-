package com.application.repositories;

import com.application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "Select * from UserEntity u where u.email = :email and u.password = :password",nativeQuery = true)
    Optional<UserEntity> findByEmailAndPassword(@Param("email") String email,@Param("passwrd") String password);

    @Query(value = "Select * from UserEntity u where u.id = :id and u.password = :password",nativeQuery = true)
    Optional<UserEntity> findByIdAndPassword(@Param("id") Integer email,@Param("passwrd") String password);
}
