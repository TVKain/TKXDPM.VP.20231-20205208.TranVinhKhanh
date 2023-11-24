package com.hust.aims.service.dao;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.model.order.Order;
import com.hust.aims.service.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class OrderMediaDao {
    public void insert(Order order) {
        Connection connection = Database.getConnection();

        Map<Media, Integer> mediaMap = Cart.getCart().getMediaMap();

        String query = "INSERT INTO OrderMedia (Order_OrderId, Media_MediaId, Quantity) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (Map.Entry<Media, Integer> entry : mediaMap.entrySet()) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, entry.getKey().getId());
                preparedStatement.setInt(3, entry.getValue());
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}