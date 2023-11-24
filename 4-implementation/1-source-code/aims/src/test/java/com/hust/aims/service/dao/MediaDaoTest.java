package com.hust.aims.service.dao;

import com.hust.aims.model.media.Media;

import java.util.List;

public class MediaDaoTest {

    public static void main(String[] args) {
        Dao<Media> mediaDao = new MediaDao();

        Media media = mediaDao.get(1);

        System.out.println(media.getTitle());

        List<Media> medias = mediaDao.getAll();

        for (Media med : medias) {
            System.out.println(med.getTitle());
        }
    }
}
