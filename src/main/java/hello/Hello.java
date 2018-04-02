package hello;

public final class Hello {
  public static void main(String[] args) {
    ((Runnable)(()->System.out.println("Hello world!"))).run();
  }
}
