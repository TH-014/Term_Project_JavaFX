package com.example.term_project_javafx.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.term_project_javafx.util.LoginDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    public Client client;
    @FXML
    public PasswordField passwordBox;
    //public static List<Movie> myMovieList;
    public Label loginLabel;
    public static List<String> movieTitleList;
    public ChoiceBox<String> logInChoiceBox;
    public static int loginStatus=0;
    public Button exitButton;
    String name;

    public void onLoginClick(ActionEvent actionEvent) throws Exception {
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
//        new ReadThreadClient(client.getSocketWrapper(), client);
        while (true)
        {
            System.out.println(loginStatus);
            if(loginStatus==1)
            {
                client.showHomePage();
                break;
            } else if (loginStatus==-1) {
                client.showAlert();
                break;
            }
        }
    }

    public void onResetClick(ActionEvent actionEvent) {
        //productionCompany.setText(null);
        passwordBox.setText(null);
    }
    void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //should be initialized
        logInChoiceBox.getItems().addAll(movieTitleList);
        logInChoiceBox.setOnAction(this::choiceDone);
    }

    public void choiceDone(ActionEvent actionEvent) {
        name = logInChoiceBox.getValue();
        System.out.println(name);
    }

    public void onExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
