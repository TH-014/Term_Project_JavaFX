package com.example.term_project_javafx.client;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HomePageController {
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void onMyMoviesClick(ActionEvent actionEvent) throws IOException {
        client.showMyMoviePage();
//        MyMoviesController myMovCon = new MyMoviesController();
//        myMovCon.addInfo();
    }

    public void onMostRecentClick(ActionEvent actionEvent) throws IOException {
        client.showRecentMoviePage();
    }

    public void onMaximumRevenueClick(ActionEvent actionEvent) throws IOException {
        client.showMaxRevenuePage();
    }

    public void onProfitClick(ActionEvent actionEvent) throws IOException {
        client.showProfit();
    }

    public void onAddMovieClick(ActionEvent actionEvent) throws IOException {
        client.showAddMoviePage();
    }

    public void onTransferMovieClick(ActionEvent actionEvent) {
    }

    public void onExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
