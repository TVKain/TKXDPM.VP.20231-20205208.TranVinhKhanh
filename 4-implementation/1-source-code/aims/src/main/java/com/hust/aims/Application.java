package com.hust.aims;

import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.home.HomeView;
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
        fxmlLoader.setController(new HomeView());

        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);

        // Initialize screen switcher
        ScreenSwitcher.setMainScene(scene);
        ScreenSwitcher.addScreen("home", "/com/hust/aims/view/home/home.fxml");

        initializeScreenSwitcher();
        stage.setMaximized(true);
        stage.setTitle("AIMS");
        stage.setScene(scene);
        stage.show();
    }

    public void initializeScreenSwitcher() throws IOException {
        ScreenSwitcher.addScreen("cart", "/com/hust/aims/view/cart/cart.fxml");
        ScreenSwitcher.addScreen("shipping", "/com/hust/aims/view/shipping/shipping.fxml");
        ScreenSwitcher.addScreen("invoice", "/com/hust/aims/view/invoice/invoice.fxml");
        ScreenSwitcher.addScreen("success", "/com/hust/aims/view/payment_result/success.fxml");
        ScreenSwitcher.addScreen("fail", "/com/hust/aims/view/payment_result/fail.fxml");
    }
}