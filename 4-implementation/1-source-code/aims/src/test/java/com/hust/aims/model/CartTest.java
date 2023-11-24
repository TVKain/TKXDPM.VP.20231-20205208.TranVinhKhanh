package com.hust.aims.model;

import com.hust.aims.model.media.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    @BeforeEach
    public void initialize() {
        Cart.getCart().empty();
    }
    @Test
    public void addTest() {
        Media media = new Media();
        media.setId(0);

        Media media1 = new Media();
        media1.setId(1);
        Cart.getCart().add(media, 2);
        Cart.getCart().add(media1, 3);

        assertEquals(5, Cart.getCart().getSize());
    }

    @Test
    public void totalTest() {
        Media media = new Media();
        media.setId(0);
        media.setPrice(1000.);

        Media media1 = new Media();
        media1.setId(1);
        media1.setPrice(2000.);

        Cart.getCart().add(media, 10);
        Cart.getCart().add(media1, 10);

        assertEquals(30000, Cart.getCart().getTotal());
    }
}
