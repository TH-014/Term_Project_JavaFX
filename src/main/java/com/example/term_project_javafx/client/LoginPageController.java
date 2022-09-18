package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.term_project_javafx.util.LoginDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    public Client client;
    @FXML
    public PasswordField passwordBox;
    public static List<Movie> myMovieList;
    public Label loginLabel;
    public static List<String> movieTitleList;
    public ChoiceBox<String> logInChoiceBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;
    String name;

    public void onLoginClick(ActionEvent actionEvent) {
        //String name = productionCompany.getText();
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
                        Object myobj = client.getSocketWrapper().read();
                        if(myobj instanceof List)
                        {
                            myMovieList = (List<Movie>) myobj;
                            System.out.println("Received Movie List");
                        }
                        System.out.println("Loading Home page");
                        client.showHomePage();
                        new ReadThreadClient(client.getSocketWrapper(), client);
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
        }
//        finally {
//            try {
//                client.getSocketWrapper().closeConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        //}
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
