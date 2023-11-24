module com.hust.aims {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.dlsc.formsfx;
    requires org.xerial.sqlitejdbc;
    requires servlet.api;

    opens com.hust.aims to javafx.fxml, javafx.base, javafx.controls;
    opens com.hust.aims.controller to javafx.fxml;
    opens com.hust.aims.view.home to javafx.fxml, javafx.controls, javafx.base;
    opens com.hust.aims.view.cart to javafx.fxml;
    opens com.hust.aims.view.shipping to javafx.fxml;
    opens com.hust.aims.view.invoice to javafx.fxml;
    opens com.hust.aims.view.payment to javafx.fxml;

    exports com.hust.aims;
    exports com.hust.aims.util;
    opens com.hust.aims.util to com.dlsc.formsfx, javafx.base, javafx.controls, javafx.fxml;
}