package com.hust.aims.controller;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.model.order.Order;

import java.util.Locale;

public class PlaceRushOrderController {
    private final Order order;
    public PlaceRushOrderController(Order order) {
        this.order = order;
    }

    public void updateShippingFee() {
        double shippingFee = order.getInvoice().getShippingFee();

        int rushOrderCount = Cart.getCart().getRushOrderSupportMedias().size();

        shippingFee += rushOrderCount * 10000;

        order.getInvoice().setShippingFee(shippingFee);
    }

    public boolean validateRushOrderMediaSupport() {
        return !Cart.getCart().getRushOrderSupportMedias().isEmpty();
    }
    public boolean validateRushOrderAddress(String address) {
        address = address.toLowerCase(Locale.of("vn", "vi"));
        return address.contains("ha noi");
    }
}
