package com.hust.aims.service.dao;

import com.hust.aims.model.media.Media;

import java.util.ArrayList;
import java.util.List;

public class MediaDao implements  Dao<Media> {
    private final List<Media> mediaList;
    public MediaDao() {
        mediaList = new ArrayList<Media>();

        Media media1 = new Media(1, 20., 100, "Movie A", "Action", "An exciting action movie", 50, 3., false);
        Media media2 = new Media(2, 15., 80, "Book B", "Fantasy", "A captivating fantasy book", 30, 1., false);
        Media media3 = new Media(3, 10., 50, "Music C", "Pop", "Catchy pop music album", 100, 2., false);
        Media media4 = new Media(4, 25., 120, "Game D", "Adventure", "An immersive adventure game", 40,1., false);
        Media media5 = new Media(5, 12., 60, "Magazine E", "Lifestyle", "A trendy lifestyle magazine", 75, 2.,true);

        mediaList.add(media1);
        mediaList.add(media2);
        mediaList.add(media3);
        mediaList.add(media4);
        mediaList.add(media5);
    }

    @Override
    public List<Media> getAll() {
        return mediaList;
    }

    @Override
    public Media get(Integer id) {
        return mediaList.get(id);
    }

    @Override
    public boolean update(Media item) {
        return false;
    }

    @Override
    public boolean delete(Media item) {
        return false;
    }
}
