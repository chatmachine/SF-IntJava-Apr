package dynamic;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Properties;

public final class TestFramework {
  public static void main(String[] args) throws Throwable {
    Properties prop = new Properties();
    prop.load(new FileReader("totest.properties"));
    String classname = prop.getProperty("1");
    System.out.println("About to test: " + classname);
    Class cl = Class.forName(classname);
    Object obj = cl.getConstructor().newInstance();

//    System.setSecurityManager(new SecurityManager());

//    Method[] methods = cl.getMethods();
    Method[] methods = cl.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
      RunMe annot = m.getAnnotation(RunMe.class);
      if (annot != null) {
        System.out.println("**** Found @RunMe value = " + annot.value() + " num = " + annot.num());
        m.setAccessible(true);
        m.invoke(obj);
      }
    }
  }
}
