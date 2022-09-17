package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.*;
import javafx.application.Platform;

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
        //this.client = client;
        thr.start();
    }

    public void run() {
        //            System.out.println("In Read Thread Client");
//            Object o = client.getSocketWrapper().read();
//            if(o != null)
//            {
//                if(o instanceof MovieWrapper)
//                {
//                    MovieWrapper mywrap = (MovieWrapper) o;
//                    if(mywrap.getCommand().equals("added"))
//                    {
//                        client.myMovieList.add(mywrap.getMovie());
//                        //client.addMovCheck();
////                        System.out.println("Run Later");
////                        client.addController.addMovieWarning.setText("Movie Added!");
////                        client.addController.addMovieWarning.setStyle("-fx-text-fill: green;");
//
//                    }
//                    else {
//                        //AddMovieController controller = new AddMovieController();
//                        //AddMovieController.labelWarning = new String("Already a movie exists with this name!");
//                        client.addController.addMovieWarning.setText("Already a movie exists with this name!");
//                    }
//                }
//            }
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