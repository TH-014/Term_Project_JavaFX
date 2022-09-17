package com.example.term_project_javafx.util;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title, genre1, genre2, genre3, productionCompany;
    private int year, time, budget, revenue, profit;
    public Movie(String line)
    {
        String[] str = line.split(",");
        title = str[0];
        genre1 = str[2];
        genre2 = str[3];
        genre3 = str[4];
        productionCompany = str[6];
        year = Integer.parseInt(str[1]);
        time = Integer.parseInt(str[5]);
        budget = Integer.parseInt(str[7]);
        revenue = Integer.parseInt(str[8]);
        profit = revenue - budget;
    }

    public Movie(String title, String genre1, String genre2, String genre3, String productionCompany, int year, int time, int budget, int revenue) {
        this.title = title;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
        this.productionCompany = productionCompany;
        this.year = year;
        this.time = time;
        this.budget = budget;
        this.revenue = revenue;
        this.profit = revenue-budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre1() {
        return genre1;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public String getGenre3() {
        return genre3;
    }

    public void setGenre3(String genre3) {
        this.genre3 = genre3;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void showMovie() {
        System.out.println("\no Title: " + title +
                "\no Year of Release: " + year+
                "\no Genre: " +genre1+", "+genre2+", "+genre3+
                "\no Running Time (in minutes): " + time +
                "\no Production Company: " + productionCompany +
                "\no Budget: " + budget +
                "\no Revenue: " + revenue + "\n");
    }
}

