package br.com.dgc.simplechurch.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dgc.simplechurch.church.model.Church;
import br.com.dgc.simplechurch.church.repository.ChurchRepository;
import br.com.dgc.simplechurch.role.model.Role;
import br.com.dgc.simplechurch.role.repository.RoleRepository;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private ChurchRepository churchRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
            ChurchRepository churchRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.churchRepository = churchRepository;
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

    @Transactional
    public User addRoleToUser(UUID userId, UUID roleId) {
        User user = this.userRepository.findById(userId).get();
        Role role = this.roleRepository.findById(roleId).get();
        user.addRole(role);
        return this.userRepository.save(user);
    }

    @Transactional
    public User addChurchToUser(UUID userId, UUID churchId) {
        User user = this.userRepository.findById(userId).get();
        Church church = this.churchRepository.findById(churchId).get();
        user.setChurch(church);
        return this.userRepository.save(user);
    }

}
