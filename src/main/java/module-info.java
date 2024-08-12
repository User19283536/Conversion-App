module com.edp.edp_proj {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires json.simple;
    requires java.sql;
    requires commons.validator;
    requires commons.email;
    requires com.google.common;

    opens com.edp.edp_proj to javafx.fxml;
    exports com.edp.edp_proj;
}