package collections;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

final class Average {
  private double sum;
  private long count;
  public void include(double d) {
    sum += d;
    count++;
  }
  public void merge(Average other) {
    this.sum += other.sum;
    this.count += other.count;
  }
  public OptionalDouble get() {
    return (count != 0)
        ? OptionalDouble.of(sum / count)
        : OptionalDouble.empty();
  }

  @Override
  public String toString() {
    return "Average{" +
        "sum=" + sum +
        ", count=" + count +
        ", average = " + get() +
        '}';
  }
}

public final class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
        .parallel()
//        .limit(4_000_000_000L)
        .limit(500_000_000L)
        .map(x -> Math.sin(x))
        .collect(() -> new Average(), (b, d) -> b.include(d), (bFinal, b) -> bFinal.merge(b))
        .get()
        .ifPresentOrElse(
            v -> System.out.println("Average is " + v),
            () -> System.out.println("No data in stream!"));
    long time = System.nanoTime() - start;
    System.out.printf("Total time %9.6f seconds\n", (time / 1_000_000_000.0));
  }
}
