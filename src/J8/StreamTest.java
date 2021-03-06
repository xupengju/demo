package J8;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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

        List<String> list = new ArrayList<>();
        System.out.println(null == list);
        for (String sw :list){
            System.out.println(sw);
        }

        Map<Dish.Type, Long> typeLongMap = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typeLongMap);
        Map<Dish.Type, Optional<Dish>> collect2 = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(collect2);

        List<Long> ommunityids = Arrays.asList(1l,2l,3l);


        List<String> communityids = Arrays.asList("1l","2","3");
        String collect3 = communityids.stream().collect(Collectors.joining(","));
        System.out.println(collect3);



        String communityidsStr = communityids.stream().collect(Collectors.joining(" OR communityid:"));
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("(");
        sBuilder.append("communityid:").append(communityidsStr);
        sBuilder.append(")");
        System.out.println(communityidsStr);
        System.out.println(sBuilder.toString());

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect4 = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                                    if (dish.getCalories() < 400) return CaloricLevel.NORMAL;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.DIET;
                                    else return CaloricLevel.FAT;
                                }

                        )));
        System.out.println(collect4);
        //要数一数菜单中每类菜有多少个，可以传递counting收集器作为 groupingBy收集器的第二个参数
        Map<Dish.Type, Long> collect5 = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(collect5);

        //你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类 型分类:
        Map<Dish.Type, Optional<Dish>> collect6 = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(collect6);
        //因为分组操作的Map结果中的每个值上包装的Optional没什么用，所以你可能想要把它们去掉。要做到这一点，或者更一般地来说，把收集器返回的结果转换为另一种类型，你可以使用 Collectors.collectingAndThen工厂方法返回的收集器，
        //reducing 收集器永远都不会返回Optional.empty()
        Map<Dish.Type, Dish> collect7 = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect7);




    }
}


