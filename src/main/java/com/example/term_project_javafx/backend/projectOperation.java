package com.example.term_project_javafx.backend;

import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.pCompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class projectOperation {
    public static List<Movie> movieList;

    static void mainMenu() {
        System.out.println("Main Menu:\n" +
                "\t1) Search Movies\n" +
                "\t2) Search Production Companies\n" +
                "\t3) Add Movie\n" +
                "\t4) Exit System\n");
        System.out.print("Enter your choice: ");
    }

    static void movieSearchMenu() {
        System.out.println("Movie Searching Options:\n" +
                "\t1) By Movie Title\n" +
                "\t2) By Release Year \n" +
                "\t3) By Genre \n" +
                "\t4) By Production Company\n" +
                "\t5) By Running Time \n" +
                "\t6) Top 10 Movies \n" +
                "\t7) Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    static void companySearchMenu() {
        System.out.println("Production Company Searching Options:\n" +
                "\t1) Most Recent Movies \n" +
                "\t2) Movies with the Maximum Revenue\n" +
                "\t3) Total Profit\n" +
                "\t4) List of Production Companies and the Count of their Produced Movies \n" +
                "\t5) Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    public static List<Movie> searchMovieByTitle(String name) {
        List<Movie> prefList = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            Movie mv = movieList.get(i);
            if (mv.getTitle().equalsIgnoreCase(name)) {
                prefList.add(mv);
                break;
            }
        }
        return prefList;
    }

    static List<Movie> searchMovieByYear(int yr) {
        List<Movie> prefList = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            Movie mv = movieList.get(i);
            if (mv.getYear() == yr) {
                prefList.add(mv);
            }
        }
        return prefList;
    }

    static List<Movie> searchMovieByGenre(String name) {
        List<Movie> prefList = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            Movie mv = movieList.get(i);
            if (mv.getGenre1().equalsIgnoreCase(name) || mv.getGenre2().equalsIgnoreCase(name) || mv.getGenre3().equalsIgnoreCase(name)) {
                prefList.add(mv);
            }
        }
        return prefList;
    }

    static List<Movie> searchMovieByCompany(String name) {
        List<Movie> prefList = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            Movie mv = movieList.get(i);
            if (mv.getProductionCompany().equalsIgnoreCase(name)) {
                prefList.add(mv);
            }
        }
        return prefList;
    }

    static List<Movie> searchMovieByTime(int time1, int time2) {
        List<Movie> prefList = new ArrayList<>();
        for (Movie mv : movieList) {
            if (mv.getTime() >= time1 && mv.getTime() <= time2) {
                prefList.add(mv);
            }
        }
        return prefList;
    }

    static List<Movie> searchTop10Movie() {
        List<Movie> prefList = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            int max = -999999999, maxid = -1;
            boolean skip;
            for (int i = 0; i < movieList.size(); i++) {
                skip = false;
                for (int k = 0; k < index.size(); k++) {
                    if (i == index.get(k)) {
                        skip = true;
                        break;
                    }
                }
                if (skip)
                    continue;

                Movie mv = movieList.get(i);
                if (max < mv.getProfit()) {
                    max = mv.getProfit();
                    maxid = i;
                }
            }
            if (maxid >= 0) {
                index.add(maxid);
                prefList.add(movieList.get(maxid));
            }
        }
        return prefList;
    }

    static void searchMovie() {
        Scanner cin = new Scanner(System.in);
        Scanner cstr = new Scanner(System.in);
        boolean mm = true;
        List<Movie> prefList = new ArrayList<>();
        while (mm) {
            movieSearchMenu();
            int op = cin.nextInt();
            switch (op) {
                case 1:
                    System.out.print("Enter a movie title: ");
                    String name = cstr.nextLine();
                    prefList = searchMovieByTitle(name);
                    if (prefList.size() == 0)
                        System.out.println("No such movie with this name");
                    break;
                case 2:
                    System.out.print("Enter a releasing year: ");
                    int yr = cin.nextInt();
                    prefList = searchMovieByYear(yr);
                    if (prefList.size() == 0)
                        System.out.println("No such movie with this  release year");
                    break;
                case 3:
                    System.out.print("Enter a movie genre: ");
                    String gen = cstr.nextLine();
                    prefList = searchMovieByGenre(gen);
                    if (prefList.size() == 0)
                        System.out.println("No such movie with this genre");
                    break;
                case 4:
                    System.out.print("Enter a production company name: ");
                    String cmp = cstr.nextLine();
                    prefList = searchMovieByCompany(cmp);
                    if (prefList.size() == 0)
                        System.out.println("No such movie with this production company");
                    break;
                case 5:
                    System.out.print("Enter minimum and maximum length: ");
                    int time1 = cin.nextInt();
                    int time2 = cin.nextInt();
                    prefList = searchMovieByTime(time1, time2);
                    if (prefList.size() == 0)
                        System.out.println("No such movie with this running time range");
                    break;
                case 6:
                    prefList = searchTop10Movie();
                    break;
                case 7:
                    mm = false;
                    break;
                default:
                    System.out.println("Wrong input command !!!");
                    break;
            }
            for (Movie movie : prefList) {
                movie.showMovie();
            }
        }
    }

    public static List<Movie> searchMovieByCompanyRecentRelease(String pcname) {
        List<Movie> prefList = new ArrayList<>();
        int yr = 0;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(pcname) && movieList.get(i).getYear() > yr) {
                yr = movieList.get(i).getYear();
            }
        }
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(pcname) && movieList.get(i).getYear() == yr) {
                prefList.add(movieList.get(i));
            }
        }
        return prefList;
    }

    public static List<Movie> searchMovieByCompanyRevenue(String pcname) {
        List<Movie> prefList = new ArrayList<>();
        int rev = -999999999;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(pcname) && movieList.get(i).getRevenue() > rev) {
                rev = movieList.get(i).getRevenue();
            }
        }
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(pcname) && movieList.get(i).getRevenue() == rev) {
                prefList.add(movieList.get(i));
            }
        }
        return prefList;
    }

    public static long pcsProfit(String pcname) {
        long prof = 0;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(pcname)) {
                prof = prof + movieList.get(i).getProfit();
            }
        }
        return prof;
    }

    static List<pCompany> pcsCount() {
        List<pCompany> pCompanyList = new ArrayList<>();
        int pcin;
        for (int i = 0; i < movieList.size(); i++) {
            pcin = -1;
            for (int k = 0; k < pCompanyList.size(); k++) {
                if (movieList.get(i).getProductionCompany().equalsIgnoreCase(pCompanyList.get(k).getName())) {
                    pcin = k;
                    break;
                }
            }
            if (pcin > -1) {
                pCompanyList.get(pcin).setmCount(pCompanyList.get(pcin).getmCount() + 1);
            } else {
                pCompany pc = new pCompany();
                pc.setName(movieList.get(i).getProductionCompany());
                pc.setmCount(1);
                pCompanyList.add(pc);
            }
        }
        return pCompanyList;
    }

    static void searchPC() {
        Scanner cin = new Scanner(System.in);
        Scanner cstr = new Scanner(System.in);
        boolean mm = true;
        List<Movie> prefList = new ArrayList<>();
        List<pCompany> pCompanyList;
        while (mm) {
            companySearchMenu();
            int op = cin.nextInt();
            switch (op) {
                case 1:
                    System.out.print("Enter a production company: ");
                    String pcname = cstr.nextLine();
                    prefList = searchMovieByCompanyRecentRelease(pcname);
                    break;
                case 2:
                    System.out.print("Enter a production company: ");
                    pcname = cstr.nextLine();
                    prefList = searchMovieByCompanyRevenue(pcname);
                    break;
                case 3:
                    System.out.print("Enter a production company: ");
                    pcname = cstr.nextLine();
                    long p = pcsProfit(pcname);
                    if (p == -999999999)
                        System.out.println("No such movie with this production company");
                    else
                        System.out.println("Total Profit: " + p);
                    break;
                case 4:
                    pCompanyList = pcsCount();
                    for (pCompany x : pCompanyList)
                        x.showCompany();
                    break;
                case 5:
                    mm = false;
                    break;
                default:
                    System.out.println("Wrong input command");
                    break;
            }
            if (op < 3 && prefList.size() == 0)
                System.out.println("No such movie with this production company");
            else if (op < 3) {
                for (Movie movie : prefList) {
                    movie.showMovie();
                }
            }
        }
    }

    static void addMovie() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter a movie details: ");
        String line = cin.nextLine();
        Movie mv = new Movie(line);
        List<Movie> prefList = searchMovieByTitle(mv.getTitle());
        if (prefList.size() == 0)
            movieList.add(mv);
        else
            System.out.println("A movie with given name already exists!");
    }

    static void perform(List<Movie> mList) {
        movieList = mList;
        Scanner cin = new Scanner(System.in);
        boolean mm = true;
        while (mm) {
            mainMenu();
            int op = cin.nextInt();
            switch (op) {
                case 1:
                    searchMovie();
                    break;
                case 2:
                    searchPC();
                    break;
                case 3:
                    addMovie();
                    break;
                case 4:
                    mm = false;
                    break;
                default:
                    System.out.println("Wrong input command");
                    break;
            }
        }
    }
}
