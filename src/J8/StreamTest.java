package J8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Milo on 2017/5/4.
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));


        List<String> names = menu.stream().filter(dish -> dish.getCalories() > 100).map(Dish::getName).limit(3).collect(toList());
        List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
        menu.stream().forEach(System.out::println);
        System.out.println(dishNames);

        String[] arrayOfWords = {"Goodbye", "World"};

        List<String> collect = Arrays.stream(arrayOfWords)
                                     .map(words -> words.split(""))
                                     .flatMap(Arrays::stream)
                                     .distinct()
                                     .collect(toList());
        System.out.println(collect);

        //(1) 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢?例如，给定[1, 2, 3, 4,5]，应该返回[1, 4, 9, 16, 25]。
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> resultNumbers = numbers.stream().map(num -> num * num).collect(toList());
        System.out.println(resultNumbers);

        //(2) 给定两个数字列表，如何返回所有的数对呢?例如，给定列表[1, 2, 3]和列表[3, 4]，应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代表数对。

        List<Integer> firstNumbers = Arrays.asList(1, 2, 3);
        List<Integer> secondNumbers = Arrays.asList(4, 5);

        List<List<Integer>> collect1 = firstNumbers.stream().flatMap(num -> secondNumbers.stream().map(num2 -> Arrays.asList(num, num2))).collect(Collectors.toList());
        System.out.println(collect1);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        System.out.println(pairs);

        //(3) 如何扩展前一个例子，只返回总和能被3整除的数对呢?例如(2, 4)和(3, 3)是可以的。

        //List<List<Integer>> collect1 = firstNumbers.stream().flatMap(num -> secondNumbers.stream().map(num2 -> Arrays.asList(num, num2))).filter(list -> list.forEach(l -> SubThreepredicate :: test(l))).collect(Collectors.toList());
        //System.out.println(collect1);
        List<int[]> pairss = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (j + i) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());
        pairss.stream().forEach(System.out::println);

        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));
//        if (dish.isPresent()) {
//            System.out.println(dish.get());
//        }
        // 归约
        Integer sum = numbers1.stream().reduce(0, (a, b) -> (a + b));
        System.out.println(sum);
        // 最大值  最小值
        numbers2.stream().reduce(Integer::max).ifPresent(d -> System.out.println(d));
        numbers2.stream().reduce(Integer::min).ifPresent(d -> System.out.println(d));
        // 计算菜的个数
        menu.stream().map(dish -> 1).reduce((a, b) -> a + b).ifPresent(count -> System.out.println(count));

        long count = menu.stream().count();
        System.out.println(count);
        // FIXME: 2017/9/1  stream()换成了parallelStream()  并行化操作(同步)
        String s  = "1,2,3,4,5,6";
        List<String> strings = Arrays.stream(s.split(",")).collect(toList()).stream().limit(3).distinct().collect(toList());
        System.out.println(strings);

    }
}


