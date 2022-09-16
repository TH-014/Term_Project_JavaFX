package com.example.term_project_javafx.backend;

import com.example.term_project_javafx.util.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class makePassword {
    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "credentials.txt";
    static HashMap<String, String> passMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        List<Movie> movieList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            movieList.add(new Movie(line));
        }
        br.close();
        for(int i=0; i<movieList.size(); i++)
        {
            Movie mv = movieList.get(i);
            if(!passMap.containsKey(mv.getProductionCompany()))
            {
                strList.add(mv.getProductionCompany());
            }
            String str = mv.getProductionCompany()+","+mv.getProductionCompany().toLowerCase();
            passMap.put(mv.getProductionCompany(), str);
        }
        for(int i=0; i<strList.size(); i++)
        {
            String str = passMap.get(strList.get(i));
            bw.write(str);
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}
