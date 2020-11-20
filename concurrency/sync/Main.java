package io.abhishek.concurrency.sync;

public class Main {

  public static void main(String[] args) {
    final MathClass mathClass = new MathClass();

    Runnable runnable = new Runnable() {
      @Override public void run() {
        try {
          mathClass.printNumber(3);
        } catch (InterruptedException exception) {
          exception.printStackTrace();
        }
      }
    };
//    new Thread(r, "ONE").start();
//    new Thread(r, "TWO").start();
    Thread t1 = new Thread(runnable, "ONE");
    Thread t2 = new Thread(runnable, "TWO");
    t1.start();
    t2.start();
  }

}
