package io.abhishek.concurrency.executerservice;

import java.time.LocalDate;

public class Task implements Runnable {

  @Override
  public void run() {
    try {
      System.out.println("Current Time is " + LocalDate.now());
      Thread.sleep(100);
    } catch(InterruptedException exp) {
      exp.printStackTrace();
    }
  }
}
