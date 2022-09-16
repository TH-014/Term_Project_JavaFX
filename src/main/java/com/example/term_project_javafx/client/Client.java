package com.example.term_project_javafx.client;

import com.example.term_project_javafx.HelloApplication;
import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.Message;
import com.example.term_project_javafx.util.LoginDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Client extends Application {
    private Stage stage;
    private SocketWrapper socketWrapper;

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }
    public static void main(String args[]) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }
    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper = new SocketWrapper(serverAddress, serverPort);
        //new ReadThreadClient(this.socketWrapper, this);
    }

    public void showLoginPage() throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        // Loading the controller
        LoginPageController controller = fxmlLoader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Movie Database: Login");
        stage.setScene(scene);
        stage.show();
    }
    public void showHomePage() throws Exception {

//        System.out.println("Test 1");
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-page.fxml"));
//        System.out.println("Test 2");
        //loader.setLocation(getClass().getResource("home-page.fxml"));
//        System.out.println("Test 3");
        Parent root = loader.load();//illegal state exception
//        System.out.println("Test 4");

        // Loading the controller
        HomePageController controller = loader.getController();
//        System.out.println("Test 5");
        controller.setClient(this);
//        System.out.println("Test 6");

        // Set the primary stage
        stage.setTitle("Movie Database: Home Page");
//        System.out.println("Test 7");
        stage.setScene(new Scene(root, 900, 600));
//        System.out.println("Test 8");
        stage.show();
//        System.out.println("Test 9");
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
}


