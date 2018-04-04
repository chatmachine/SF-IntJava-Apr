package collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface ExceptionFunction<E, F> {
  F apply(E e) throws Throwable;
}

public final class ExceptionConcordance {

  public static <E, F> Function<E, Optional<F>> wrap(ExceptionFunction<E, F> op) {
    return e -> {
      try {
        return Optional.of(op.apply(e));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }

  static final Function<String, Optional<Stream<String>>> fs = wrap(f -> Files.lines(Paths.get(f)));
  static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");
  static final Comparator<Map.Entry<String, Long>> byValue = Map.Entry.comparingByValue();
  static final Comparator<Map.Entry<String, Long>> byDescendingValue = byValue.reversed();

  public static void main(String[] args) throws Throwable {
    List.of("PrideAndPrejudice.txt", "Emma.txt", "SenseAndSensibility.txt")
        .stream()
        .map(fs)
        .peek(opt -> {
          if (!opt.isPresent()) {
            System.out.println("There was a problem");
          }
        })
        .filter(opt -> opt.isPresent())
        .flatMap(opt -> opt.get())
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
  }
}
