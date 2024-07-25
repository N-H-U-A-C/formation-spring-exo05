package dev.cb.business.service;

import dev.cb.business.domain.CartItem;
import dev.cb.business.domain.Furniture;
import dev.cb.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final FurnitureService furnitureService;

    public CartItemService(CartItemRepository cartItemRepository, FurnitureService furnitureService) {
        this.cartItemRepository = cartItemRepository;
        this.furnitureService = furnitureService;
    }

    // CRUD
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getById(UUID id) {
        return cartItemRepository.findById(id);
    }

    public boolean createOrUpdate(UUID furnitureId) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findByFurnitureId(furnitureId);
        if (optionalCartItem.isPresent()) {
            update(optionalCartItem.get(), 1);
            return true;
        } else {
            return create(furnitureId);
        }
    }

    private boolean create(UUID furnitureId) {
        Optional<Furniture> optionalFurniture = furnitureService.getById(furnitureId);
        if (optionalFurniture.isPresent()) {
            CartItem cartItem = new CartItem(optionalFurniture.get());
            updateQuantityAndStock(cartItem, 1);
            cartItemRepository.save(cartItem);
            return true;
        } else {
            //TODO handle if optional is empty
            return false;
        }
    }

    public CartItem update(CartItem cartItem, int quantity) {
        updateQuantityAndStock(cartItem, quantity);
        return cartItemRepository.save(cartItem);
    }

    public void deleteById(UUID id) {
        getById(id).ifPresent(cartItem -> updateQuantityAndStock(cartItem, -cartItem.getQuantity()));
        cartItemRepository.deleteById(id);
    }

    public void deleteAll() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        cartItems.forEach(cartItem -> updateQuantityAndStock(cartItem, -cartItem.getQuantity()));
        cartItemRepository.deleteAll();
    }

    // logic business
    private void updateQuantityAndStock(CartItem cartItem, int quantity) {
        cartItem.updateQuantity(quantity);
        cartItem.getFurniture().updateStock(-quantity);
    }
}
