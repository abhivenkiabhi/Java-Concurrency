package io.abhishek.concurrency.blockingqueue;

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
      Thread.sleep(1000);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
    System.out.println("Executing : " + name);
  }
}
