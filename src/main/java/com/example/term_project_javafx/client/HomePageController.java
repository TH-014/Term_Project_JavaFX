package com.example.term_project_javafx.client;

import javafx.event.ActionEvent;

public class HomePageController {
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void onMyMoviesClick(ActionEvent actionEvent) {
    }

    public void onMostRecentClick(ActionEvent actionEvent) {
    }

    public void onMaximumRevenueClick(ActionEvent actionEvent) {
    }

    public void onProfitClick(ActionEvent actionEvent) {
    }

    public void onAddMovieClick(ActionEvent actionEvent) {
    }

    public void onTransferMovieClick(ActionEvent actionEvent) {
    }

    public void onExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
