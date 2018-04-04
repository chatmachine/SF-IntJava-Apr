package runnables;

public final class Timing {

  public static void main(String[] args) throws Throwable {
    boolean [] stop = { false };
    Runnable r = () -> {
      System.out.println("Job starting...");
      while (!stop[0])
        ;
      System.out.println("Job finishing...");
    };
    new Thread(r).start();
    Thread.sleep(1000);
    stop[0] = true;
    System.out.println("main thread exiting...");
    System.out.println("value of stop[0] is " + stop[0]);
  }
}
