package com.hust.aims.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupManager {
    public static void showError(String message) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox parent = new VBox();
        parent.setMaxWidth(Double.MAX_VALUE);
        parent.setMaxHeight(Double.MAX_VALUE);
        parent.setPadding(new Insets(10, 20, 10, 20));
        parent.setSpacing(32);
        parent.setAlignment(Pos.CENTER);

        Label label = new Label("Error: " + message);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        label.setStyle(
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: red;");

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-font-size: 16px");
        closeButton.setAlignment(Pos.CENTER);
        closeButton.setOnMouseClicked(e -> {
            stage.close();
        });
        closeButton.setCursor(Cursor.HAND);

        parent.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(200);
        stage.setResizable(false);
        stage.show();
    }
}
