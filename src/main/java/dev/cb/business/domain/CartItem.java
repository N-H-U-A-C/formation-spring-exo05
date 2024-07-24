package dev.cb.business.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int quantity;

    @ManyToOne
    private Furniture furniture;

    public CartItem() {
    }

    public CartItem(UUID id, int quantity, Furniture furniture) {
        this.id = id;
        this.quantity = quantity;
        this.furniture = furniture;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
