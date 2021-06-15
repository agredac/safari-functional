package functional.programing.intro;

public interface Predicate<E> {

    boolean test(E c);

    default Predicate<E> negate() {
        return x -> !test(x);
    }

    default Predicate<E> and(Predicate<E> second) {

        return x -> this.test(x) && second.test(x);

    }

    default Predicate<E> or(Predicate<E> second) {

        return x -> this.test(x) || second.test(x);

    }


//    static <E> Criterion<E> negate(Criterion<E> crit) {
//        return x -> !crit.test(x);
//    }
//
//    static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second) {
//
//        return x -> first.test(x) && second.test(x);
//
//    }
//
//    static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second) {
//
//        return x -> first.test(x) || second.test(x);
//
//    }

}
