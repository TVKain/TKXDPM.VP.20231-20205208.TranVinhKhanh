package com.hust.aims.view.shipping;

import com.hust.aims.controller.PlaceOrderController;
import com.hust.aims.util.PopupManager;
import com.hust.aims.util.ScreenSwitcher;
import com.hust.aims.view.cart.CartView;
import com.hust.aims.view.invoice.InvoiceView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ShippingView {
    PlaceOrderController placeOrderController = new PlaceOrderController();
    @FXML
    TextField nameTextField;
    @FXML
    TextField phoneTextField;
    @FXML
    TextField addressTextField;
    @FXML
    TextArea shippingInstructionTextArea;
    @FXML
    CheckBox rushOrderCheckBox;

    @FXML
    Button confirmDeliveryButton;

    @FXML
    ComboBox<String> cityComboBox;

    @FXML
    Button backButton;

    @FXML
    void initialize() {
        cityComboBox.getItems().setAll("Ha Noi", "TP.HCM", "Bac Ninh", "Da Nang", "Can Tho");
        cityComboBox.getSelectionModel().selectFirst();

        confirmDeliveryButton.setOnMouseClicked(e -> {
            if (!placeOrderController.validateAddress(addressTextField.getText())) {
                PopupManager.showError("Address is invalid");
                return;
            }

            if (!placeOrderController.validateName(nameTextField.getText())) {
                PopupManager.showError("Name is invalid");
                return;
            }

            if (!placeOrderController.validatePhone(phoneTextField.getText())) {
                PopupManager.showError("Phone is invalid");
                return;
            }

            placeOrderController.initializeDeliveryInfo(
                    nameTextField.getText(), phoneTextField.getText(),
                    cityComboBox.getValue(), addressTextField.getText(),
                    shippingInstructionTextArea.getText(), rushOrderCheckBox.isSelected());
            placeOrderController.initializeOrder();

            ScreenSwitcher.setScreen("invoice", new InvoiceView(placeOrderController));
        });

        backButton.setOnMouseClicked(e -> {
            ScreenSwitcher.setScreen("cart", new CartView());
        });
    }


}
