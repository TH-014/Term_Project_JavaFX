package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.*;
import javafx.application.Platform;
import javafx.scene.control.Labeled;

import java.io.IOException;
import java.util.List;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    public List<Movie> myMovieList;
    private Client client;

    public ReadThreadClient(SocketWrapper socketWrapper, Client cl) {
        this.socketWrapper = socketWrapper;
        myMovieList = LoginPageController.myMovieList;
        this.client = cl;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        System.out.println("In Read Thread Client");
        while(true)
        {
            Object o;
            try {
                o = client.getSocketWrapper().read();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(o != null)
            {
                if(o instanceof Movie)
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
                        //addMovieWarning.setText("Movie Added!");
                        //addMovieWarning.setStyle("-fx-text-fill: green;");

                    }
                    else {
                        AddMovieController.labelWarning = "Already a movie exists with this name!";
                        //addMovieWarning.setText("Already a movie exists with this name!");
                    }
                }
                else if (o instanceof PassWrapper) {
                    PassWrapper passWrapper = (PassWrapper) o;
                    if(passWrapper.isStatus())
                    {
                        ChangePasswordController.serverStatus="Password successfully changed!";
                    }
                    else {
                        ChangePasswordController.serverStatus="Wrong Password !!!";
                    }
                }
            }
        }
        //            Object o = client.getSocketWrapper().read();
//            if (o != null) {
//                if (o instanceof LoginDTO) {
//                    LoginDTO loginDTO = (LoginDTO) o;
//                    System.out.println(loginDTO.getUserName());
//                    System.out.println(loginDTO.isStatus());
                    // the following is necessary to update JavaFX UI components from user created threads
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (loginDTO.isStatus()) {
//                                try {
//                                    client.showHomePage();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                client.showAlert();
//                            }
//
//                        }
//                    });
//                    for (int i=0; i<myMovieList.size(); i++)
//                    {
//                        myMovieList.get(i).showMovie();
//                    }
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//            }
        //Login Completed...
//        //finally {
////            try {
////                client.getSocketWrapper().closeConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
    }
}