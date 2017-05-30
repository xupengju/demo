package J8;

import java.util.*;

/**
 * Created by Milo on 2017/5/4.
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Virat", "Kohli"));
        personList.add(new Person("Arun", "Kumar"));
        personList.add(new Person("Rajesh", "Mohan"));
        personList.add(new Person("Rahul", "Dravid"));


        //Sorting using Anonymous Inner class.
        //alt+ enter 快捷键
       // Collections.sort(personList, (p1, p2) -> p1.firstName.compareTo(p2.firstName));

        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        });

        Collections.sort(personList,(o1, o2) -> o1.firstName.compareTo(o2.firstName));

        System.out.println(personList.get(1).getFirstName());

        Comparator<String> comp = (first, second) -> first.length() - second.length();
        int result = comp.compare("sssss", "sss");
        System.out.println(result);

        String[] words = new String[]{"1","00","99","66","xxxx"};
        Arrays.sort(words, (three, four) -> three.length() - four.length());
        System.out.println(Arrays.toString(words));
        Arrays.asList(words).stream().forEach(s -> System.out.println(s));

        Arrays.sort(words, (x,y) -> x.compareToIgnoreCase(y));
        System.out.println(Arrays.toString(words));

        Arrays.sort(words, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(words));
    }
}


