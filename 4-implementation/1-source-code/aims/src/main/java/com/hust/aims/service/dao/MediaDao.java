package com.hust.aims.service.dao;

import com.hust.aims.model.media.Media;
import com.hust.aims.service.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaDao implements Dao<Media> {
    private final List<Media> mediaList;
    public MediaDao() {
        mediaList = new ArrayList<Media>();

        Media media1 = new Media(1, 20000., 100, "Movie A", "Action", "An exciting action movie", 50, 3., false);
        Media media2 = new Media(2, 15000., 80, "Book B", "Fantasy", "A captivating fantasy book", 30, 1., false);
        Media media3 = new Media(3, 10000., 50, "Music C", "Pop", "Catchy pop music album", 100, 2., false);
        Media media4 = new Media(4, 25000., 120, "Game D", "Adventure", "An immersive adventure game", 40,1., false);
        Media media5 = new Media(5, 12000., 60, "Magazine E", "Lifestyle", "A trendy lifestyle magazine", 75, 2.,true);

        Media media6 = new Media(6, 18000., 90, "Movie F", "Drama", "A gripping drama movie", 60, 4., true);
        Media media7 = new Media(7, 13000., 70, "Book G", "Science Fiction", "An intriguing science fiction book", 45, 3.5, true);
        Media media8 = new Media(8, 8000., 40, "Music H", "Rock", "Energetic rock music album", 85, 4.5, true);
        Media media9 = new Media(9, 22000., 110, "Game I", "Strategy", "A challenging strategy game", 55, 3.5, false);
        Media media10 = new Media(10, 15000., 75, "Magazine J", "Fashion", "A stylish fashion magazine", 70, 4., true);

        Media media11 = new Media(11, 16000., 85, "Movie K", "Comedy", "A hilarious comedy movie", 65, 3.5, true);
        Media media12 = new Media(12, 11000., 55, "Book L", "Mystery", "An intriguing mystery book", 50, 4., true);
        Media media13 = new Media(13, 9000., 45, "Music M", "Hip Hop", "Groovy hip hop music album", 90, 3., false);
        Media media14 = new Media(14, 20000., 95, "Game N", "Simulation", "An immersive simulation game", 35, 4., true);
        Media media15 = new Media(15, 14000., 65, "Magazine O", "Technology", "An informative technology magazine", 80, 3.5, true);
        Media media16 = new Media(16, 17000., 105, "Movie P", "Romance", "A heartwarming romance movie", 55, 4., true);
        Media media17 = new Media(17, 12000., 50, "Book Q", "Historical Fiction", "A compelling historical fiction book", 40, 3.5, false);
        Media media18 = new Media(18, 10000., 60, "Music R", "Country", "Soulful country music album", 70, 4., true);
        Media media19 = new Media(19, 23000., 115, "Game S", "Puzzle", "A challenging puzzle game", 45, 3., false);
        Media media20 = new Media(20, 16000., 70, "Magazine T", "Travel", "An adventurous travel magazine", 60, 4., true);

        mediaList.add(media1);
        mediaList.add(media2);
        mediaList.add(media3);
        mediaList.add(media4);
        mediaList.add(media5);
        mediaList.add(media6);
        mediaList.add(media7);
        mediaList.add(media8);
        mediaList.add(media9);
        mediaList.add(media10);
        mediaList.add(media11);
        mediaList.add(media12);
        mediaList.add(media13);
        mediaList.add(media14);
        mediaList.add(media15);
        mediaList.add(media16);
        mediaList.add(media17);
        mediaList.add(media18);
        mediaList.add(media19);
        mediaList.add(media20);
    }

    @Override
    public List<Media> getAll() {
        Connection connection = Database.getConnection();

        String query = "SELECT * FROM Media";

        ArrayList<Media> medias = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                medias.add(mapToMedia(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return medias;
    }

    @Override
    public Media get(Integer id) {
        Connection connection = Database.getConnection();

        String query = String.format("SELECT * FROM Media WHERE MediaID=%d", id);

        Media media;
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            media = mapToMedia(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return media;
    }

    @Override
    public boolean update(Media item) {
        return false;
    }
    @Override
    public boolean delete(Media item) {
        return false;
    }

    @Override
    public Media insert(Media item) {
        return null;
    }

    private Media mapToMedia(ResultSet resultSet) {
        Media media = new Media();
        try {
            media.setId(resultSet.getInt("MediaId"));
            media.setTitle(resultSet.getString("Title"));
            media.setValue(resultSet.getInt("Value"));
            media.setQuantity(resultSet.getInt("Quantity"));
            media.setWeight((double) resultSet.getFloat("Weight"));
            media.setPrice((double) resultSet.getFloat("Price"));
            media.setCategory(resultSet.getString("Category"));
            media.setDescription(resultSet.getString("Description"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return media;
    }
}
