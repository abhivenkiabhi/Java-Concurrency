package io.abhishek.concurrency.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Integer> sharedQueue = new ArrayList<>();
    final int maxSize = 5;
    Thread tProducer = new Thread(new Producer(sharedQueue, maxSize), "Producer");
    Thread tConsumer = new Thread(new Consumer(sharedQueue), "Consumer");
    tProducer.start();
    tConsumer.start();
  }
}
