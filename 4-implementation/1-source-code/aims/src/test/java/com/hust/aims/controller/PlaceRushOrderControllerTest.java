package com.hust.aims.controller;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceRushOrderControllerTest {
    private final PlaceRushOrderController placeRushOrderController = new PlaceRushOrderController();;

    @Test
    public void validateRushOrderMediaSupportTest() {
        Cart.getCart().empty();

        Media media1 = new Media(1, 20., 100, "Movie A", "Action", "An exciting action movie", 50, 3., false);
        Media media2 = new Media(2, 15., 80, "Book B", "Fantasy", "A captivating fantasy book", 30, 1., false);
        Media media3 = new Media(3, 10., 50, "Music C", "Pop", "Catchy pop music album", 100, 2., true);
        Media media4 = new Media(4, 25., 120, "Game D", "Adventure", "An immersive adventure game", 40,1., false);
        Media media5 = new Media(5, 12., 60, "Magazine E", "Lifestyle", "A trendy lifestyle magazine", 75, 2.,false);

        Cart.getCart().addMedia(media1);
        Cart.getCart().addMedia(media2);
        Cart.getCart().addMedia(media3);
        Cart.getCart().addMedia(media4);
        Cart.getCart().addMedia(media5);

        assertTrue(placeRushOrderController.validateRushOrderMediaSupport(Cart.getCart()));

        Cart.getCart().empty();

        Media media6 = new Media(1, 20., 100, "Movie A", "Action", "An exciting action movie", 50, 3., false);
        Media media7 = new Media(2, 15., 80, "Book B", "Fantasy", "A captivating fantasy book", 30, 1., false);
        Media media8 = new Media(3, 10., 50, "Music C", "Pop", "Catchy pop music album", 100, 2., false);
        Media media9 = new Media(4, 25., 120, "Game D", "Adventure", "An immersive adventure game", 40,1., false);
        Media media10 = new Media(5, 12., 60, "Magazine E", "Lifestyle", "A trendy lifestyle magazine", 75, 2.,false);


        Cart.getCart().addMedia(media6);
        Cart.getCart().addMedia(media7);
        Cart.getCart().addMedia(media8);
        Cart.getCart().addMedia(media9);
        Cart.getCart().addMedia(media10);

        assertFalse(placeRushOrderController.validateRushOrderMediaSupport(Cart.getCart()));
    }

    @ParameterizedTest
    @CsvSource({
            "'Hà Nội', true",
            "'hà nội', true",
            "'TP. HCM', false",
            "'Bắc Ninh';, false"
    })
    public void validateRushOrderAddressTest(String address, boolean expected) {
        boolean isValid = placeRushOrderController.validateRushOrderAddress(address);
        assertEquals(expected, isValid);
    }
}
