package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RecentlistPageController implements Initializable {
    @FXML
    public TableView<Movie> table;
    public TableColumn<Movie, String> titleCol;
    public TableColumn<Movie, Integer> yearCol;
    public TableColumn<Movie, String> gen1Col;
    public TableColumn<Movie, String> gen2Col;
    public TableColumn<Movie, String> gen3Col;
    public TableColumn<Movie, Integer> lengthCol;
    public TableColumn<Movie, Integer> budgetCol;
    public TableColumn<Movie, Integer> revenueCol;
    public static Client client;

    public void setClient(Client cl) {
        client = cl;
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        client.showHomePage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        gen1Col.setCellValueFactory(new PropertyValueFactory<>("genre1"));
        gen2Col.setCellValueFactory(new PropertyValueFactory<>("genre2"));
        gen3Col.setCellValueFactory(new PropertyValueFactory<>("genre3"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        budgetCol.setCellValueFactory(new PropertyValueFactory<>("budget"));
        revenueCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));
    }

    void addInfo()
    {
        if(
                Client.myMovieList ==null)
        {
            System.out.println("Movie List is null");
        }
        else
        {
            for(Movie mv: client.recentList)
                table.getItems().add(mv);
        }
    }
}
