package com.hust.aims.service.dao;

import com.hust.aims.model.order.DeliveryInfo;
import com.hust.aims.service.database.Database;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DeliveryInfoDao implements Dao<DeliveryInfo> {
    @Override
    public List<DeliveryInfo> getAll() {
        return null;
    }


    // Todo
    @Override
    public DeliveryInfo get(Integer id) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        Connection connection = Database.getConnection();

        String query = "SELECT * FROM DeliveryInfo WHERE DeliveryInfoID=" + id;

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean update(DeliveryInfo item) {
        return false;
    }

    @Override
    public boolean delete(DeliveryInfo item) {
        return false;
    }

    @Override
    public DeliveryInfo insert(DeliveryInfo item) {
        Connection connection = Database.getConnection();

        String query = "INSERT INTO DeliveryInfo (Name, City, Address, Phone, Instruction, RushOrder) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getCity());
            preparedStatement.setString(3, item.getAddress());
            preparedStatement.setString(4, item.getPhone());
            preparedStatement.setString(5, item.getShippingInstruction());
            preparedStatement.setString(6, item.isRushOrder() ? "yes" : "no");

            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT last_insert_rowid()");
            ResultSet resultSet = preparedStatement1.executeQuery();

            item.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return item;
    }
}
