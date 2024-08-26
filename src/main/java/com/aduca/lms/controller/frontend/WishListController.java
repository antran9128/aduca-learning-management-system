package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.User;
import com.aduca.lms.domain.WishList;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.exception.WishListNotFoundException;
import com.aduca.lms.service.UserService;
import com.aduca.lms.service.WishListService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@Controller
public class WishListController {
  private WishListService wishListService;
  private UserService userService;
  public WishListController(WishListService wishListService, UserService userService) {
    this.wishListService = wishListService;
    this.userService = userService;
  }

  @PostMapping("/wishlist/add")
  @ResponseBody
  public HashMap<String, String> addToWishList(@RequestParam("course_id") Long id, @RequestParam("user_id") Long userId) throws UserNotFoundException {
    HashMap<String, String> response = new HashMap<>();
    if(userId != null){
      try {
        WishList exist = wishListService.findByCourse_IdAndUser_Id(id, userId);
        if(exist != null){
          response.put("error", "This course is already in your Wishlist");
        }else{
          WishList newWL = new WishList();
          newWL.setCourse(new Course(id));
          newWL.setUser(userService.getUserById(userId));
          newWL.setCreatedAt(new Date());
          wishListService.save(newWL);
          response.put("success", "Successfully added to your Wishlist");
        }
      } catch (WishListNotFoundException e) {
        WishList newWL = new WishList();
        newWL.setCourse(new Course(id));
        newWL.setUser(userService.getUserById(userId));
        newWL.setCreatedAt(new Date());
        wishListService.save(newWL);
        response.put("success", "Successfully added to your Wishlist");
      }

    }else{
      response.put("error", "Please log in first");
    }
    return response;
  }
}
