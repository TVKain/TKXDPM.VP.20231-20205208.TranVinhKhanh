package com.hust.aims.service.dao;

import com.hust.aims.model.order.Invoice;
import com.hust.aims.service.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDao implements Dao<Invoice>{
    @Override
    public List<Invoice> getAll() {
        return null;
    }

    @Override
    public Invoice get(Integer id) {
        return null;
    }

    @Override
    public boolean update(Invoice item) {
        return false;
    }

    @Override
    public boolean delete(Invoice item) {
        return false;
    }

    @Override
    public Invoice insert(Invoice item) {
        Connection connection = Database.getConnection();

        String query = "INSERT INTO Invoice (Total, Subtotal, Vat, ShippingFee) " +
                "VALUES " +
                "(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1, item.getTotal());
            preparedStatement.setDouble(2, item.getMediaFee());
            preparedStatement.setDouble(3, item.getVat());
            preparedStatement.setDouble(4, item.getShippingFee());

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
