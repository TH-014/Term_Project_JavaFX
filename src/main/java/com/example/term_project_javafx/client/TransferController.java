package com.example.term_project_javafx.client;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.MovieWrapper;
import com.example.term_project_javafx.util.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import com.example.term_project_javafx.backend.projectOperation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransferController implements Initializable {
    public String movieName;
    public String prodCompany;

    public static Client client;
    public Label warningLabel;
    public ChoiceBox<String> prodComCheckBox;
    public ChoiceBox<String> movNameCheckBox;

    public void setClient(Client cl)
    {
        client = cl;
    }
    public void onTransferClick(ActionEvent actionEvent) throws Exception {
        projectOperation.movieList = Client.myMovieList;
        List<Movie> movie = projectOperation.searchMovieByTitle(movieName);
        if(movie.size()>0)
        {
            MovieWrapper mwr = new MovieWrapper("transfer",movie.get(0).getProductionCompany(),prodCompany,movie.get(0));
            try {
                SocketWrapper sw = client.getSocketWrapper();
                sw.write(mwr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Client.myMovieList.remove(movie.get(0));
            System.out.println("MovieWrapper written");
            client.showHomePage();
        }
        else {
            warningLabel.setText("This movie does not exists! !! !!!");
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        client.showHomePage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prodComCheckBox.getItems().addAll(Client.movieTitleList);
        prodComCheckBox.setOnAction(this::choiceDone);
        movNameCheckBox.getItems().addAll(Client.movieNameList);
        movNameCheckBox.setOnAction(this::choiceDone2);
    }

    private void choiceDone2(ActionEvent actionEvent) {
        movieName = movNameCheckBox.getValue();
        System.out.println(movieName);
    }

    private void choiceDone(ActionEvent actionEvent) {
        prodCompany = prodComCheckBox.getValue();
        System.out.println(prodCompany);
    }
}
