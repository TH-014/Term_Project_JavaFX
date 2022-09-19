package com.example.term_project_javafx.client;

import static java.lang.Thread.sleep;

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
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
