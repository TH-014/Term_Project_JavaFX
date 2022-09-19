package com.example.term_project_javafx.server;

import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.Movie;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable{
    public List<Movie> movieList;
    public HashMap<String, List<Movie>> productionCompanyMap;
    public HashMap<String, String> credentialsMap;
    public List<String> productionCompanyList;
    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String CREDENTIALS_INPUT = "credentials.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";

    Server() throws IOException {
        readFiles();
        clientMap = new HashMap<>();
        Thread exitThread = new Thread(this);
        exitThread.start();
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
        try {
            socketWrapper.write(productionCompanyList);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        new ReadThreadServer(clientMap, productionCompanyMap, credentialsMap, productionCompanyList, movieList, socketWrapper);
    }

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
        br.close();
    }
    public static void main(String args[]) throws IOException {
        System.out.println("Server Started...");
        new Server();
    }
    public void writeFiles() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(int i=0; i<movieList.size(); i++)
        {
            Movie mv = movieList.get(i);
            String str = mv.getTitle()+","+mv.getYear()+","+mv.getGenre1()+","+mv.getGenre2()+","+mv.getGenre3()+","+mv.getTime()+","+mv.getProductionCompany()+","+mv.getBudget()+","+mv.getRevenue()+"";
            bw.write(str);
            bw.write(System.lineSeparator());
        }
        bw.close();
        BufferedWriter crw = new BufferedWriter(new FileWriter(CREDENTIALS_INPUT));
        for(int i=0; i<productionCompanyList.size();i++)
        {
            String line = productionCompanyList.get(i)+","+credentialsMap.get(productionCompanyList.get(i))+"";
            crw.write(line);
            crw.write(System.lineSeparator());
        }
        crw.close();
    }
    @Override
    public void run() {
        System.out.println("Write STOP to exit the server");
        Scanner scn = new Scanner(System.in);
        String command = scn.nextLine();
        if(command.equalsIgnoreCase("STOP"))
        {
            try {
                writeFiles();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }
}
