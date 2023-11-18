package com.hust.aims.model;

import com.hust.aims.model.media.Media;

import java.util.ArrayList;
import java.util.List;


/**
 * Cart class, implemented as Singleton, one global object
 * @author TVKain
 */
public class Cart {
    private static Cart cart = null;
    private final List<Media> mediaList;

    private Double total = 0.0;

    private Cart() {
        this.mediaList = new ArrayList<Media>();
    }

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Media> getMediaList() {
        return this.mediaList;
    }

    public void addMedia(Media media) {
        this.mediaList.add(media);
        total += media.getPrice() * media.getQuantity();
    }

    public void removeMedia(Media media) {
        this.mediaList.remove(media);
        total -= media.getPrice() * media.getQuantity();
    }

    public void empty() {
        mediaList.clear();
        total = 0.0;
    }

    public Integer getMediaNumber() {
        return mediaList.size();
    }

    public boolean isInCart(Media media) {
        return mediaList.contains(media);
    }

    public Media getHeaviestMedia() {
        Media heaviestMedia = mediaList.get(0);

        for (int i = 1; i < mediaList.size(); ++i) {
            Media currentMedia = mediaList.get(i);
            if (heaviestMedia.getWeight() * heaviestMedia.getQuantity() < currentMedia.getWeight() * currentMedia.getQuantity()) {
                heaviestMedia = mediaList.get(i);
            }
        }

        return heaviestMedia;
    }

    public Integer getRushOrderSupportedNumber() {
        int count = 0;
        for (Media media : mediaList) {
            if (media.getSupportRushOrder()) {
                count += 1;
            }
        }
        return count;
    }
}
