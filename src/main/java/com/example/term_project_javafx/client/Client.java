package com.example.term_project_javafx.client;

import com.example.term_project_javafx.HelloApplication;
import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.MovieWrapper;
import com.example.term_project_javafx.backend.projectOperation;
import com.example.term_project_javafx.util.Message;
import com.example.term_project_javafx.util.LoginDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Client extends Application {
    private static Stage stage;
    private SocketWrapper socketWrapper;
    public  static List<Movie> myMovieList;
    public  List<Movie> recentList;
    public  List<Movie> revenueList;
    public MovieWrapper movieWrapper;

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
        myMovieList = LoginPageController.myMovieList;
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
//        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("my-movies.fxml"));
//        MyMoviesController controller1 = fxmlLoader1.getController();
//        controller1.setClient(this);
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

    public void showMyMoviePage() throws IOException {
        //MyMoviesController.myMovieList = myMovieList;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("my-movies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        // Loading the controller
        MyMoviesController controller = fxmlLoader.getController();
        controller.setClient(this);
        controller.addInfo();

        // Set the primary stage
        stage.setTitle("Movie Database: My Movies");
        stage.setScene(scene);
        stage.show();
    }
    public void showRecentMoviePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("recentlist-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        projectOperation.movieList = myMovieList;
        recentList = projectOperation.searchMovieByCompanyRecentRelease(myMovieList.get(0).getProductionCompany());
        // Loading the controller
        RecentlistPageController controller = fxmlLoader.getController();
        controller.setClient(this);
        controller.addInfo();

        // Set the primary stage
        stage.setTitle("Movie Database: Recent Movies");
        stage.setScene(scene);
        stage.show();
    }
    public void showMaxRevenuePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("max-revenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        projectOperation.movieList = myMovieList;
        revenueList = projectOperation.searchMovieByCompanyRevenue(myMovieList.get(0).getProductionCompany());
        // Loading the controller
        MaxRevenueController controller = fxmlLoader.getController();
        controller.setClient(this);
        controller.addInfo();

        // Set the primary stage
        stage.setTitle("Movie Database: Maximum Revenue");
        stage.setScene(scene);
        stage.show();
    }
    public void showProfit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("total-profit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        projectOperation.movieList = myMovieList;
        long profit;
        profit = projectOperation.pcsProfit(myMovieList.get(0).getProductionCompany());
        // Loading the controller
        TotalProfitController controller = fxmlLoader.getController();
        TotalProfitController.setClient(this);
        controller.setProfit(profit);

        // Set the primary stage
        stage.setTitle("Movie Database: Total Profit");
        stage.setScene(scene);
        stage.show();
    }
    public AddMovieController addController;
    public void showAddMoviePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-movie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        // Loading the controller
        addController = fxmlLoader.getController();
        addController.setClient(this);
        //socketWrapper.write("TRY");
        //controller.addMovieWarning.setText(AddMovieController.labelWarning);

        // Set the primary stage
        stage.setTitle("Movie Database: Add Movie");
        stage.setScene(scene);
        stage.show();
    }
    public void showTransferPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("transfer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        // Loading the controller
        TransferController controller = fxmlLoader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Movie Database: Transfer Page");
        stage.setScene(scene);
        stage.show();
    }

}


