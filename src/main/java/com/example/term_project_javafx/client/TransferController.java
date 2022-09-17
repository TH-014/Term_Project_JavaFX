package com.example.term_project_javafx.client;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.MovieWrapper;
import com.example.term_project_javafx.util.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.term_project_javafx.backend.projectOperation;

import java.io.IOException;
import java.util.List;

public class TransferController {
    public TextField movNameBox;
    public TextField proComBox;

    public String movieName;
    public String prodCompany;

    public static Client client;
    public Label warningLabel;

    public void setClient(Client cl)
    {
        client = cl;
    }
    public void onTransferClick(ActionEvent actionEvent) throws Exception {
        movieName = movNameBox.getText();
        prodCompany = proComBox.getText();
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
            //new ReadThreadClient(client.getSocketWrapper(), client);
            Client.myMovieList.remove(movie.get(0));
            System.out.println("MovieWrappwr written");
            client.showHomePage();
        }
        else {
            warningLabel.setText("This movie does not exists! !! !!!");
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        client.showHomePage();
    }
}
