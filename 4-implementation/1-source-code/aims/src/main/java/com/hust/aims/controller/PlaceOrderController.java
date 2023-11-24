package com.hust.aims.controller;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.model.order.DeliveryInfo;
import com.hust.aims.model.order.Order;
import com.hust.aims.subsystem.banking.Banking;
import com.hust.aims.subsystem.banking.vnpay.VNPaySubsystem;

import java.util.List;
import java.util.Map;

public class PlaceOrderController {
    private final Order order = new Order();
    private final DeliveryInfo deliveryInfo = new DeliveryInfo();
    public Order getOrder() {
        return order;
    }

    public void initializeDeliveryInfo(String name, String phone, String city, String address, String shipping, boolean rushOrder) {
        deliveryInfo.setName(name);
        deliveryInfo.setPhone(phone);
        deliveryInfo.setCity(city);
        deliveryInfo.setAddress(address);
        deliveryInfo.setShippingInstruction(shipping == null ? "" : shipping);
        deliveryInfo.setRushOrder(rushOrder);
    }

    public void initializeOrder() {
        order.setDeliveryInfo(this.deliveryInfo);
        order.getInvoice().setShippingFee(calculateShippingFee());
        order.getInvoice().setMediaFee(Cart.getCart().getTotal());
        order.getInvoice().setVat(Cart.VAT);
        order.getInvoice().calculateTotal();
    }

    /**
     * @return the shipping fee for the current cart
     */
    public Double calculateShippingFee() {
        Double mediaFee = Cart.getCart().getTotal();

        if (mediaFee > 100000) {
            return 0.0;
        }

        double shippingFee;

        Map.Entry<Media, Integer> heaviestMedia = Cart.getCart().getHeaviestMedia();

        double weight = heaviestMedia.getKey().getWeight() * heaviestMedia.getValue();
        double remainWeight;

        final List<String> discountCities = List.of(new String[]{"ha noi", "tp. hcm"});
        if (discountCities.contains(order.getDeliveryInfo().getCity())) {
            shippingFee = 22000.0;
            remainWeight = weight - 3;
        } else {
            shippingFee = 30000.0;
            remainWeight = weight - 0.5;
        }
        shippingFee += 5000 * remainWeight;

        return shippingFee;
    }

    public void redirectToPayment() {
        Banking vnpay = new VNPaySubsystem();

        vnpay.processTransaction(order);
    }

    public boolean validatePhone(String phoneNumber) {
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

        return name.matches("^[a-zA-Z ]{1,64}$" );
    }
    public boolean validateAddress(String address) {
        if (address == null) {
            return false;
        }

        address = address.trim();

        return address.matches("^[a-zA-Z0-9, ]{4,128}$");
    }
}
