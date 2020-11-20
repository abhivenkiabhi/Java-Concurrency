package io.abhishek.concurrency.wait_notify;

import java.util.List;

public class Producer implements Runnable {
  private final List<Integer> taskQueue;
  private final int MAX_SIZE;

  public Producer(List<Integer> sharedQueue, int size) {
    this.taskQueue = sharedQueue;
    this.MAX_SIZE = size;
  }

  public void run() {
    int counter = 0;
    while(true) {
      try {
        produce(counter++);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void produce(int i) throws InterruptedException {
    synchronized (taskQueue) {
      while(MAX_SIZE == taskQueue.size()) {
        System.out.println("Queue is Full, " + Thread.currentThread().getName() + " is Waiting, Queue size is "
                + taskQueue.size());
        taskQueue.wait();
      }
      Thread.sleep(500);
      taskQueue.add(i);
      System.out.println("Produced: " + i);
      taskQueue.notifyAll();

    }
  }
}
