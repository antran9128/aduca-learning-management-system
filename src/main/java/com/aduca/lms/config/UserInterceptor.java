package com.aduca.lms.config;

import com.aduca.lms.domain.User;
import com.aduca.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

  @Autowired
  private UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession(false);

    if (session != null) {
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
        // Tải lại thông tin người dùng từ cơ sở dữ liệu
        User user = userService.getUserById(id);
        session.setAttribute("name", user.getName());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("avatar", user.getPhoto());
        session.setAttribute("id", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("active", user.isStatus());
        session.setAttribute("role", user.getRole().getName());
      }
    }
    return true;
  }
}
