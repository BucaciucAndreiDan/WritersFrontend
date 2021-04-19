package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private static final int PAGE_SIZE = 10;

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(int page) {
        Pageable limit = PageRequest.of(page, PAGE_SIZE);
        return userRepository.findAll(limit).getContent();
    }

    public User findUserByCnp(long cnp) {
        return userRepository.findUserByCnp(cnp);
    }

    public List<User> findUsers(Long cnp, String name, int page) {
        if (cnp == null)
            cnp = -1L;
        if (name == null)
            name = "";
        Pageable limit = PageRequest.of(page, PAGE_SIZE);
        return userRepository.findUserByCnpOrFirstNameContainingOrLastNameContaining(cnp, name, name, limit).getContent();
    }
}
