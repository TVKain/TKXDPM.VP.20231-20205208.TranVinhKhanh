package com.hust.aims;

import com.hust.aims.controller.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/home/home.fxml"));
        fxmlLoader.setController(new HomeController());
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);

        // Initialize screen switcher
        ScreenSwitcher.setMainScene(scene);
        ScreenSwitcher.addScreen("home", "view/home/home.fxml");

        initializeScreenSwitcher();

        stage.setTitle("AIMS");
        stage.setScene(scene);
        stage.show();
    }

    public void initializeScreenSwitcher() throws IOException {
        ScreenSwitcher.addScreen("cart", "view/cart/cart.fxml");
    }
}