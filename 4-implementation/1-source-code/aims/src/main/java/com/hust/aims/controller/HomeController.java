package com.hust.aims.controller;

import com.hust.aims.ScreenSwitcher;
import com.hust.aims.model.media.Media;
import com.hust.aims.service.dao.MediaDao;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


import java.util.List;

public class HomeController {
    @FXML
    private ImageView cartButton;

    @FXML
    public void initialize() {
        cartButton.setOnMouseClicked(e -> {
            ScreenSwitcher.setScreen("cart", new Object());
        });
    }

    public void initializeMediaList() {
        List<Media> mediaList = new MediaDao().getAll();


    }

    public VBox initializeMediaItem() {
        VBox parent = new VBox();


        return parent;
    }
}
