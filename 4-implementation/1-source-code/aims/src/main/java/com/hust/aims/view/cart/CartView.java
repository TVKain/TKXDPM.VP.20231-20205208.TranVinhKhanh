package com.hust.aims.view.cart;

import com.hust.aims.controller.CartViewController;
import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.util.CurrencyFormatter;
import com.hust.aims.util.PopupManager;
import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.home.HomeView;
import com.hust.aims.view.home.MediaView;
import com.hust.aims.view.shipping.ShippingView;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Map;

public class CartView {
    public CartViewController cartViewController = new CartViewController();

    @FXML
    GridPane gridPane;

    @FXML
    Label subtotalLabel;

    @FXML
    Label totalLabel;

    @FXML
    ImageView aimsIcon;

    @FXML
    Button placeOrderButton;

    @FXML
    void initialize() {
        setUpLabels();
        setUpGridPane();
        setUpAimsIcon();
        setUpPlaceOrderButton();
    }

    void setUpPlaceOrderButton() {
        placeOrderButton.setOnMouseClicked(e -> {
            if (Cart.getCart().getMediaMap().isEmpty()) {
                PopupManager.showError("Cart is empty");
                return;
            }

            if (!cartViewController.verifyStockInCart()) {
                PopupManager.showError("Not enough items in stock");
                return;
            }

            ScreenSwitcher.setScreen("shipping", new ShippingView());
        });
    }

    void setUpAimsIcon() {
        aimsIcon.setOnMouseClicked(e -> {
            ScreenSwitcher.setScreen("home", new HomeView());
        });
    }

    void setUpLabels() {
        StringBinding subtotalBinding = Bindings.createStringBinding(() ->
                CurrencyFormatter.vnd(Cart.getCart().getTotal()), Cart.getCart().getMediaMap());

        StringBinding totalBinding = Bindings.createStringBinding(() ->
                CurrencyFormatter.vnd(Cart.getCart().getTotal() * 110/100), Cart.getCart().getMediaMap());

        subtotalLabel.textProperty().bind(subtotalBinding);

        totalLabel.textProperty().bind(totalBinding);
    }

    void setUpGridPane() {
        Cart.getCart().getMediaMap().addListener((MapChangeListener<Media, Integer>) e -> {
            updateGridPane();
        });

        updateGridPane();
    }

    void updateGridPane() {
        gridPane.getChildren().clear();

        ObservableMap<Media, Integer> mediaMap = Cart.getCart().getMediaMap();

        ArrayList<Label> headers = new ArrayList<>();
        headers.add(new Label("Product"));
        headers.add(new Label("Price"));
        headers.add(new Label("Quantity"));
        headers.add(new Label("Total"));
        headers.add(new Label("In stock"));
        headers.add(new Label("Missing"));

        headers.forEach(header -> {
            header.setStyle("-fx-font-weight: bold;");
            header.setMaxWidth(Double.MAX_VALUE);
            header.setAlignment(Pos.CENTER);
            header.setFocusTraversable(false);
        });

        for (int i = 0; i < headers.size(); ++i) {
            gridPane.add(headers.get(i), i, 0);
        }

        int rowCount = 1;

        for (Map.Entry<Media, Integer> entry : mediaMap.entrySet()) {
            Image image = new Image(
                    String.valueOf(MediaView.class.getResource("/com/hust/aims/image/placeholder.jpg")));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(125);

            VBox mediaCell = new VBox();
            mediaCell.setAlignment(Pos.CENTER);
            mediaCell.setSpacing(10);

            Label mediaTitle = new Label(entry.getKey().getTitle());
            mediaTitle.setFont(new Font(14));
            mediaTitle.setMaxWidth(Double.MAX_VALUE);
            mediaTitle.setAlignment(Pos.CENTER);

            mediaCell.getChildren().addAll(imageView, mediaTitle);

            gridPane.add(mediaCell, 0, rowCount);

            Label mediaPrice = new Label(CurrencyFormatter.vnd(entry.getKey().getPrice()));
            mediaPrice.setFont(new Font(14));
            mediaPrice.setAlignment(Pos.CENTER);
            mediaPrice.setMaxWidth(Double.MAX_VALUE);

            gridPane.add(mediaPrice, 1, rowCount);

            Label mediaQuantity = new Label(String.valueOf(entry.getValue()));
            mediaQuantity.setFont(new Font(14));
            mediaQuantity.setAlignment(Pos.CENTER);
            mediaQuantity.setMaxWidth(Double.MAX_VALUE);

            gridPane.add(mediaQuantity, 2, rowCount);

            Label mediaTotal = new Label(CurrencyFormatter.vnd(entry.getValue() * entry.getKey().getPrice()));
            mediaTotal.setFont(new Font(14));
            mediaTotal.setAlignment(Pos.CENTER);
            mediaTotal.setMaxWidth(Double.MAX_VALUE);

            gridPane.add(mediaTotal, 3, rowCount);

            Label mediaStock = new Label(entry.getKey().getQuantity().toString());
            mediaStock.setMaxWidth(Double.MAX_VALUE);
            mediaStock.setAlignment(Pos.CENTER);
            gridPane.add(mediaStock, 4, rowCount);

            int missingMedia = entry.getValue() - entry.getKey().getQuantity();
            missingMedia = Math.max(missingMedia, 0);

            String mediaMissingStyle = missingMedia == 0 ? "-fx-text-fill: green;" : "-fx-text-fill: red;";

            Label mediaMissing = new Label(Integer.toString(missingMedia));
            mediaMissing.setStyle(mediaMissingStyle);
            mediaMissing.setMaxWidth(Double.MAX_VALUE);
            mediaMissing.setAlignment(Pos.CENTER);

            gridPane.add(mediaMissing, 5, rowCount);

            Button removeButton = new Button("Remove");
            removeButton.setFont(new Font(14));
            removeButton.setPadding(new Insets(10, 20, 10, 20));
            removeButton.setCursor(Cursor.HAND);

            removeButton.setOnMouseClicked(e -> {
                Cart.getCart().remove(entry.getKey());
            });

            gridPane.add(removeButton, 6, rowCount);

            ++rowCount;
        }
    }
}
