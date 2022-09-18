package com.example.term_project_javafx.client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ServerConnectionController {
    public TextField serverIPBox;
    public TextField serverPortBox;
    public String serverIp;
    public int serverPort;
    public static Client client;

    public void onConnectClick(ActionEvent actionEvent) throws IOException {
        serverIp = serverIPBox.getText();
        try{
            serverPort = Integer.parseInt(serverPortBox.getText());
            client.connectToServer(serverIp, serverPort);
        } catch (Exception e)
        {
            serverPortBox.setText(null);
        }
    }
}
