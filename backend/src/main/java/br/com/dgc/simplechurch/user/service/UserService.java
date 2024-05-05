package br.com.dgc.simplechurch.user.service;

import org.springframework.stereotype.Service;

import br.com.dgc.simplechurch.user.controller.dto.request.LoginRequestDto;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public void attempToLogin(LoginRequestDto loginRequestDto) {

    }

}
