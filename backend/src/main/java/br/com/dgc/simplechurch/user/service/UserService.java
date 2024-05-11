package br.com.dgc.simplechurch.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dgc.simplechurch.role.repository.RoleRepository;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public Optional<User> readUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public boolean checkPassword(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    public User addRoleToUser(UUID userId, UUID roleId) {
        User user = this.userRepository.findById(userId).get();
        user.addRole(this.roleRepository.findById(roleId).get());
        return this.userRepository.save(user);
    }

}
