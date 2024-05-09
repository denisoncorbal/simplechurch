package br.com.dgc.simplechurch.role.service;

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

}
