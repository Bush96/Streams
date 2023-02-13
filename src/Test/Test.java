package Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Test.Gender.FEMALE;
import static Test.Gender.MALE;
import static java.util.stream.Collectors.toList;

public class Test {
    public static void main(String[] args) {
        List<Person> people = getPersons();
//Filters
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(FEMALE))
                .collect(toList());
//        females.forEach(System.out::println);

//Sorting
        List<Person> sorted = people.stream().sorted(Comparator.comparing(Person::getAge))
                .collect(toList());
//        sorted.forEach(System.out::println);

//AllMAtch
        boolean allMatch = people.stream().allMatch(person -> person.getAge() > 2);
//        System.out.println(allMatch);

//AnyMatch
        boolean anyMatch = people.stream().anyMatch(person -> person.getName().startsWith("B"));
//        System.out.println(anyMatch);


//NoneMatch
        boolean noneMatch = people.stream().noneMatch(person -> person.getName().startsWith("B"));
//        System.out.println(noneMatch);

//Max
        people.stream()
                .max(Comparator.comparing(Person::getAge));
//                .ifPresent(person -> System.out.println(person));

//Min
        people.stream()
                .min(Comparator.comparing(Person::getAge));
//                .ifPresent(person -> System.out.println(person));

//Group
        Map<Gender, List<Person>> groupBy = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupBy.forEach((gender, people1) -> {
//            System.out.println(gender);
//            people1.forEach(System.out::println);
//            System.out.println();
        });

//Chaining
        Optional<String> oldestFemale = people.stream()
                .filter(person -> person.getGender().equals(FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

//        oldestFemale.ifPresent(System.out::println);


//______________________TASK1________________________

//        Stream.of(10, 19, 20, 1, 2).map(p ->p*2)            //Have a sorted collection, you should display the data multiples of 2m using a map increase the value and display a new value.
//                .sorted()
//                .collect(toList())
//                .forEach(System.out::println);

//______________________TASK1 (ADDS)________________________
        long before = System.currentTimeMillis();
        List<Integer> list = Arrays.asList(10, 19, 20, 1, 2);

        list.stream()
                .parallel().map(p -> p * 2)
                .sorted()
                .forEachOrdered(System.out::println);
        long after = System.currentTimeMillis() - before;
        System.out.println("forEachOrdered time " + after);


        long before2 = System.currentTimeMillis();
        List<Integer> list2 = Arrays.asList(10, 19, 20, 1, 2);

        list2.stream()
                .parallel().map(p -> p * 2)
                .sorted()
                .forEach(System.out::println);
        long after2 = System.currentTimeMillis() - before2;
        System.out.println("forEach " + after2);


    }


    public static List<Person> getPersons() {
        return List.of(
                new Person(1, "Anna", 21, FEMALE),
                new Person(2, "Ben", 19, MALE),
                new Person(3, "Monika", 45, FEMALE),
                new Person(4, "Kek", 88, MALE)
        );
    }
}
