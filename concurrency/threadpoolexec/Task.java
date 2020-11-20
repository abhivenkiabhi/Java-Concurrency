package io.abhishek.concurrency.threadpoolexec;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Runnable {
 private String name;

 public Task(String name) {
   this.name = name;
 }
 public String getName() {
   return this.name;
 }
  @Override
  public void run() {
    try {
      System.out.println("Current Time is " + LocalDateTime.now());
      Thread.sleep(1000);
    } catch(InterruptedException exp) {
      exp.printStackTrace();
    }
  }
}
