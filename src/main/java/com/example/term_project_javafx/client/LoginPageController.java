package com.example.term_project_javafx.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.term_project_javafx.util.LoginDTO;

import java.io.IOException;

public class LoginPageController {
    public Client client;
    @FXML
    public TextField productionCompany;
    @FXML
    public PasswordField passwordBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;

    public void onLoginClick(ActionEvent actionEvent) throws Exception {
        String name = productionCompany.getText();
        String password = passwordBox.getText();
        System.out.println(name);
        System.out.println(password);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(name);
        loginDTO.setPassword(password);
        try {
            client.getSocketWrapper().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object o = client.getSocketWrapper().read();
            if (o != null) {
                if (o instanceof LoginDTO) {
                    loginDTO = (LoginDTO) o;
                    System.out.println(loginDTO.getUserName());
                    System.out.println(loginDTO.isStatus());
                    if(loginDTO.isStatus())
                    {
                        System.out.println("Loading Home page");
                        client.showHomePage();
                    }
                    else
                    {
                        client.showAlert();
                    }
                }
            }
            //Login Completed...
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                client.getSocketWrapper().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onResetClick(ActionEvent actionEvent) {
        productionCompany.setText(null);
        passwordBox.setText(null);
    }
    void setClient(Client client) {
        this.client = client;
    }
}
