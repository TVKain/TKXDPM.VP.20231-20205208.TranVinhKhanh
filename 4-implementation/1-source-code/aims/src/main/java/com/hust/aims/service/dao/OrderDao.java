package com.hust.aims.service.dao;

import com.hust.aims.model.order.Order;
import com.hust.aims.service.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements  Dao<Order> {
    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order get(Integer id) {
        return null;
    }

    @Override
    public boolean update(Order item) {
        return false;
    }

    @Override
    public boolean delete(Order item) {
        return false;
    }

    @Override
    public Order insert(Order item) {
        Connection connection = Database.getConnection();

        String query = "INSERT INTO \"Order\" (DeliveryInfo_DeliveryInfoId, Invoice_InvoiceId)" +
                "VALUES" +
                "(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.getDeliveryInfo().getId());
            preparedStatement.setInt(2, item.getInvoice().getId());

            preparedStatement.execute();

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT last_insert_rowid()");
            ResultSet resultSet = preparedStatement1.executeQuery();

            item.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return item;
    }

}
