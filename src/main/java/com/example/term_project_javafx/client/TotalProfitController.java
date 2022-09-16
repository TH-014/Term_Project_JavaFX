package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TotalProfitController {
    @FXML
    public TextField profitBox;
    public static Client client;
    //public long profit;

    public static void setClient(Client cl) {
        client = cl;
    }

    public void setProfit(long profit) {
        //this.profit = profit;
        profitBox.setText(profit+"");
        profitBox.setStyle("-fx-text-fill: #440cdf; -fx-font-size: 24px;");
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        client.showHomePage();
    }

}
