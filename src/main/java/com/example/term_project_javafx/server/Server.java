package com.example.term_project_javafx.server;

import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.LoginDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public List<Movie> movieList;
    public HashMap<String, List<Movie>> productionCompanyMap;
    public HashMap<String, String> credentialsMap;
    public List<String> productionCompanyList;
    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;

    Server() throws IOException {
        readFiles();
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        new ReadThreadServer(clientMap, productionCompanyMap, credentialsMap, productionCompanyList, socketWrapper);
    }

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String CREDENTIALS_INPUT = "credentials.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";

    public void readFiles() throws IOException {
        movieList = new ArrayList<>();
        productionCompanyMap = new HashMap<>();
        credentialsMap = new HashMap<>();
        productionCompanyList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            Movie mv = new Movie(line);
            if(!productionCompanyMap.containsKey(mv.getProductionCompany()))
            {
                productionCompanyList.add(mv.getProductionCompany());
                productionCompanyMap.put(mv.getProductionCompany(), new ArrayList<>());
            }
            movieList.add(mv);
            List<Movie> mvList = productionCompanyMap.get(mv.getProductionCompany());
            mvList.add(mv);
            productionCompanyMap.put(mv.getProductionCompany(), mvList);
        }
        System.out.println("Movie loading complete...");
        BufferedReader cr = new BufferedReader(new FileReader(CREDENTIALS_INPUT));
        while (true) {
            String line = cr.readLine();
            if (line == null) break;
            String[] str = line.split(",");
            credentialsMap.put(str[0],str[1]);
        }
        System.out.println("Credentials Loading Complete...");
//        for(int i=0;i<productionCompanyList.size(); i++)
//        {
//            for(int j=0; j<productionCompanyMap.get(productionCompanyList.get(i)).size();j++)
//            {
//                productionCompanyMap.get(productionCompanyList.get(i)).get(j).showMovie();
//            }
//        }
        br.close();
    }
    public static void main(String args[]) throws IOException {
        System.out.println("Server Started...");
        new Server();
    }
}
