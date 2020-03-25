package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

//  Java 8 Iterable already has "forEach"
//  public void forEvery(Consumer<E> op) {
//    for (E e : self) {
//      op.accept(e);
//    }
//  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
    self.forEach(e -> res.add(op.apply(e)));
    return new SuperIterable<>(res);
  }

  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();
    for (E e : self) {
      SuperIterable<F> sif = op.apply(e);
      for (F f : sif) {
        res.add(f);
      }
    }
    return new SuperIterable<>(res);
  }

  public SuperIterable<E> filter(Predicate<E> pred) {
    List<E> res = new ArrayList<>();
    for (E e : self) {
      if (pred.test(e)) {
        res.add(e);
      }
    }
    return new SuperIterable<>(res);
  }

  public Iterator<E> iterator() {
    return self.iterator();
  }
}
