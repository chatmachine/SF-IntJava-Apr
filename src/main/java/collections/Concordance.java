package collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Concordance {

  static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");
  static final Comparator<Map.Entry<String, Long>> byValue = Map.Entry.comparingByValue();
  static final Comparator<Map.Entry<String, Long>> byDescendingValue = byValue.reversed();

  public static void main(String[] args) {
    try (Stream<String> input = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
      input
          .map(s -> s.toLowerCase())
          .flatMap(l -> WORD_BOUNDARY.splitAsStream(l))
          .filter(w -> w.length() > 0)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet().stream()
//          .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//          .sorted(byDescendingValue)
          .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
          .limit(200)
          .forEach(e -> System.out.printf("%20s : %5d\n", e.getKey(), e.getValue()));
    } catch (IOException ioe) {
      System.out.println("Problem " + ioe.getMessage());
    }
  }
}
