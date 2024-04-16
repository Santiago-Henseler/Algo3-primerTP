module com.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires jdk.xml.dom;

    opens com.main to javafx.fxml;
    exports com.main;
}
