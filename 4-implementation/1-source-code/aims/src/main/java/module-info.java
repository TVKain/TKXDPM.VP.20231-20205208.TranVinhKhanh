module com.hust.aims {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.dlsc.formsfx;

    opens com.hust.aims to javafx.fxml, javafx.base, javafx.controls, com.dlsc.formsfx;
    opens com.hust.aims.controller to javafx.fxml;
    exports com.hust.aims;
}