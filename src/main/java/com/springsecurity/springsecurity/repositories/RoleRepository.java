package com.springsecurity.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.springsecurity.models.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    
}
