package com.aduca.lms.service;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Service;

import com.aduca.lms.domain.User;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    private UserRepository repo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            encodePassword(user);

            User newUser = this.repo.save(user);
            return newUser;
        }

        return this.repo.save(user);

    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

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

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public void changePassword(User theUser, String newPassword) {
        theUser.setPassword(newPassword);
        encodePassword(theUser);
        repo.save(theUser);
    }

    public void updateSessionInfo(HttpServletRequest request, User user){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("avatar", user.getPhoto());
            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());
            // int sum = user.getCart() == null ? 0 : user.getCart().getSum();
            // session.setAttribute("sum", sum);
        }
    }
}
