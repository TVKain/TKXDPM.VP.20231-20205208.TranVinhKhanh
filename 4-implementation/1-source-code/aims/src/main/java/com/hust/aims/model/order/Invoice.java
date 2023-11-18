package com.hust.aims.model.order;

public class Invoice {
    private Integer id;
    private Double total;
    private Double shippingFee;
    private Double mediaFee;
    private Double vat;


    public Invoice() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getMediaFee() {
        return mediaFee;
    }

    public void setMediaFee(Double mediaFee) {
        this.mediaFee = mediaFee;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public void calculateTotal() {
        total = shippingFee + mediaFee * (100 + vat) / 100;
    }
}
