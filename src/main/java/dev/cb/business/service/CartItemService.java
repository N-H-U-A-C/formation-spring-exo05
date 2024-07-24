package dev.cb.business.service;

import dev.cb.business.domain.CartItem;
import dev.cb.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getById(UUID id) {
        return cartItemRepository.findById(id);
    }

    public CartItem create(CartItem cartItem) {
        cartItem.setId(UUID.randomUUID());
        return cartItemRepository.save(cartItem);
    }

    public CartItem update(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteById(UUID id) {
        cartItemRepository.deleteById(id);
    }
}
