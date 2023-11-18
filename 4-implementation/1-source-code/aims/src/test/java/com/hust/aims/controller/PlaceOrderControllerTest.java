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
            "'Hà Nội, Việt Nam', true",
            "'Số nhà 20, ngách 12, đường về nhà, là vào tim ta', true",
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
            "Nguyễn Văn A, true",
            "Nguyễn Văn B 69420, false",
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
        boolean isValid = placeOrderController.validatePhoneNumber(phoneNumber);
        assertEquals(expected, isValid);
    }

    @Test
    public void calculateShippingFeeTest1() {
        Cart.getCart().empty();

        Media media1 = new Media();
        media1.setPrice(30000.);
        media1.setWeight(2.5);
        media1.setQuantity(3);

        Media media2 = new Media();
        media2.setPrice(15000.);
        media2.setWeight(10.);
        media2.setQuantity(4);

        Cart.getCart().addMedia(media1);
        Cart.getCart().addMedia(media2);

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCity("hà nội");
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
        media1.setQuantity(3);
        media1.setSupportRushOrder(true);

        Media media2 = new Media();
        media2.setPrice(15000.);
        media2.setWeight(10.);
        media2.setQuantity(4);
        media2.setSupportRushOrder(true);

        Cart.getCart().addMedia(media1);
        Cart.getCart().addMedia(media2);

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCity("hà nội");
        deliveryInfo.setRushOrder(false);
        placeOrderController.getOrder().setDeliveryInfo(deliveryInfo);

        Double shippingFee = placeOrderController.calculateShippingFee();

        assertEquals(207000, shippingFee);
    }

    @Test
    public void calculateShippingFeeTest3() {
        Cart.getCart().empty();

        Media media1 = new Media();
        media1.setPrice(1000.);
        media1.setWeight(2.5);
        media1.setQuantity(3);
        media1.setSupportRushOrder(true);

        Media media2 = new Media();
        media2.setPrice(15000.);
        media2.setWeight(10.);
        media2.setQuantity(4);
        media2.setSupportRushOrder(true);

        Cart.getCart().addMedia(media1);
        Cart.getCart().addMedia(media2);

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCity("hà nội");
        deliveryInfo.setRushOrder(true);
        placeOrderController.getOrder().setDeliveryInfo(deliveryInfo);

        Double shippingFee = placeOrderController.calculateShippingFee();

        assertEquals(227000, shippingFee);
    }
}


