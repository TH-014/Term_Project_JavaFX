package com.example.term_project_javafx.server;

import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.LoginDTO;
import com.example.term_project_javafx.util.Message;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    public HashMap<String, SocketWrapper> clientMap;
    public HashMap<String, List<Movie>> productionCompanyMap;
    public HashMap<String, String> credentialsMap;
    public List<String> productionCompanyList;


    public ReadThreadServer(HashMap<String, SocketWrapper> map, HashMap<String, List<Movie>> productionCompanyMap, HashMap<String, String> credentialsMap, List<String> productionCompanyList, SocketWrapper socketWrapper) {
        this.clientMap = map;
        this.credentialsMap = credentialsMap;
        this.productionCompanyMap = productionCompanyMap;
        this.productionCompanyList = productionCompanyList;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = credentialsMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        socketWrapper.write(loginDTO);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true){}
    }
}



