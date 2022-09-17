package com.example.term_project_javafx.util;

import java.io.Serializable;

public class MovieWrapper implements Serializable {
    private String command;
    private String from;
    private String to;
    private Movie movie;

    public MovieWrapper(String command, String from, String to, Movie movie) {
        this.command = command;
        this.from = from;
        this.to = to;
        this.movie = movie;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
