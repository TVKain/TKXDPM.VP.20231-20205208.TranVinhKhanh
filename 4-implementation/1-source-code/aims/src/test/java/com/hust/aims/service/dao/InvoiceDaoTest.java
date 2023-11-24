package com.hust.aims.service.dao;

import com.hust.aims.model.order.DeliveryInfo;
import com.hust.aims.model.order.Invoice;
import org.junit.jupiter.api.Test;

public class InvoiceDaoTest {
    @Test
    public void Test() {
        InvoiceDao invoiceDao = new InvoiceDao();

        Invoice invoice = new Invoice();
        invoice.setShippingFee(30.);
        invoice.setMediaFee(20.);
        invoice.setVat(10.);
        invoice.setTotal(40.);

        invoice.setId(invoiceDao.insert(invoice).getId());

        assert(invoice.getId() != null);
    }
}
