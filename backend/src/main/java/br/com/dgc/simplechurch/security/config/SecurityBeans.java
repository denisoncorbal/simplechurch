package br.com.dgc.simplechurch.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.dgc.simplechurch.user.exception.UsernameNotFoundException;
import br.com.dgc.simplechurch.user.repository.UserRepository;

@Configuration
public class SecurityBeans {
    private UserRepository userRepository;

    public SecurityBeans(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            try {
                return userRepository.loadUserDetails(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            } catch (UsernameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        };
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 2, 66536, 2);
    }
}
