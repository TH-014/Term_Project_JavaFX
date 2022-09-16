package com.example.term_project_javafx.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.pCompany;

public class projectMain {
    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";

    public static void main(String[] args) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        HashMap<String, List<Movie>> productionCompanyMap = new HashMap<>();
        List<String> productionCompanyList = new ArrayList<>();
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
//        for(int i=0;i<productionCompanyList.size(); i++)
//        {
//            for(int j=0; j<productionCompanyMap.get(productionCompanyList.get(i)).size();j++)
//            {
//                productionCompanyMap.get(productionCompanyList.get(i)).get(j).showMovie();
//            }
//        }
        br.close();
        projectOperation.perform(movieList);
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(int i=0; i<movieList.size(); i++)
        {
            Movie mv = movieList.get(i);
            String str = mv.getTitle()+","+mv.getYear()+","+mv.getGenre1()+","+mv.getGenre2()+","+mv.getGenre3()+","+mv.getTime()+","+mv.getProductionCompany()+","+mv.getBudget()+","+mv.getRevenue()+"";
            bw.write(str);
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}

