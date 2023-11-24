package com.hust.aims.model.media;

import java.util.Objects;

public class Media {
    private Integer id;
    private Double price;
    private Integer value;
    private String title;
    private String category;
    private String description;
    private Integer quantity;

    private Double weight;

    public Media() {

    }
    public Media(Integer id, Double price, Integer value, String title, String category, String description, Integer quantity, Double weight, boolean supportRushOrder) {
        this.id = id;
        this.price = price;
        this.value = value;
        this.title = title;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.supportRushOrder = supportRushOrder;
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private boolean supportRushOrder;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getSupportRushOrder() {
        return supportRushOrder;
    }

    public void setSupportRushOrder(boolean supportRushOrder) {
        this.supportRushOrder = supportRushOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
