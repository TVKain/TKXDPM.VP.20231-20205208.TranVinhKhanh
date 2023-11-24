package com.hust.aims.service.dao;

import com.hust.aims.model.order.DeliveryInfo;
import org.junit.jupiter.api.Test;

public class DeliveryInfoDaoTest {

    @Test
    public void insertTest() {
        DeliveryInfoDao deliveryInfoDao = new DeliveryInfoDao();

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setName("Khanh");
        deliveryInfo.setCity("Ha Noi");
        deliveryInfo.setAddress("GH Noi");
        deliveryInfo.setPhone("1234567890");
        deliveryInfo.setRushOrder(true);

        deliveryInfo.setId(deliveryInfoDao.insert(deliveryInfo).getId());

        assert(deliveryInfo.getId() != null);
    }

    @Test
    public void getTest() {
        DeliveryInfoDao deliveryInfoDao = new DeliveryInfoDao();
        DeliveryInfo deliveryInfo = deliveryInfoDao.get(0);
    }
}
