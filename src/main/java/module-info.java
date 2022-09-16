module com.example.term_project_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.term_project_javafx to javafx.fxml;
    exports com.example.term_project_javafx;
    exports com.example.term_project_javafx.client;
    exports com.example.term_project_javafx.util;
    opens com.example.term_project_javafx.client to javafx.fxml;
}