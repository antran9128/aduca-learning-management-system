package com.aduca.lms.service;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aduca.lms.domain.User;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.repository.UserRepository;


@Service
public class UserService {

    private UserRepository repo;
    //private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo) {
        this.repo = repo;

    }

    public User saveUser(User user) {
//        if (user.getId() == null) {
//            encodePassword(user);
//
//            User newUser = this.repo.save(user);
//            return newUser;
//        }

        return this.repo.save(user);

    }

//    private void encodePassword(User user) {
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//    }

    public User getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
    }
}
