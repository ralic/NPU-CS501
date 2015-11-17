
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author raliclo
 * @author http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class StreamExample {

    private static final Logger LOG = Logger.getLogger(StreamExample.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /**
             * @param speedX to monitor program runtime
             */
            // TODO code application logic here
            long speedX = System.currentTimeMillis();
            runexec("pwd");
//            streamExamples_MapAverage_findMax();
//            streamExamples_ArrayasList_StreamOf_sorted_filter_foreach_anyMatch_nonMatch();
//            streamExamples_Supplier_Collector_Reducer();
//            streamExample_FlapMap();
            streamExample_parallelStream();
//            runexec("hadoop dfs -ls");
            System.out.println("Time spent :" + (System.currentTimeMillis() - speedX));
        } catch (IOException ex) {
            Logger.getLogger(StreamExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void streamExamples_MapAverage_findMax() {
        System.out.println("Example : Average of 1~3, with map function 2*n+1");
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);
// 5.0
        System.out.println("Example : IntStream Range 1~4 and average , i.e.Average of 1~3");
        IntStream.range(1, 4) //                .forEach(System.out::println) 
                .average()
                .ifPresent(System.out::println);
// 2.0
    }

    public static void streamExamples_String_buildup() {
        System.out.println("Example : Random Sorted Unique Integer Generator");
        int number = 4, qty = 3;
        ThreadLocalRandom.current().ints(0, number).distinct().limit(qty).sorted()
                .forEach(System.out::println);
        System.out.println("Example : IntStream with range 1~4, print 1~3");
        IntStream.range(1, 4)
                .forEach(System.out::println);
// 1 
// 2 
// 3
        System.out.println("Example : Map transfer integer to stringObj");
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
// a1 
// a2 
// a3
        System.out.println("Example : Map trasnsfer double to integer and build StringObj");
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
// a1 
// a2 
// a3
    }

    public static void streamExamples_filters() {

        System.out.println("Example : Map filtering String to integer and findmax");
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
// 3
        System.out.println("Example : Map filter and printall");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    if (s.contains("2")) {
                        System.out.println("filter: contains2 -- " + s);
                        return true;
                    } else {
                        System.out.println("filter: not contains 2 -- " + s);
                        return false; // Not going to print by foreach.
                    }
                })
                .forEach(s -> System.out.println("forEach: " + s));
// Stream to upper case.
        System.out.println("Example : Map with toUpperCase and filter and print by anyMatch instead of fliter/foreach");
        String[] testS = {"d2", "a2", "b1", "b3", "c"};
        System.out.println("Input : String[] : d2" + ",a2" + ",b1" + ",b3" + ",c");
        Stream.of(testS)
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
// map:      d2 
// anyMatch: D2
// map:      a2
// anyMatch: A2
        System.out.println("Example : New One");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

// map:     d2
// filter:  D2
// map:     a2
// filter:  A2
// forEach: A2
// map:     b1
// filter:  B1
// map:     b3
// filter:  B3
// map:     c
// filter:  C
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

// filter:  d2
// filter:  a2
// map:     a2
// forEach: A2
// filter:  b1
// filter:  b3
// filter:  c
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
//sort:    a2; d2
//sort:    b1; a2
//sort:    b1; d2
//sort:    b1; a2
//sort:    b3; b1
//sort:    b3; d2
//sort:    c; b3
//sort:    c; d2
//filter:  a2
//map:     a2
//forEach: A2
//filter:  b1
//filter:  b3
//filter:  c
//filter:  d2
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

// filter:  d2
// filter:  a2
// filter:  b1
// filter:  b3
// filter:  c
// map:     a2
// forEach: A2
    }

    public static void streamExamples_ArrayasList_StreamOf_sorted_filter_foreach_anyMatch_nonMatch() {

        System.out.println("Example -- Arrays.asList,stream(),filter(s.startsWith),toUpperCase,map,sorting,print");
        List<String> myList
                = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
// C1 
// C2
        System.out.println("Example : Arrays.asList,Stream.of,findFirst,ifPresent");
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
// a1
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);
// a1

        System.out.println("Example --  anyMatch  & nonMatch");
        Stream<String> stream
                = Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
//        stream.noneMatch(s -> true);   // exception
        /*
        Calling noneMatch after anyMatch on the same stream results in the following exception
       java.lang.IllegalStateException: stream has already been operated upon or closed
    at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:229)
    at java.util.stream.ReferencePipeline.noneMatch(ReferencePipeline.java:459)
    at com.winterbe.java8.Streams5.test7(Streams5.java:38)
    at com.winterbe.java8.Streams5.main(Streams5.java:28)
         */
    }

    public static void streamExamples_Supplier_Collector_Reducer() {

        Supplier<Stream<String>> streamSupplier
                = () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok

        /*
        Collect is an extremely useful terminal operation to transform 
        the elements of the stream into a different kind of result, 
        e.g. a List, Set or Map. 
        
        Collect accepts 
        a Collector which consists of four different operations: 
        a supplier, 
        an accumulator, 
        a combiner and a finisher. 
        
        This sounds super complicated at first, 
        but the good part is Java 8 supports various built-in collectors 
        via the Collectors class. 
        
        So for the most common operations you don't have to implement 
        a collector yourself.
        
        Let's start with a very common usecase:
         */
        List<Person> persons
                = Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        List<Person> filtered
                = persons
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());

        System.out.println(filtered);    // [Peter, Pamela]
