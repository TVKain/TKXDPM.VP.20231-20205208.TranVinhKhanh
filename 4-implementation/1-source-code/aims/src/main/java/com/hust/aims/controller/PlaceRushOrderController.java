package com.hust.aims.controller;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;

import java.util.Locale;

public class PlaceRushOrderController {

    public boolean validateRushOrderMediaSupport(Cart cart) {
        boolean flag = false;

        for (Media media : cart.getMediaList()) {
            if (media.getSupportRushOrder()) {
                flag = true;
                break;
            }
        }

        return flag;
    }
    public boolean validateRushOrderAddress(String address) {
        address = address.toLowerCase(Locale.of("vn", "vi"));
        System.out.println(address);
        return address.contains("hà nội") || address.contains("ha noi");
    }
}
