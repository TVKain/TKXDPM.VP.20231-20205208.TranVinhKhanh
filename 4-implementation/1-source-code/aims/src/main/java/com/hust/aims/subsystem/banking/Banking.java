package com.hust.aims.subsystem.banking;

import com.hust.aims.model.order.Order;

public interface Banking {
    public void processTransaction(Order order);
}
