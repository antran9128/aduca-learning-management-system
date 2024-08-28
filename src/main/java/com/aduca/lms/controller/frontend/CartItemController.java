package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.dto.CartDTO;
import com.aduca.lms.service.CartItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartItemController {
  private CartItemService cartItemService;

  public CartItemController(CartItemService cartItemService) {
    this.cartItemService = cartItemService;
  }

  @GetMapping("/mycart")
  public String getMyCart(){
    return "client/cart/view_mycart";
  }

  @GetMapping("/checkout")
  public String checkoutCreate(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
    Long userId = (Long) session.getAttribute("id");
    if (userId != null) {  // Check if user is authenticated
      CartDTO cart = cartItemService.getCartDTO(request);
      double cartTotal = cart.getTotalPrice();
      int cartQty = cart.getQuantity();

      if (cartTotal > 0) {
        model.addAttribute("carts", cart.getCourseDTOs());
        model.addAttribute("cartTotal", cartTotal);
        model.addAttribute("cartQty", cartQty);
        return "client/checkout/checkout_view";  // Return the checkout view
      } else {
        redirectAttributes.addFlashAttribute("message", "Add at least one course");
        redirectAttributes.addFlashAttribute("alertType", "error");
        return "redirect:/homepage";  // Redirect to homepage
      }
    } else {
      redirectAttributes.addFlashAttribute("message", "You need to log in first");
      redirectAttributes.addFlashAttribute("alertType", "error");
      return "redirect:/login";  // Redirect to login page
    }
  }
}
