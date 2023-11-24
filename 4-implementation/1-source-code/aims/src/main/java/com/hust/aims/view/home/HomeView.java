package com.hust.aims.view.home;

import com.hust.aims.controller.HomeController;
import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.service.dao.MediaDao;
import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.cart.CartView;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class HomeView {
    HomeController homeController = new HomeController();

    @FXML
    GridPane gridPane;

    @FXML
    Label cartSizeLabel;

    @FXML
    ImageView cartButton;

    @FXML
    public void initialize() {
        setUpGrid();
        setUpCartSizeLabel();
        setUpCartButton();
    }

    public void setUpCartButton() {
        cartButton.setOnMouseClicked(e -> {
            ScreenSwitcher.setScreen("cart", new CartView());
        });
    }

    public void setUpCartSizeLabel() {
        StringBinding cartSizeBinding = Bindings.createStringBinding(
                () -> String.valueOf(Cart.getCart().getSize()), Cart.getCart().getMediaMap());

        cartSizeLabel.textProperty().bind(cartSizeBinding);
    }
    public void setUpGrid() {
        List<Media> medias = homeController.getMedias(new MediaDao());

        int mediaIndex = 0;
        final int ROW_NUMBER = 2;
        final int MEDIA_PER_ROW = 10;
        for (int i = 0; i < ROW_NUMBER && mediaIndex < medias.size(); ++i) {

            for (int j = 0; j < MEDIA_PER_ROW && mediaIndex < medias.size(); ++j) {
                gridPane.add(new MediaView(medias.get(mediaIndex)), j, i);
                ++mediaIndex;
            }
        }

        gridPane.setHgap(10);
        gridPane.setVgap(10);
    }
}
