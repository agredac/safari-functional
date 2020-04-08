package usestream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsingCollectors {
  public static String categorize(String s) {
    int l = s.length();
    if (l < 4) return "Short";
    if (l > 6) return "Long";
    return "Medium";
  }
  public static void main(String[] args) {
    Map<String, List<String>> map = Arrays.asList("one", "very long indeed", "modest", "a", "Banana split", "clock")
        .stream()
        .collect(Collectors.groupingBy(s -> categorize(s)));
    map.entrySet().stream()
        .forEach(e -> System.out.println(
            "These " + e.getValue() + " are " + e.getKey()));

    Map<String, Long> map2 = Arrays.asList("one", "very long indeed", "modest", "a", "Banana split", "clock")
        .stream()
        .collect(Collectors.groupingBy(s -> categorize(s),
            Collectors.counting()));

    map2.entrySet().stream()
        .forEach(e -> System.out.println(
            "There are " + e.getValue() + " " + e.getKey() + " Strings"));


    System.out.println("---------------------------");
    /* DOES NOT FIX THIS volatile */int [] data = {0}; // READ/MODIFY/WRITE cycle

    long total = Stream.generate(() -> 1)
        .limit(100_000)
        .parallel()
        .map(v -> {data[0] += v; return v;}) // NO NO NO NO
        .count();
    System.out.println("total is " + total + " data[0] is " + data[0]);

  }
}
