package dev.cb.controller;

import dev.cb.business.service.CartItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("cartItems", cartItemService.getAll());
        return "/cartitems/list";
    }

    @GetMapping("/addtocart/{id}")
    public String createOrUpdate(@PathVariable(name = "id") UUID furnitureId) {
        cartItemService.createOrUpdate(furnitureId);
        return "redirect:/furnitures";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        cartItemService.deleteById(id);
        return "redirect:/cartitems";
    }

    @GetMapping("/delete")
    public String delete() {
        cartItemService.deleteAll();
        return "redirect:/furnitures";
    }
}
