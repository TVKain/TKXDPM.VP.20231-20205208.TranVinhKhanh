package com.hust.aims.subsystem.banking.vnpay;

import com.hust.aims.model.order.Order;
import com.hust.aims.subsystem.banking.Banking;
import com.hust.aims.util.ScreenSwitcher;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VNPaySubsystem implements Banking {
    VNPayController vnPayController = new VNPayController();
    @Override
    public void processTransaction(Order order) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load(vnPayController.buildUrl(String.valueOf(Math.round(order.getInvoice().getTotal()))));
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED.equals(newValue)) {
                String url = webEngine.getLocation();

                vnPayController.handleResponse(order, url);
            }
        });

        ScreenSwitcher.setScreen(webView);
    }
}
