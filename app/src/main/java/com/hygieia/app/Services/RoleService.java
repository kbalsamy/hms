package com.hygieia.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.RoleDto;
import com.hygieia.app.Models.Role;
import com.hygieia.app.Repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public Role saveRole(RoleDto roleDto) {

        if (roleRepo.existsByRoleName(roleDto.getRoleName())) {
            return null;
        }

        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        // user.setUserEmail(userregDto.getUserEmail());

        role.setPermissions(roleDto.getPermissions());

        Role newRole = roleRepo.save(role);
        return newRole;
    }

    public void updateRole(Role roleDto) {

        roleRepo.save(roleDto);

    }

    public List<Role> getAllRoles() {

        List<Role> roles = roleRepo.findAll();

        return roles;

    }

    public Optional<Role> findRoleById(int id) {

        return roleRepo.findById(id);

    }

    public Optional<Role> findRoleByRoleName(String name) {

        return roleRepo.findByRoleName(name);

    }

}