/*
        As you can see it's very simple to construct a list 
        from the elements of a stream. 
        Need a set instead of list - just use Collectors.toSet().
        
        The next example groups all persons by age: */
        System.out.println("Example: Transform List to another Map with grouping");
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

// age 18: [Max]
// age 23: [Peter, Pamela]
// age 12: [David]
/*
Collectors are extremely versatile. 
You can also create aggregations on the elements of the stream, 
e.g. determining the average age of all persons: */
        System.out.println("Example : Collector\n"
                + "Use collect for element of stream data type"
                + " and collector.averagingInt to get average person.age");
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println("Average Age:" + averageAge);     // 19.0
        System.out.println("Example : Use IntSummaryStatistics to show info about person.age ");
        IntSummaryStatistics ageSummary
                = persons
                .stream()
                .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}

        System.out.println("Examples : Collector.joining (delimitor , prefix, sufix)");
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
// In Germany Max and Peter and Pamela are of legal age.
/*
*@ Transform List to Map Format
**/
        System.out.println("Transform List to Map format");
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
// {18=Max, 23=Peter;Pamela, 12=David}
/*
@ Collector , StringJoiner 
**/
        System.out.println("Example : Join Strings by using Collector.of & StringJoiner &add & merge");
        Collector<Person, StringJoiner, String> personNameCollector
                = Collector.of(
                        () -> new StringJoiner(" | "), // supplier
                        (j, p) -> j.add(p.name.toUpperCase()), // accumulator
                        (j1, j2) -> j1.merge(j2), // combiner
                        StringJoiner::toString);                // finisher

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
/*
        The reduction operation combines all elements of the stream into a single result. 
        Java 8 supports three different kind of reduce methods. 
        The first one reduces a stream of elements to exactly one element of the stream. 
        Let's see how we can use this method to determine the oldest person:
        **/
        System.out.println("Example : Stream Reducer to find the oldest person");
        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);    // Pamela
/*
        The reduce method accepts a @BinaryOperator accumulator function. 
        That's actually @a BiFunction where both operands share the same type, 
        in that case Person. 
        @BiFunctions are like Function but accept two arguments. 
        The example function compares both persons ages in order to 
        return the person with the maximum age.
        The second reduce method accepts both 
        @ an identity value and  @a BinaryOperator accumulator. 
        This method can be utilized to construct a new Person with 
        the aggregated names and ages from all other persons in the stream:
**/
        System.out.println("Example: 2nd Reduce Method\n"
                + "-Use stream(), reduce() \n"
                + "-Use new Object as Identity value\n"
                + "-Return : Aggregated Names and Ages from Person Object");
        Person result
                = persons
                .stream()
                .reduce(new Person("", 0), (p1, p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });

        System.out.format("name=%s, age=%s\n", result.name, result.age);
// name=MaxPeterPamelaDavid; age=76
/*
The third reduce method accepts three parameters: 
@an identity value, 
@a BiFunction accumulator and 
@a combiner function of type BinaryOperator. 
Since the identity values type is not restricted to the Person type,
we can utilize this reduction to determine the sum of ages from all persons:
**/
        System.out.println("Example : Parallel Steam");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3
// JVM parameter to increase ForkJoinPool
//        -Djava.util.concurrent.ForkJoinPool.common.parallelism = 5
        System.out.println("Example :Use parallel Stream to make sure combiner is runned");

        System.out.println("Example: AgeSum by Reduce Method3 "
                + "stream(),parallelStream(), reduce()"
                + "- with accumulator and combiner ");
        System.out.println("Common Tread Counts before Stream():" + commonPool.getRunningThreadCount());

        Integer ageSum = persons
                .stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.println("Common Tread Counts in Aggregator using Stream():" + commonPool.getRunningThreadCount());
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.println("Common Tread Counts in Combiner using Stream():" + commonPool.getRunningThreadCount());
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        System.out.println(ageSum);  // 76 
// accumulator: sum=0; person=Max
// accumulator: sum=18; person=Peter
// accumulator: sum=41; person=Pamela
// accumulator: sum=64; person=David
        System.out.println("The combiner never gets called if just uses stream()");
        System.out.println("Common Tread Counts before parallelStream():" + commonPool.getRunningThreadCount());

        Integer ageSum2 = persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.println("Common Tread Counts in Aggregator using parallelStream():" + commonPool.getRunningThreadCount());
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.println("Common Tread Counts in Combiner using parallelStream():" + commonPool.getRunningThreadCount());
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });

