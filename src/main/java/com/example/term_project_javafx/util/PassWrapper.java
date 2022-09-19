package com.example.term_project_javafx.util;

import java.io.Serializable;

public class PassWrapper implements Serializable {
    private String username;
    private String oldPass;
    private String newPass;
    private boolean status=false;

    public PassWrapper(String username, String oldPass, String newPass) {
        this.username = username;
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getUsername() {
        return username;
    }

    public String getOldPass() {
        return oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
