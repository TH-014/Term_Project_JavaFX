module com.example.term_project_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.term_project_javafx to javafx.fxml;
    exports com.example.term_project_javafx;
}