package com.hust.aims.controller;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.model.order.DeliveryInfo;
import com.hust.aims.model.order.Invoice;
import com.hust.aims.model.order.Order;
import com.hust.aims.util.RegexUtil;
import javafx.fxml.FXML;

import java.util.List;

public class PlaceOrderController {
    private final Order order = new Order();

    public Order getOrder() {
        return order;
    }

    @FXML
    public void initialize() {

    }

    public void initializeOrder(DeliveryInfo deliveryInfo) {
        order.setDeliveryInfo(deliveryInfo);

        order.getInvoice().setShippingFee(calculateShippingFee());
        order.getInvoice().setMediaFee(Cart.getCart().getTotal());
        order.getInvoice().setVat(10.0);
        order.getInvoice().calculateTotal();
    }
    /**
     *
     * @return the shipping fee for the current cart
     */
    public Double calculateShippingFee() {
        Double mediaFee = Cart.getCart().getTotal();

        if (mediaFee > 100000) {
            return 0.0;
        }

        Double shippingFee;

        Media heaviestMedia = Cart.getCart().getHeaviestMedia();
        Double weight = heaviestMedia.getWeight() * heaviestMedia.getQuantity();
        Double remainWeight;

        final List<String> discountCities = List.of(new String[]{"hà nội", "tp. hcm"});
        if (discountCities.contains(order.getDeliveryInfo().getCity())) {
            shippingFee = 22000.0;
            remainWeight = weight - 3;
        } else {
            shippingFee = 30000.0;
            remainWeight = weight - 0.5;
        }
        shippingFee += 5000 * remainWeight;

        if (order.getDeliveryInfo().isRushOrder()) {
            shippingFee += 10000 * Cart.getCart().getRushOrderSupportedNumber();
        }

        System.out.println(shippingFee);

        return shippingFee;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        phoneNumber = phoneNumber.trim();

        return phoneNumber.matches("^[0-9]{10}$");
    }

    public boolean validateName(String name) {
        if (name == null) {
            return false;
        }

        name = name.trim();

        return name.matches("^[a-zA-Z " + RegexUtil.vietnamRegex + "]{1,64}$" );
    }

    public boolean validateAddress(String address) {
        if (address == null) {
            return false;
        }

        address = address.trim();

        return address.matches("^[a-zA-Z0-9, " + RegexUtil.vietnamRegex + "]{4,128}$");
    }
}
