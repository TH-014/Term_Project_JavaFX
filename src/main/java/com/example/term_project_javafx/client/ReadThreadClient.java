package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.*;
import javafx.application.Platform;
import javafx.scene.control.Labeled;

import java.io.IOException;
import java.util.List;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    //public List<Movie> myMovieList;
    private Client client;

    public ReadThreadClient(SocketWrapper socketWrapper, Client cl) {
        this.socketWrapper = socketWrapper;
        //myMovieList = LoginPageController.myMovieList;
        this.client = cl;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        System.out.println("In Read Thread Client");
        while(true)
        {
            Object o = null;
            try {
                o = client.getSocketWrapper().read();
                System.out.println(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e)
            {
                System.out.println(e);
            }
            if(o != null)
            {
                if (o instanceof LoginDTO) {
                    LoginDTO loginDTO = (LoginDTO) o;
                    System.out.println(loginDTO.getUserName());
                    System.out.println(loginDTO.isStatus());
                    if(loginDTO.isStatus())
                    {
                        Object myobj;
                        try {
                            myobj = client.getSocketWrapper().read();
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        if(myobj instanceof List)
                        {
                            Client.myMovieList = (List<Movie>) myobj;
                            System.out.println("Received Movie List");
                            LoginPageController.loginStatus=1;
                            System.out.println("Loading Home page"); //Log in issues
                        }
                    }
                    else
                    {
                        client.showAlert();
                        LoginPageController.loginStatus=-1;
                    }
                }
                else if(o instanceof Movie)
                {
                    Movie myMov = (Movie) o;
                    Client.myMovieList.add(myMov);
                    Client.myMovieList.get(Client.myMovieList.size()-1).showMovie();
                    MyMoviesController.transferDitected=true;
                    //MyMoviesController.myMovieList.add(myMov);
                }
                else if (o instanceof  MovieWrapper) {
                    MovieWrapper mywrap = (MovieWrapper) o;
                    if(mywrap.getCommand().equals("added"))
                    {
                        Client.myMovieList.add(mywrap.getMovie());
                        AddMovieController.labelWarning = "Movie Added!";

                    }
                    else {
                        AddMovieController.labelWarning = "Already a movie exists with this name!";
                    }
                }
                else if (o instanceof PassWrapper) {
                    PassWrapper passWrapper = (PassWrapper) o;
                    if(passWrapper.isStatus())
                    {
                        ChangePasswordController.serverStatus="Password changed successfully!";
                    }
                    else {
                        ChangePasswordController.serverStatus="Wrong Password !!!";
                    }
                }
            }
            //System.out.println(login);
        }
        //System.out.println("Read Thread Client Closed");
    }
}