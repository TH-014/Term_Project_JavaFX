package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.term_project_javafx.util.MovieWrapper;

import java.io.IOException;

public class AddMovieController {

    public TextField titleBox;
    public TextField yearBox;
    public TextField genre1Box;
    public TextField genre2Box;
    public TextField genre3Box;
    public TextField lengthBox;
    public TextField budgetBox;
    public TextField revenueBox;
    public Button addButton;
    public Label addMovieWarning;
    public static String labelWarning;

    private int year, time, budget, revenue;

    private static Client client;
    void setClient(Client c)
    {
        client = c;
    }
    public void onSubmitClick(ActionEvent actionEvent) throws IOException {
        String title = titleBox.getText();
        String genre1 = genre1Box.getText();
        String genre2 = genre2Box.getText();
        String genre3 = genre3Box.getText();
        boolean checked = true;
        try {
            year = Integer.parseInt(yearBox.getText());
            time = Integer.parseInt(lengthBox.getText());
            budget = Integer.parseInt(budgetBox.getText());
            revenue = Integer.parseInt(revenueBox.getText());
        } catch (Exception e)
        {
            checked = false;
            addMovieWarning.setText("Please complete the fileds correctly");
        }
        if(checked)
        {
            String productionCompany = Client.myMovieList.get(0).getProductionCompany();
            Movie mv = new Movie(title, genre1, genre2, genre3, productionCompany, year, time, budget, revenue);
            try {
                SocketWrapper sw = client.getSocketWrapper();
                sw.write(new MovieWrapper("add", productionCompany, productionCompany,mv));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("MovieWrapper written");
            while (true)
            {
                if(labelWarning==null) continue;
                if(labelWarning.equals("Movie Added!"))
                {
                    addMovieWarning.setText(labelWarning);
                    client.showMyMoviePage();
                    break;
                }
                else if (labelWarning.equals("Already a movie exists with this name!")) {
                    System.out.println(labelWarning);
                    addMovieWarning.setText(labelWarning);
                    titleBox.setText(null);
                    break;
                }
            }
            labelWarning=null;
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        client.showHomePage();
    }
}
