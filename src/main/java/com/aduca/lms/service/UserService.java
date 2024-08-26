package com.aduca.lms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.aduca.lms.domain.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Service;

import com.aduca.lms.domain.User;
import com.aduca.lms.domain.dto.RegisterDTO;
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

    public void updateSessionInfo(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (user != null) {
            session.setAttribute("name", user.getName());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("avatar", user.getPhoto());
            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("active", user.isStatus());
            // int sum = user.getCart() == null ? 0 : user.getCart().getSum();
            // session.setAttribute("sum", sum);
        }
    }

    public boolean checkUsernameExist(Long id, String username) {
        User user = repo.findByUsername(username);

        if (user == null)
            return false;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (user != null)
                return true;
        } else {
            if (user.getId() != id) {
                return false;
            }
        }

        return false;
    }

    public boolean checkEmailExist(Long id, String email) {
        User userByEmail = repo.findByEmail(email);

        if (userByEmail == null)
            return false;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (userByEmail != null)
                return true;
        } else {
            if (userByEmail.getId() != id) {
                return true;
            }
        }

        return false;
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        if(registerDTO.getPhone() != null || !registerDTO.getPhone().isEmpty()){
          user.setPhone(registerDTO.getPhone());
        }

      if(registerDTO.getAddress() != null || !registerDTO.getAddress().isEmpty()){
        user.setAddress(registerDTO.getAddress());
      }
        return user;
    }

    public List<User> getAllInstructors() {
       return repo.findByRole(new Role(2L));
    }

  public String getJoinedTimeRelativeToNow(Date createdAt) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(createdAt);

    Calendar now = Calendar.getInstance();

    int years = now.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    int months = now.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
    int days = now.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);

    // Điều chỉnh tháng và năm
    if (months < 0) {
      years--;
      months += 12;
    }

    // Điều chỉnh ngày và tính toán tuần
    if (days < 0) {
      months--;
      calendar.add(Calendar.MONTH, 1);
      days = now.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
    }
    int weeks = days / 7;
    days = days % 7;

    StringBuilder timeDiff = new StringBuilder();
    if (years > 0) {
      timeDiff.append(years).append(" year").append(years > 1 ? "s" : "").append(" ");
    }
    else if (months > 0) {
      timeDiff.append(months).append(" month").append(months > 1 ? "s" : "").append(" ");
    }
    else if (weeks > 0) {
      timeDiff.append(weeks).append(" week").append(weeks > 1 ? "s" : "").append(" ");
    }
    else if (days > 0) {
      timeDiff.append(days).append(" day").append(days > 1 ? "s" : "").append(" ");
    }

    if (timeDiff.length() == 0) {
      return "Just now";
    } else {
      return timeDiff.append("ago").toString().trim();
    }
  }

}
