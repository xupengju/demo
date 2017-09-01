package J8;

import java.util.function.Predicate;

/**
 * Created by Milo on 2017/9/1.
 */
class SubThreepredicate implements Predicate {

    @Override
    public boolean test(Object o) {
        return (Integer) o % 3 == 0;
    }

    @Override
    public Predicate and(Predicate other) {
        return null;
    }

    @Override
    public Predicate negate() {
        return null;
    }

    @Override
    public Predicate or(Predicate other) {
        return null;
    }
}
