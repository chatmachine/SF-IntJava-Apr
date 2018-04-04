package runnables;

class MyJob implements Runnable {
  private int i = 0;
  @Override
  public void run() {
    for (; i < 10_000; i++) {
      System.out.println(Thread.currentThread().getName() + " value of i is " + i);
    }
    System.out.println("Thread is quitting");
  }
}

public final class MyRunnableEx {
  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + " starting...");
    Runnable r = new MyJob();
    Thread t1 = new Thread(r);
    t1.start();

    Thread t2 = new Thread(r);
    t2.start();

    System.out.println("Other thread started, main exiting...");
  }
}
