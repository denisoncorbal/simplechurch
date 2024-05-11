package br.com.dgc.simplechurch.role.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.dgc.simplechurch.role.model.Role;
import br.com.dgc.simplechurch.role.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return this.roleRepository.save(role);
    }

    public Optional<Role> readRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public List<Role> readAllRoles() {
        return this.roleRepository.findAll();
    }

}
