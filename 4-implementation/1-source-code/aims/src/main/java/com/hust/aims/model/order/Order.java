package com.hust.aims.model.order;

public class Order {
    private Integer id;
    private Invoice invoice;
    private DeliveryInfo deliveryInfo;

    public Order() {
        this.invoice = new Invoice();
        this.deliveryInfo = new DeliveryInfo();
    }

    public Order(Invoice invoice, DeliveryInfo deliveryInfo) {
        this.invoice = invoice;
        this.deliveryInfo = deliveryInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }
}
