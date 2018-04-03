package shopping;

//public final class ClothingPair<E extends Sized, String> extends Pair<E> {
//  String s = "Hello";
//  java.lang.String x = "Hello";

public final class ClothingPair<E extends Sized & Colored> extends Pair<E> {
//  String s = "Hello";
//  java.lang.String x = "Hello";

  public ClothingPair(E left, E right) {
    super(left, right);
  }

  public boolean isMatched() {
    return left.getSize() == right.getSize()
        && left.getColor().equals(right.getColor());
  }
}
