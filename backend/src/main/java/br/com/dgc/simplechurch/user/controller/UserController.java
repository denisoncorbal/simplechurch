package br.com.dgc.simplechurch.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dgc.simplechurch.user.controller.dto.request.LoginRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.response.LoginResponseDto;
import br.com.dgc.simplechurch.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> attemptToLogin(@RequestBody LoginRequestDto loginRequestDto) {
        this.userService.attempToLogin(loginRequestDto);

        return null;
    }

}
