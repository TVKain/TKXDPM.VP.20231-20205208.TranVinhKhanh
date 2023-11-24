package com.hust.aims.view.payment;

import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.home.HomeView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PaymentView {

    @FXML
    Button homeButton;

    @FXML
    public void initialize() {
        homeButton.setOnMouseClicked(e -> {
            ScreenSwitcher.setScreen("home", new HomeView());
        });
    }
}
