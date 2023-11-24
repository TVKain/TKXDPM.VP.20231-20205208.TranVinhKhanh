package com.hust.aims.view.invoice;

import com.hust.aims.controller.PlaceOrderController;
import com.hust.aims.controller.PlaceRushOrderController;
import com.hust.aims.model.Cart;
import com.hust.aims.model.media.Media;
import com.hust.aims.util.CurrencyFormatter;
import com.hust.aims.util.PopupManager;
import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.shipping.ShippingView;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Map;

public class InvoiceView {
    private final PlaceOrderController placeOrderController;
    private final PlaceRushOrderController placeRushOrderController;

    @FXML
    Label nameLabel;
    @FXML
    Label phoneLabel;
    @FXML
    Label cityLabel;
    @FXML
    Label addressLabel;
    @FXML
    Label shippingLabel;
    @FXML
    Label shippingFeeLabel;
    @FXML
    Label subtotalLabel;
    @FXML
    Label totalLabel;

    @FXML
    Label rushOrderLabel;
    @FXML
    Button backButton;

    @FXML
    Button confirmOrderButton;

    @FXML
    GridPane gridPane;

    public InvoiceView(PlaceOrderController placeOrderController) {
        this.placeOrderController = placeOrderController;
        this.placeRushOrderController = new PlaceRushOrderController(placeOrderController.getOrder());

        if (placeOrderController.getOrder().getDeliveryInfo().isRushOrder()) {
            placeRushOrderController.updateShippingFee();
        }
    }

    @FXML
    public void initialize() {
        setUpLabel();
        setUpGridPane();
        setUpBackButton();
        setUpConfirmOrderButton();
    }

    public void setUpLabel() {
        nameLabel.setText(placeOrderController.getOrder().getDeliveryInfo().getName());
        phoneLabel.setText(placeOrderController.getOrder().getDeliveryInfo().getPhone());
        cityLabel.setText(placeOrderController.getOrder().getDeliveryInfo().getCity());
        addressLabel.setText(placeOrderController.getOrder().getDeliveryInfo().getAddress());
        shippingLabel.setText(placeOrderController.getOrder().getDeliveryInfo().getShippingInstruction());
        rushOrderLabel.setText(placeOrderController.getOrder().getDeliveryInfo().isRushOrder() ? "Yes" : "No");

        Double subtotal = placeOrderController.getOrder().getInvoice().getMediaFee() * (100 + Cart.VAT) / 100;
        Double shippingFee = placeOrderController.getOrder().getInvoice().getShippingFee();
        Double total = placeOrderController.getOrder().getInvoice().getTotal();

        subtotalLabel.setText(CurrencyFormatter.vnd(subtotal));
        shippingFeeLabel.setText(CurrencyFormatter.vnd(shippingFee));
        totalLabel.setText(CurrencyFormatter.vnd(total));
    }

    public void setUpBackButton() {
        backButton.setOnMouseClicked(e -> ScreenSwitcher.setScreen("shipping", new ShippingView()));
    }

    public void setUpConfirmOrderButton() {
        confirmOrderButton.setOnMouseClicked(e -> {
            if (placeOrderController.getOrder().getDeliveryInfo().isRushOrder()) {
                if (!placeRushOrderController
                        .validateRushOrderAddress(placeOrderController.getOrder().getDeliveryInfo().getCity())) {
                    PopupManager.showError("City not supported for rush delivery");
                    return;
                }

                if (!placeRushOrderController.validateRushOrderMediaSupport()) {
                    PopupManager.showError("No media has support for rush delivery");
                    return;
                }
            }

            System.out.println("Processing order");

            placeOrderController.redirectToPayment();
        });
    }

    public void setUpGridPane() {
        int rowCount = 1;
        for (Map.Entry<Media, Integer> entry : Cart.getCart().getMediaMap().entrySet()) {
            ArrayList<Label> row = getLabels(entry);

            for (int i = 0; i < row.size(); ++i) {
                gridPane.add(row.get(i), i, rowCount);
            }

            ++rowCount;
        }
    }

    private static ArrayList<Label> getLabels(Map.Entry<Media, Integer> entry) {
        Media media = entry.getKey();

        Label title = new Label(media.getTitle());
        Label quantity = new Label(entry.getValue().toString());
        Label price = new Label(CurrencyFormatter.vnd(media.getPrice()));
        Label total = new Label(CurrencyFormatter.vnd(entry.getValue() * media.getPrice()));
        Label rushOrder = new Label(media.getSupportRushOrder() ? "Yes" : "No");

        ArrayList<Label> row = new ArrayList<>();
        row.add(title);
        row.add(quantity);
        row.add(price);
        row.add(total);
        row.add(rushOrder);

        for (Label label : row) {
            label.setAlignment(Pos.CENTER);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setFont(new Font(14));
        }

        return row;
    }
}
