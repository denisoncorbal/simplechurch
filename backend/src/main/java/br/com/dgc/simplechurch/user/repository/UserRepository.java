package br.com.dgc.simplechurch.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dgc.simplechurch.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.roles WHERE u.email = ?1")
    Optional<User> loadUserDetails(String email);
}
