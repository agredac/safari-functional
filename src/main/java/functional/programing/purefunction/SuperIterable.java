package functional.programing.purefunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private final Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    public SuperIterable<E> filter(Predicate<E> pred) {

        List<E> results = new ArrayList<>();
        self.forEach( e -> {
            if(pred.test(e)) results.add(e);
        });


/*
        for (E e : self) {
            if (pred.test(e))
                results.add(e);

        }*/

        return new SuperIterable<>(results);
    }

    public void forEvery(Consumer<E> consumer){

        for(E e:self)
            consumer.accept(e);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public static void main(String[] args) {

        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("Red", "blue", "Green")
        );

        for (String s : strings) {
            System.out.println("> " + s);
        }

        System.out.println("-----------------------------");

        SuperIterable<String> upperCaseList = strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        upperCaseList.forEvery(s -> System.out.println(s));
        System.out.println("-----------------------------");

        strings.forEach(s-> System.out.println(s));
        /*
        for (String s : upperCaseList) {
            System.out.println("> " + s);
        }
        for (String s : strings) {
            System.out.println("> " + s);
        }*/
    }
}
