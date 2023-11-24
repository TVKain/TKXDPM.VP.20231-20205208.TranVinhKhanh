package com.hust.aims.subsystem.banking.vnpay;

import com.hust.aims.model.Cart;
import com.hust.aims.model.order.DeliveryInfo;
import com.hust.aims.model.order.Invoice;
import com.hust.aims.model.order.Order;
import com.hust.aims.service.dao.*;
import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.payment.PaymentView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class VNPayController {
    public String buildUrl(String amount) {
        String url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?";
        String vnp_Amount = "vnp_Amount=" + amount + "00&";
        String vnp_BankCode = "vnp_BankCode=NCB&";
        String vnp_Command = "vnp_Command=pay&";
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = "vnp_CreateDate=" + formatter.format(cld.getTime()) + "&";

        String vnp_CurrCode = "vnp_CurrCode=VND&";
        String vnp_IpAddr = "vnp_IpAddr=222.252.10.226&";
        String vnp_Locale = "vnp_Locale=vn&";
        String vnp_OrderInfo = "vnp_OrderInfo=Thanh+toan+don+hang+%3A5&";
        String vnp_OrderType = "vnp_OrderType=other&";
        String vnp_ReturnUrl = "vnp_ReturnUrl=https%3A%2F%2Fsandbox.vnpayment.vn%2Ftryitnow%2FHome%2FVnPayReturn&";
        String vnp_TmnCode = "vnp_TmnCode=" + Config.vnp_TmnCode + "&";
        String vnp_TxnRef = "vnp_TxnRef=" + Config.getRandomNumber(8) + "&";
        String vnp_Version = "vnp_Version=2.1.0";

        String hashData = vnp_Amount + vnp_BankCode + vnp_Command  + vnp_CreateDate + vnp_CurrCode + vnp_IpAddr + vnp_Locale + vnp_OrderInfo + vnp_OrderType + vnp_ReturnUrl + vnp_TmnCode + vnp_TxnRef + vnp_Version;
        url += hashData;
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData);
        url += "&vnp_SecureHash=" + vnp_SecureHash;
        System.out.println(url);
        return url;
    }

    public void handleResponse(Order order, String url) {
        if (url.contains("vnp_TransactionStatus")) {
            if (url.contains("vnp_TransactionStatus=00")) {
                new Thread(() -> {
                    Dao<Order> orderDao = new OrderDao();
                    Dao<DeliveryInfo> deliveryInfoDao = new DeliveryInfoDao();
                    Dao<Invoice> invoiceDao = new InvoiceDao();
                    OrderMediaDao orderMediaDao = new OrderMediaDao();

                    DeliveryInfo deliveryInfo = deliveryInfoDao.insert(order.getDeliveryInfo());
                    Invoice invoice = invoiceDao.insert(order.getInvoice());

                    order.getDeliveryInfo().setId(deliveryInfo.getId());
                    order.getInvoice().setId(invoice.getId());

                    order.setId(orderDao.insert(order).getId());

                    orderMediaDao.insert(order);

                    Cart.getCart().empty();
                }).start();

                ScreenSwitcher.setScreen("success", new PaymentView());

            } else {
                ScreenSwitcher.setScreen("fail", new PaymentView());
            }
        }
    }
}
