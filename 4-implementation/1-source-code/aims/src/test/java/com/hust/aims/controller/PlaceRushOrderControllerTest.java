package com.hust.aims.controller;

import com.hust.aims.model.Cart;
import com.hust.aims.model.CartTest;
import com.hust.aims.model.media.Media;
import com.hust.aims.model.order.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceRushOrderControllerTest {
    private final PlaceRushOrderController placeRushOrderController = new PlaceRushOrderController(new Order());;

    @Test
    public void validateRushOrderMediaSupportTest() {
        Cart.getCart().empty();

        Media media1 = new Media(1, 20., 100, "Movie A", "Action", "An exciting action movie", 50, 3., false);
        Media media2 = new Media(2, 15., 80, "Book B", "Fantasy", "A captivating fantasy book", 30, 1., false);
        Media media3 = new Media(3, 10., 50, "Music C", "Pop", "Catchy pop music album", 100, 2., true);
        Media media4 = new Media(4, 25., 120, "Game D", "Adventure", "An immersive adventure game", 40,1., false);
        Media media5 = new Media(5, 12., 60, "Magazine E", "Lifestyle", "A trendy lifestyle magazine", 75, 2.,false);

        Cart.getCart().add(media1, 4);
        Cart.getCart().add(media2, 5);
        Cart.getCart().add(media3, 6);
        Cart.getCart().add(media4, 8);
        Cart.getCart().add(media5, 4);

        assertTrue(placeRushOrderController.validateRushOrderMediaSupport());

        Cart.getCart().empty();

        Media media6 = new Media(1, 20., 100, "Movie A", "Action", "An exciting action movie", 50, 3., false);
        Media media7 = new Media(2, 15., 80, "Book B", "Fantasy", "A captivating fantasy book", 30, 1., false);
        Media media8 = new Media(3, 10., 50, "Music C", "Pop", "Catchy pop music album", 100, 2., false);
        Media media9 = new Media(4, 25., 120, "Game D", "Adventure", "An immersive adventure game", 40,1., false);
        Media media10 = new Media(5, 12., 60, "Magazine E", "Lifestyle", "A trendy lifestyle magazine", 75, 2.,false);


        Cart.getCart().add(media6, 1);
        Cart.getCart().add(media7, 1);
        Cart.getCart().add(media8, 1);
        Cart.getCart().add(media9, 1);
        Cart.getCart().add(media10, 1);

        assertFalse(placeRushOrderController.validateRushOrderMediaSupport());
    }


    @ParameterizedTest
    @CsvSource({
            "'Ha Noi', true",
            "'ha noi', true",
            "'TP. HCM', false",
            "'Bac Ninh';, false"
    })
    public void validateRushOrderAddressTest(String address, boolean expected) {
        boolean isValid = placeRushOrderController.validateRushOrderAddress(address);
        assertEquals(expected, isValid);
    }
}
