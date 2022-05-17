/*
 * The MIT License
 *
 * Copyright 2022 Thomas Lehmann.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
public enum Complexity {
    LOW, MEDIUM, HIGH
}

// implements getters, setters, hasCode, equal and toString
public record Task(
    String title,
    String category,
    Complexity complexity
) {}

// define some tasks
// Note: List.of provides an immutable list
var someTasks = List.of(
    new Task("learning streaming api", "data", Complexity.LOW),
    new Task("learning grouping", "streaming api", Complexity.MEDIUM),
    new Task("learning sorting", "streaming api", Complexity.MEDIUM),
    new Task("learning joining", "streaming api", Complexity.LOW),
    new Task("learning records", "data", Complexity.LOW),
    new Task("learning reducing data", "streaming api", Complexity.LOW),
    new Task("learning filtering", "streaming api", Complexity.LOW));

// define sorting criterias using the getters
// Note: sortByTitle.revsersed() for decending order
var sortByTitle = Comparator.comparing(Task::title);
var sortByCategory = Comparator.comparing(Task::category);

// printing them (toString nicely handled by records) -> no streaming api
// Note: also possible: task -> System.out.println(task) for forEach(...)
System.out.println("\n--- Given entries (no sort, no streaming api)");
someTasks.forEach(System.out::println);

// printing them sorted by title
System.out.println("\n--- Sorted By Title");
someTasks.stream().sorted(sortByTitle).forEach(System.out::println);

// printing them sorted by category first, then by title
System.out.println("\n--- Sorted by category, then by title");
someTasks.stream().sorted(sortByCategory.thenComparing(sortByTitle)).forEach(System.out::println);

// printing the groups sorted and per group sorted titles
System.out.println("\n--- Sorted groups");
someTasks.stream().collect(Collectors.groupingBy(Task::category)).entrySet().forEach(group -> {
    System.out.println(group.getKey());
    group.getValue().stream().sorted(sortByTitle).forEach(task -> System.out.println(" ... " + task.title()));
});

// printing the group of groups sorted and the final group with sorted titles
System.out.println("\n--- Sorted group of groups");
someTasks.stream().collect(Collectors.groupingBy(Task::category, Collectors.groupingBy(Task::complexity))).entrySet().forEach(group -> {
    System.out.println("category: " + group.getKey());
    group.getValue().entrySet().forEach(subGroup -> {
        System.out.println(" ... complexity: " + subGroup.getKey());
        subGroup.getValue().stream().sorted(sortByTitle).forEach(task -> System.out.println(" ...... " + task.title()));
    });
});

// Filtering of data
System.out.println("\n--- Filtering for task with low complexity");
someTasks.stream().filter(task -> task.complexity().equals(Complexity.LOW)).forEach(System.out::println);

// Reducing data and filtering data on collecting
// Note: also possible: task -> task.complexity() for map(...)
System.out.println("\n--- Filtering result to see all used complexities only");
someTasks.stream().map(Task::complexity).collect(Collectors.toSet()).forEach(System.out::println);

// Lambda for checking a number to be prime
Predicate<Integer> isPrime = (n) -> {
    if (n < 2) return false;
    if (n % 2 == 0) return n == 2;
    final var d = Double.valueOf(Math.sqrt(n)).intValue();
    for (var k = 3; k <= d; k += 2) {
        if (n % k == 0) return false;
    }
    return true;
}

// Converting stream to booleans representing the prime checks results
System.out.println("\n--- Print results of prime check starting by 0");
var primChecks = IntStream.range(0, 100).mapToObj(Integer::valueOf).map(isPrime::test).collect(Collectors.toList());
System.out.println(primChecks);

// calculating primes below 100
System.out.println("\n--- Print primes below 100");
var primes = IntStream.range(0, 100).mapToObj(Integer::valueOf).filter(isPrime).collect(Collectors.toList());
System.out.println(primes);

// calculating primes below 5000 in parallel
System.out.println("\n--- Print primes below 5000 (parallel calculation)");
primes = IntStream.range(0, 5000).parallel().mapToObj(Integer::valueOf).filter(isPrime).collect(Collectors.toList());
System.out.println(primes);

// create list of 10 elements each initialized with 0
System.out.println("\n--- Generated list 1");
System.out.println(Stream.generate(() -> 0).limit(10).collect(Collectors.toList()));

// create list of 10 elements initialized from 0 to 9
System.out.println("\n--- Generated list 2");
System.out.println(Stream.iterate(0, n -> n + 1).limit(10).collect(Collectors.toList()));

// Statistics
System.out.println("\n--- Statistics for numbers");
System.out.println("Count of primes: " + primes.stream().count());
System.out.println("Sum of primes: " + primes.stream().mapToInt(n -> n).sum());
System.out.println("Min of primes: " + primes.stream().mapToInt(n -> n).min());
System.out.println("Max of primes: " + primes.stream().mapToInt(Integer::intValue).max());

/exit
