package shopping;

import java.time.LocalDate;

public final class TryPair {
  public static void main(String[] args) {
//    Pair<String> p = new Pair<>("Fred", LocalDate.now());
    Pair<String> p = new Pair<>("Fred", "Jones");

    ClothingPair<Shoe> ps = new ClothingPair<>(
        new Shoe("Red", 44),
        new Shoe("Pink", 44)
    );
    System.out.println("Matched? " + ps.isMatched());
  }
}
