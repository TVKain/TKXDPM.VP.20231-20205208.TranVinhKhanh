package com.hust.aims.service.dao;

import com.hust.aims.model.order.DeliveryInfo;
import com.hust.aims.model.order.Invoice;
import com.hust.aims.model.order.Order;
import org.junit.jupiter.api.Test;

public class OrderDaoTest {

    @Test
    void Test() {
        DeliveryInfoDao deliveryInfoDao = new DeliveryInfoDao();

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setName("Khanh");
        deliveryInfo.setCity("Ha Noi");
        deliveryInfo.setAddress("GH Noi");
        deliveryInfo.setPhone("1234567890");
        deliveryInfo.setRushOrder(true);

        deliveryInfo.setId(deliveryInfoDao.insert(deliveryInfo).getId());

        assert(deliveryInfo.getId() != null);

        InvoiceDao invoiceDao = new InvoiceDao();

        Invoice invoice = new Invoice();
        invoice.setShippingFee(30.);
        invoice.setMediaFee(20.);
        invoice.setVat(10.);
        invoice.setTotal(40.);

        invoice.setId(invoiceDao.insert(invoice).getId());

        assert(invoice.getId() != null);

        Order order = new Order();
        order.setInvoice(invoice);
        order.setDeliveryInfo(deliveryInfo);

        OrderDao orderDao = new OrderDao();
        order.setId(orderDao.insert(order).getId());

        assert(order.getId() != null);
    }
}
