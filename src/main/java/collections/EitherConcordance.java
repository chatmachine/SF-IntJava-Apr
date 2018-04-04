package collections;

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

final class Either<V> {
  private V value;
  private Throwable throwable;

  private Either(V value, Throwable throwable) {
    this.value = value;
    this.throwable = throwable;
  }

  public static <V> Either<V> success(V value) {
    return new Either(value, null);
  }

  public static <V> Either<V> failure(Throwable t) {
    return new Either(null, t);
  }

  public boolean isSuccess() {
    return value != null;
  }

  public V get() {
    if (value == null) {
      throw new RuntimeException("Attempt to get success value from failed Either");
    }
    return value;
  }

  public String failureMessage() {
    if (throwable == null) {
      throw new RuntimeException("Attempt to get failure message from successful Either");
    }
    return "Exception occurred " + throwable.getClass().getName() + ": " + throwable.getMessage();
  }

  public static <E, F> Function<E, Either<F>> wrap(ExceptionFunction<E, F> op) {
    return e -> {
      try {
        return Either.success(op.apply(e));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }
}

public final class EitherConcordance {

  static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");
  static final Comparator<Map.Entry<String, Long>> byValue = Map.Entry.comparingByValue();
  static final Comparator<Map.Entry<String, Long>> byDescendingValue = byValue.reversed();

  public static void main(String[] args) throws Throwable {
    List.of("PrideAndPrejudice.txt", "Emma.txt", "SenseAndSensibility.txt")
        .stream()
        .map(Either.wrap(f -> Files.lines(Paths.get(f))))
        .peek(e -> {
          if (!e.isSuccess()) {
            System.out.println("There was a problem " + e.failureMessage());
          }
        })
        .filter(opt -> opt.isSuccess())
        .flatMap(opt -> opt.get())
        .map(s -> s.toLowerCase())
        .flatMap(l -> WORD_BOUNDARY.splitAsStream(l))
        .filter(w -> w.length() > 0)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .limit(200)
        .forEach(e -> System.out.printf("%20s : %5d\n", e.getKey(), e.getValue()));
  }
}
