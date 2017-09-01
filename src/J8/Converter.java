package J8;

/**
 * Created by Milo on 2017/9/1.
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
