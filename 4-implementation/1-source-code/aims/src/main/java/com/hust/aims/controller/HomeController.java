package com.hust.aims.controller;

import com.hust.aims.service.dao.Dao;
import com.hust.aims.model.media.Media;


import java.util.List;

public class HomeController {
    public List<Media> getMedias(Dao<Media> mediaDao) {
        return mediaDao.getAll();
    }
}
