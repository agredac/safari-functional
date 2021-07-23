package functional.programing.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {

    private static final Pattern WORD_BREAK = Pattern.compile("\\W+");
    private static final Comparator<Map.Entry<String,Long>> reverseOrder= Map.Entry.comparingByValue(Comparator.reverseOrder());


    public static void main(String[] args) throws IOException {


        Files.lines(Paths.get("PrideAndPrejudice.txt"))
              //  .flatMap(s -> WORD_BREAK.splitAsStream(s))
                .flatMap(WORD_BREAK::splitAsStream)
                .filter(s -> s.length() > 0)
             //   .map(s -> s.toLowerCase())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(reverseOrder)
                .limit(200)
                .map(l -> String.format("%20s : %5d",l.getKey(),l.getValue()))
                .forEach(System.out::println);
                //.forEach(l -> System.out.println(l.getKey() +": "+l.getValue()));

    }
}
