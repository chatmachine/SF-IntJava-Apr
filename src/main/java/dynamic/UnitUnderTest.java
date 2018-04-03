package dynamic;


//@RunMe
public final class UnitUnderTest {
//  @RunMe
  private String text;

  @RunMe("Albert")
  public void doStuff() {
    System.out.println("UnitUnderTest.doStuff!");
  }

  public void dontDoStuff() {
    System.out.println("UnitUnderTest.dontDoStuff!");
  }

  @RunMe(value="Freddy", num=3)
  private void doPrivateStuff() {
    System.out.println("UnitUnderTest.doPrivateStuff!");
  }

  @RunMe
  public void doOtherStuff() {
    System.out.println("UnitUnderTest.doOtherStuff!");
  }
}
