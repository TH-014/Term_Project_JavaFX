package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.SocketWrapper;
import com.example.term_project_javafx.util.Movie;
import com.example.term_project_javafx.util.Message;
import java.util.Scanner;

public class Client {

    public Client(String serverAddress, int serverPort) {
        try {
            System.out.print("Enter name of the client: ");
            Scanner scanner = new Scanner(System.in);
            String clientName = scanner.nextLine();
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write(clientName);
            new ReadThreadClient(socketWrapper);
            new WriteThreadClient(socketWrapper, clientName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        new Client(serverAddress, serverPort);
    }
}


