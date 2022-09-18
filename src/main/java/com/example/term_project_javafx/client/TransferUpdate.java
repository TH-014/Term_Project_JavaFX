package com.example.term_project_javafx.client;

import java.util.concurrent.TimeUnit;

public class TransferUpdate implements Runnable{
    private MyMoviesController controller;
    private Thread thr;

    public TransferUpdate(MyMoviesController controller) {
        this.controller = controller;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        while (!MyMoviesController.backClicked)
        {
            if(MyMoviesController.transferDitected)
            {
                controller.addInfo();
                MyMoviesController.transferDitected=false;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
