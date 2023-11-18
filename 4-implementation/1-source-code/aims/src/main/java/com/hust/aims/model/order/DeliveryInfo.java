package com.hust.aims.model.order;

public class DeliveryInfo {
    private Integer id;
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    private String shippingInstruction;
    private boolean rushOrder;

    public DeliveryInfo(Integer id, String name, String city, String address, String phoneNumber, String shippingInstruction, boolean rushOrder) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.shippingInstruction = shippingInstruction;
        this.rushOrder = rushOrder;
    }

    public DeliveryInfo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShippingInstruction() {
        return shippingInstruction;
    }

    public void setShippingInstruction(String shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
    }

    public boolean isRushOrder() {
        return rushOrder;
    }

    public void setRushOrder(boolean rushOrder) {
        this.rushOrder = rushOrder;
    }
}
