package com.hust.aims.controller;

import com.hust.aims.model.Cart;

import com.hust.aims.model.media.Media;
import com.hust.aims.model.order.DeliveryInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOrderControllerTest {
    private static final PlaceOrderController placeOrderController = new PlaceOrderController();

    @ParameterizedTest
    @CsvSource({
            "'Ha Noi, Viet Nam', true",
            "'So nha 20, Ngach 39, Hahahahah', true",
            "1 , false",
            "'', false",
            "'  ', false",
            "'ThisIsAInValidAddress1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz," +
                    "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzasgasgasgasgasgasg', false"
    })
    public void validateAddressTest(String address, boolean expected) {
        boolean isValid = placeOrderController.validateAddress(address);
        assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            "Nguyen Van A, true",
            "Nguyen Van B 69420, false",
            "213123!@#!@$#, false",
            "'', false",
            "'     ', false",
            "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzasasasasasasasasasasasasas, false",
            ", false"
    })
    public void validateNameTest(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        assertEquals(expected, isValid);
    }
    @ParameterizedTest
    @CsvSource({
            "  9876543210  , true",
            ", false",
            "12345, false",
            "1234567890, true",
            "9876543210, true",
            "boots and cats, false"
    })
    public void validatePhoneNumberTest(String phoneNumber, boolean expected) {
        boolean isValid = placeOrderController.validatePhone(phoneNumber);
        assertEquals(expected, isValid);
    }

    @Test
    public void calculateShippingFeeTest1() {
        Cart.getCart().empty();

        Media media1 = new Media();
        media1.setPrice(30000.);
        media1.setWeight(2.5);

        Media media2 = new Media();
        media2.setPrice(15000.);
        media2.setWeight(10.);

        Cart.getCart().add(media1, 3);
        Cart.getCart().add(media2, 4);

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCity("ha noi");
        placeOrderController.getOrder().setDeliveryInfo(deliveryInfo);

        Double shippingFee = placeOrderController.calculateShippingFee();

        assertEquals(0, shippingFee);
    }

    @Test
    public void calculateShippingFeeTest2() {
        Cart.getCart().empty();

        Media media1 = new Media();
        media1.setPrice(1000.);
        media1.setWeight(2.5);
        media1.setSupportRushOrder(true);

        Media media2 = new Media();
        media2.setPrice(15000.);
        media2.setWeight(10.);
        media2.setSupportRushOrder(true);

        Cart.getCart().add(media1, 3);
        Cart.getCart().add(media2, 4);

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCity("ha noi");
        deliveryInfo.setRushOrder(false);
        placeOrderController.getOrder().setDeliveryInfo(deliveryInfo);

        Double shippingFee = placeOrderController.calculateShippingFee();

        assertEquals(94500,  shippingFee);
    }
}


