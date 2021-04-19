package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByCnp(long cnp);

    Page<User> findUserByCnpOrFirstNameContainingOrLastNameContaining(Long cnp, String firstName, String lastName, Pageable limit);
}
