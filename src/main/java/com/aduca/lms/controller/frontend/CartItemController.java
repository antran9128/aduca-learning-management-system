package com.aduca.lms.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartItemController {

  @GetMapping("/mycart")
  public String getMyCart(){
    return "client/cart/view_mycart";
  }
}
