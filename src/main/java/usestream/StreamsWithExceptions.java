package usestream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<T, R> {
  R apply(T t) throws Throwable;
}

public class StreamsWithExceptions {
  public static Stream<String> getLines(String name) {
    try {
      return Files.lines(Paths.get(name));
    } catch (Throwable t) {
      System.err.println("Ooops, file " + name + " is not found");
      return Stream.empty();
      // throw new RuntimeException(t); // BAD kills stream infrastructure
    }
  }

  public static Optional<Stream<String>> getOptLines(String name) {
    try {
      return Optional.of(Files.lines(Paths.get(name)));
    } catch (Throwable t) {
      return Optional.empty();
    }
  }

  public static <T, R> Function<T, Optional<R>> wrap(ExFunction<T, R> op) {
    return t -> {
      try {
        return Optional.of(op.apply(t));
      } catch (Throwable throwable) {
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "c.txt")
        .flatMap(name -> getLines(name))
        .forEach(s -> System.out.println(s));

    System.out.println("---------------");
    Stream.of("a.txt", "b.txt", "c.txt")
        .map(name -> getOptLines(name))
        .peek(res -> {
          if (!res.isPresent())
          System.err.println("File was missing");
        })
        .filter(res -> res.isPresent())
        .flatMap(res -> res.get())
        .forEach(s -> System.out.println(s));

    System.out.println("---------------");
    Stream.of("a.txt", "b.txt", "c.txt")
        .map(wrap(name -> Files.lines(Paths.get(name))))
        .peek(res -> {
          if (!res.isPresent())
          System.err.println("File was missing");
        })
        .filter(res -> res.isPresent())
        .flatMap(res -> res.get())
//        .forEach(s -> System.out.println(s)); //
        .forEach(System.out::println);


  }
}
