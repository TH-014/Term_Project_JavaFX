package com.example.term_project_javafx.client;

import com.example.term_project_javafx.util.PassWrapper;
import com.example.term_project_javafx.util.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ChangePasswordController {
    public PasswordField oldPassBox;
    public PasswordField newPassBox;
    public PasswordField renewPassBox;
    public Label warningBox;
    public static String serverStatus=" ";
    public static Client client;
    public static SocketWrapper socketWrapper;

    public String oldPass;
    public String newPass;
    public String renewPass;

    public void onSubmitClick(ActionEvent actionEvent) throws IOException {
        oldPass = oldPassBox.getText();
        newPass = newPassBox.getText();
        renewPass = renewPassBox.getText();
        boolean connection=true;
        if(!newPass.equals(renewPass))
        {
            warningBox.setText("You must type the same new password twice.");
            renewPassBox.setText(null);
            newPassBox.setText(null);
        }
        else
        {
            PassWrapper passWrapper = new PassWrapper(Client.myMovieList.get(0).getProductionCompany(),oldPass,newPass);
            try{
                socketWrapper.write(passWrapper);
            }
            catch (Exception e)
            {
                System.out.println(e);
                connection=false;
                client.connectToServer("127.0.0.1",33333);
            }
            while (connection)
            {
                if(serverStatus==null) continue;
                if(serverStatus.equals("Password successfully changed!"))
                {
                    warningBox.setText(serverStatus);
                    warningBox.setTextFill(Color.LIGHTGREEN);
                    serverStatus = " ";
                    oldPassBox.setText(null);
                    newPassBox.setText(null);
                    renewPassBox.setText(null);
                    break;
                } else if (serverStatus.equals("Wrong Password !!!")) {
                    warningBox.setText(serverStatus);
                    warningBox.setTextFill(Color.RED);
                    oldPassBox.setText(null);
                    newPassBox.setText(null);
                    renewPassBox.setText(null);
                    serverStatus = " ";
                    break;
                }
            }
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        client.showHomePage();
    }
}
