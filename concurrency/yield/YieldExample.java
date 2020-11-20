package io.abhishek.concurrency.yield;
class Producer extends Thread {
  public void run() {
    for(int i = 0;i < 5;i++) {
       System.out.println("Produced item " + i);
       Thread.yield();
    }
  }
}
class Consumer extends Thread {
  public void run() {
    for(int i = 0;i < 5;i++) {
      System.out.println("Consumed item " + i);
      Thread.yield();
    }
  }
}
public class YieldExample {
  public static void main(String[] args) {
    Thread producer = new Producer();
    Thread consumer = new Consumer();
   // producer.setPriority(Thread.MIN_PRIORITY);
    consumer.setPriority(Thread.MAX_PRIORITY);
    producer.start();
    consumer.start();

  }
}