// accumulator: sum=0; person=Pamela
// accumulator: sum=0; person=David
// accumulator: sum=0; person=Max
// accumulator: sum=0; person=Peter
// combiner: sum1=18; sum2=23
// combiner: sum1=23; sum2=12
// combiner: sum1=41; sum2=35
    }

    public static void streamExample_parallelStream() {

//        JVM parameter to increase ForkJoinPool
//        -Djava.util.concurrent.ForkJoinPool.common.parallelism = 5
//        Default is 3
        System.out.println("Example : Parallel Steam");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3

        System.out.println("***Example : Another parallelStream Example");
        System.out.println("Input : \"a1\", \"a2\", \"b1\", \"c2\", \"c1\"");
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                //                .stream()
                .filter(s -> {
//                    System.out.println("Common Tread Counts with parallelStream():" + commonPool.getRunningThreadCount());
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
        /*
        filter:  b1 [main]
filter:  a2 [ForkJoinPool.commonPool-worker-1]
map:     a2 [ForkJoinPool.commonPool-worker-1]
filter:  c2 [ForkJoinPool.commonPool-worker-3]
map:     c2 [ForkJoinPool.commonPool-worker-3]
filter:  c1 [ForkJoinPool.commonPool-worker-2]
map:     c1 [ForkJoinPool.commonPool-worker-2]
forEach: C2 [ForkJoinPool.commonPool-worker-3]
forEach: A2 [ForkJoinPool.commonPool-worker-1]
map:     b1 [main]
forEach: B1 [main]
filter:  a1 [ForkJoinPool.commonPool-worker-3]
map:     a1 [ForkJoinPool.commonPool-worker-3]
forEach: A1 [ForkJoinPool.commonPool-worker-3]
forEach: C1 [ForkJoinPool.commonPool-worker-2]
         */

        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                                    sum, p, Thread.currentThread().getName());
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                                    sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });
    }

    public static void streamExample_FlapMap() {
        /*
        FlatMap

We've already learned how to transform the objects of a stream 
        into another type of objects by utilizing the map operation. 
Map is kinda limited because every object can only be mapped to 
        exactly one other object. 
But what if we want to transform one object into multiple others 
        or none at all? 
This is where flatMap comes to the rescue.

FlatMap transforms each element of the stream 
        into a stream of other objects. 
So each object will be transformed into zero, 
        one or multiple other objects backed by streams. 
The contents of those streams will then be placed into 
        the returned stream of the flatMap operation.
Before we see flatMap in action we need an appropriate type hierarchy:
**/
        class Bar {

            String name;

            Bar(String name) {
                this.name = name;
            }
        }
        class Foo {

            String name;
            List<Bar> bars = new ArrayList<>();

            Foo(String name) {
                this.name = name;
            }
        }
        List<Foo> foos = new ArrayList<>();

// create foos
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

// create bars
        foos.forEach(f -> IntStream
                .range(1, 4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

// Bar1 <- Foo1
// Bar2 <- Foo1
// Bar3 <- Foo1
// Bar1 <- Foo2
// Bar2 <- Foo2
// Bar3 <- Foo2
// Bar1 <- Foo3
// Bar2 <- Foo3
// Bar3 <- Foo3
    }

    public static void streamExampleHigherHierarchical() {
        class Outer {

            Nested_L1 nested_l1;
        }

        /*
        In order to resolve the inner string foo of an outer instance 
        you have to add multiple null checks to prevent 
        possible NullPointerExceptions:
         */
        Outer outer = new Outer();
        if (outer != null && outer.nested_l1 != null && outer.nested_l1.nested_l2 != null) {
            System.out.println(outer.nested_l1.nested_l2.foo);
        }
        /*
        The same behavior can be obtained by utilizing optionals flatMap operation:
         */
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested_l1))
                .flatMap(n -> Optional.ofNullable(n.nested_l2))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);

    }

    public static int[] unique_INTgenerator(int number, int qty) {
        IntStream a = ThreadLocalRandom.current().ints(0, number).distinct().limit(qty);
        int[] arr = a.toArray();
        Arrays.sort(arr);
        return arr;
// Non-stream method :
//        int[] arr = new int[4];
//        for (int i = arr.length - 1; i >= 0; i--) {
//            System.out.println(i);
//            arr[i] = (int) (5 * Math.random() + 1);
//            Arrays.sort(arr);
//            for (int j = 0, n = arr.length; j < n; j++) {
//                System.out.print(arr[j] + " ");
//            }
//            System.out.println();
//        }
//        ThreadLocalRandom.current().ints(0, 5).distinct().limit(5).forEach(System.out::print);

    }

    /**
     * @param x for command line
     * @throws java.io.IOException
     */
    public static void runexec(String x) throws IOException {
        final Process p = Runtime.getRuntime().exec(x);
//-------------lambda Codes to print InputStream-------------
        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            try {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(StreamExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private Map<String, String> mapConfig(Map<String, Integer> input, String prefix) {
//        int subLength = prefix.length();
//        return input.entrySet().stream()
//                .collect(Collectors.toMap(entry -> entry.getKey().substring(subLength), entry -> AttributeType.getByName(entry.getValue())));
//    }
}
