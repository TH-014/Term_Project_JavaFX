package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.*;
import javafx.application.Platform;

import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Client client;

    public ReadThreadClient(SocketWrapper socketWrapper, Client client) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        this.client = client;
        thr.start();
    }

    public void run() {
        try {
            Object o = client.getSocketWrapper().read();
            if (o != null) {
                if (o instanceof LoginDTO) {
                    LoginDTO loginDTO = (LoginDTO) o;
                    System.out.println(loginDTO.getUserName());
                    System.out.println(loginDTO.isStatus());
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
                }
            }
            //Login Completed...
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                client.getSocketWrapper().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



