package com.example.term_project_javafx.client;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
    public TextField productionCompany;
    public PasswordField passwordBox;

    public void onLoginClick(ActionEvent actionEvent) {
        String name = productionCompany.getText();
        String passwod = passwordBox.getText();
        System.out.println(name);
        System.out.println(passwod);
    }
}
