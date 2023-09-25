package com.springsecurity.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.springsecurity.models.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>  {


    Optional<UserEntity> findByUsername(String username);

    // @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> getName(@Param("username")String username);
}
