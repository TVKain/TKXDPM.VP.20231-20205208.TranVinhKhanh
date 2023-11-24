package com.hust.aims.model;

import com.hust.aims.model.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;


/**
 * Cart class, implemented as Singleton, one global object
 * @author TVKain
 */
public class Cart {
    public static final double VAT = 10;
    private static final Cart cart = new Cart();
    private final ObservableMap<Media, Integer> mediaMap;
    private Cart() {
        this.mediaMap = FXCollections.observableHashMap();
    }
    public ObservableMap<Media, Integer> getMediaMap() {
        return mediaMap;
    }
    public static Cart getCart() {
        return cart;
    }
    public void add(Media media, Integer quantity) {
        if (mediaMap.containsKey(media)) {
            mediaMap.put(media, mediaMap.get(media) + quantity);
        } else {
            mediaMap.put(media, quantity);
        }
    }

    public void remove(Media media) {
        mediaMap.remove(media);
    }

    public Double getTotal() {
        double total = 0.0;
        for (Map.Entry<Media, Integer> entry : mediaMap.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Integer getSize() {
        int size = 0;

        for (Integer value : mediaMap.values()) {
            size += value;
        }

        return size;
    }

    public void empty() {
        mediaMap.clear();
    }

    public HashMap<Media, Integer> getRushOrderSupportMedias() {
        HashMap<Media, Integer> rushOrderSupportMedias = new HashMap<>();
        for (Map.Entry<Media, Integer> entry : mediaMap.entrySet()) {
            if (entry.getKey().getSupportRushOrder()) {
                rushOrderSupportMedias.put(entry.getKey(), entry.getValue());
            }
        }
        return rushOrderSupportMedias;
    }

    public boolean hasEnoughStock() {
        for (Map.Entry<Media, Integer> entry : mediaMap.entrySet()) {
            if (entry.getValue() > entry.getKey().getQuantity()) {
                return false;
            }
        }
        retuflrn true;
    }

    public Map.Entry<Media, Integer> getHeaviestMedia() {
        double heaviest = 0.;

        Map.Entry<Media, Integer> heaviesMedia = null;
        for (Map.Entry<Media, Integer> entry : mediaMap.entrySet()) {
            double weight = entry.getKey().getWeight() * entry.getValue();
            if (weight > heaviest) {
                weight = heaviest;
                heaviesMedia = entry;
            }
        }

        return heaviesMedia;
    }
}
