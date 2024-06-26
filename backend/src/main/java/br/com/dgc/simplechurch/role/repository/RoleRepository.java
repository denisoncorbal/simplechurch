package br.com.dgc.simplechurch.role.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dgc.simplechurch.role.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    public Optional<Role> findByName(String name);
}
