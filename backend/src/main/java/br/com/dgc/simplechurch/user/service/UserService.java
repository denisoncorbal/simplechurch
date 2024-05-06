package br.com.dgc.simplechurch.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dgc.simplechurch.user.controller.dto.request.LoginRequestDto;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public boolean checkPassword(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    public void attempToLogin(LoginRequestDto loginRequestDto) {

    }

}
