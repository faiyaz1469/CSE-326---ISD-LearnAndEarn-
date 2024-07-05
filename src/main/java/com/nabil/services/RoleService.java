package com.nabil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Role;
import com.nabil.repositories.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRole(String name) {
        return roleRepository.findById(name).get();
    }
}
