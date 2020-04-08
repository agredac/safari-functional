package usestream;

import java.util.Optional;

public class Average {
  private long count;
  private double sum;

  public Average(long count, double sum) {
    this.count = count;
    this.sum = sum;
  }

  public Average include(double d) {
    return new Average(count + 1, sum + d);
  }

  public Average merge(Average other) {
    return new Average(this.count + other.count, this.sum + other.sum);
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}
