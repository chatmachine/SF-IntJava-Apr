package hello;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

public final class Hello {
  public static void main(String[] args) {
    ((Runnable) (() -> System.out.println("Hello world!"))).run();
  }
}
