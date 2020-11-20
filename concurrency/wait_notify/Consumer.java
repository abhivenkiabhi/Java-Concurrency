package io.abhishek.concurrency.wait_notify;

import java.util.List;

public class Consumer implements Runnable{
  private final List<Integer> taskQueue;

  public Consumer(List<Integer> sharedQueue) {
    this.taskQueue = sharedQueue;
  }

  public void run() {

    while (true) {
      try {
        consume();
    } catch(InterruptedException e){
      e.printStackTrace();
    }
  }
  }

  private void consume() throws InterruptedException {
    synchronized (taskQueue) {
      while(taskQueue.isEmpty()) {
        System.out.println("taskQueue is empty for " + Thread.currentThread().getName());
        taskQueue.wait();
      }
      Thread.sleep(100);
      int i = taskQueue.remove(0);
      System.out.println("Consumed " + i);
      taskQueue.notifyAll();
    }
  }
}
