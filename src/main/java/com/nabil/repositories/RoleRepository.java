package com.nabil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabil.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}
