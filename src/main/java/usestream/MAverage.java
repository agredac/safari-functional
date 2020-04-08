package usestream;

import java.util.Optional;

public class MAverage {
  private long count = 0;
  private double sum = 0;

  public MAverage() {}

  public void include(double d) {
    count++;
    sum += d;
  }

  public void merge(MAverage other) {
    this.count += other.count;
    this.sum += other.sum;
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}
