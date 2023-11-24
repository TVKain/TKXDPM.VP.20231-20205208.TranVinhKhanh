package com.hust.aims.view.home;

import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.util.CurrencyFormatter;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MediaView extends VBox {
    public MediaView(Media media) {
        super();
        this.setStyle(
                "-fx-background-color: #9e9e9e;" +
                "-fx-padding: 5px;"
        );

        this.setAlignment(Pos.CENTER);

        Image image = new Image(String.valueOf(MediaView.class.getResource("/com/hust/aims/image/placeholder.jpg")));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(100);
        imageView.setFitHeight(125);

        Label title = new Label(media.getTitle());
        title.setMaxWidth(Double.MAX_VALUE);
        title.setMaxHeight(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(16));

        Label price = new Label(CurrencyFormatter.vnd(media.getPrice()));

        price.setMaxWidth(Double.MAX_VALUE);
        price.setMaxHeight(Double.MAX_VALUE);
        price.setAlignment(Pos.CENTER);
        price.setFont(new Font(14));

        Label quantity = new Label("In stock: " + media.getQuantity());
        quantity.setMaxWidth(Double.MAX_VALUE);
        quantity.setMaxHeight(Double.MAX_VALUE);
        quantity.setAlignment(Pos.CENTER);

        Spinner<Integer> quantitySpinner = new Spinner<>(1, media.getQuantity(), 0, 1);
        quantitySpinner.setMaxWidth(Double.MAX_VALUE);
        quantitySpinner.setPrefWidth(55);

        Button addButton = new Button("Add");
        addButton.setPrefWidth(USE_COMPUTED_SIZE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setCursor(Cursor.HAND);

        addButton.setOnMouseClicked(e -> {
            Cart.getCart().add(media, quantitySpinner.getValue());
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.getChildren().addAll(addButton, quantitySpinner);

        this.getChildren().addAll(imageView, title, price, quantity, hBox);
    }
}
