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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Application {
    private static Stage stage;
    private SocketWrapper socketWrapper;
    public  static List<Movie> myMovieList;
    public static List<String> movieTitleList;
    public static List<String> movieNameList;
    public  List<Movie> recentList;
    public  List<Movie> revenueList;
    public String serverIp = "127.0.0.1";
    public int serverPort = 33333;
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
        connectToServer(serverIp, serverPort);
    }
    public void connectToServer(String serverAddress, int serverPort) throws IOException {
        try{
            socketWrapper = new SocketWrapper(serverAddress, serverPort);
            loadProdComArray();
            showLoginPage();
        } catch (Exception e)
        {
            System.out.println(e);
            ServerConnectionController.client = this;
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("server-connection.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);

            // Set the primary stage
            stage.setTitle("Movie Database: Server Connection");
            stage.setScene(scene);
            stage.show();
        }
    }
    public void loadProdComArray()
    {
        try {
            Object obj = socketWrapper.read();
            if(obj instanceof List)
            {
                movieTitleList = (List<String>) obj;
            }
            LoginPageController.movieTitleList = movieTitleList;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
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

        myMovieList = LoginPageController.myMovieList;
        movieNameList = new ArrayList<>();
        for(Movie mv: myMovieList)
        {
            movieNameList.add(mv.getTitle());
        }
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-page.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomePageController controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
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
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
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
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
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
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
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
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
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
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
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
        stage.setTitle("Movie Database: "+myMovieList.get(0).getProductionCompany());
        stage.setScene(scene);
        stage.show();
    }

}