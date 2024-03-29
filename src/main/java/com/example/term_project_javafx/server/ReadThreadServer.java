package com.example.term_project_javafx.server;

import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.MovieWrapper;
import com.example.term_project_javafx.util.PassWrapper;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.LoginDTO;
import com.example.term_project_javafx.backend.projectOperation;
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
    public List<Movie> movieList;


    public ReadThreadServer(HashMap<String, SocketWrapper> map, HashMap<String, List<Movie>> productionCompanyMap, HashMap<String, String> credentialsMap, List<String> productionCompanyList, List<Movie> movieList, SocketWrapper socketWrapper) {
        this.clientMap = map;
        this.credentialsMap = credentialsMap;
        this.productionCompanyMap = productionCompanyMap;
        this.productionCompanyList = productionCompanyList;
        this.socketWrapper = socketWrapper;
        this.movieList = movieList;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object obj = socketWrapper.read();
                if (obj != null) {
                    String cname = obj.getClass().getName();
                    if (obj instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) obj;
                        String password = credentialsMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        socketWrapper.write(loginDTO);
                        if (loginDTO.isStatus()) {
                            clientMap.put(loginDTO.getUserName(), socketWrapper);
                            socketWrapper.write(productionCompanyMap.get(loginDTO.getUserName()));
                        }
                    }
                    else if (obj instanceof MovieWrapper) {
                        MovieWrapper mywrap = (MovieWrapper) obj;
                        if (mywrap.getCommand().equals("add")) {
                            projectOperation.movieList = this.movieList;
                            List<Movie> returned = projectOperation.searchMovieByTitle(mywrap.getMovie().getTitle());
                            if (returned.size() > 0) {
                                mywrap.setCommand("add_rejected");
                                socketWrapper.write(mywrap);
                            } else {
                                movieList.add(mywrap.getMovie());
                                List<Movie> mvList = productionCompanyMap.get(mywrap.getMovie().getProductionCompany());
                                mvList.add(mywrap.getMovie());
                                productionCompanyMap.put(mywrap.getMovie().getProductionCompany(), mvList);
                                mywrap.setCommand("added");
                                socketWrapper.write(mywrap);
                            }
                        }
                        else if (mywrap.getCommand().equals("transfer")) {
                            int index = -1;
                            for (int i = 0; i < movieList.size(); i++) {
                                if (movieList.get(i).getTitle().equalsIgnoreCase(mywrap.getMovie().getTitle())) {
                                    index = i;
                                    movieList.get(i).setProductionCompany(mywrap.getTo());
                                    break;
                                }
                            }
                            List<Movie> senderList = productionCompanyMap.get(mywrap.getFrom());
                            List<Movie> receiverList = productionCompanyMap.get(mywrap.getTo());
                            receiverList.add(mywrap.getMovie());
                            senderList.remove(mywrap.getMovie());
                            productionCompanyMap.put(mywrap.getFrom(), senderList);
                            productionCompanyMap.put(mywrap.getTo(), receiverList);
                            try {
                                SocketWrapper sw = clientMap.get(mywrap.getTo());
                                if(clientMap.get(mywrap.getTo())!=clientMap.get(mywrap.getFrom()))
                                    sw.write(mywrap.getMovie());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if (obj instanceof PassWrapper) {
                        PassWrapper passWrapper = (PassWrapper) obj;
                        if (passWrapper.getOldPass().equals(credentialsMap.get(passWrapper.getUsername()))) {
                            passWrapper.setStatus(true);
                            credentialsMap.put(passWrapper.getUsername(), passWrapper.getNewPass());
                        }
                        try {
                            socketWrapper.write(passWrapper);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}