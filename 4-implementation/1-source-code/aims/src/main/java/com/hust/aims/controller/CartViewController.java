package com.hust.aims.controller;

import com.hust.aims.model.Cart;

public class CartViewController {
    public boolean verifyStockInCart() {
        return Cart.getCart().hasEnoughStock();
    }
}
