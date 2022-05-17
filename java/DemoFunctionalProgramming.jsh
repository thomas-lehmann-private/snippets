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

//
// Rule 1: "pure functions should return a value based on its arguments only"
// Rule 2: "pure functions should not have side effects"
//
// Examples for not wanted side effects:
//     logging, updating global states, saving to a database
//

System.out.println("\n--- Given entries sorted using <<pure functions>>");
var someData = new ArrayList<>(List.of(9, 5, 2, 10, 4, 8, 3, 7, 6, 1));
Collections.sort(someData, Comparator.comparing(Integer::intValue));
System.out.println(someData);

//
// Other basic concepts of functional programming:
//
//   - immutability (String, Integer, Double, List.of, ...) -> also for own data structures
//   - referential transparency (pure, immutable, context-free code)
//   - function composition (composing complex function by combining simpler functions)
//
// Context-free code means: can be executed in any order and context (good for optimization)
//

@FunctionalInterface public interface DoSomething<E> {
    /** do something with the given value */
    void doSomething(final E someValue);
}

DoSomething<Integer> numberPrinter = (n) -> System.out.println(n);
System.out.println("\n--- Printing out using a functional interface");
// Please note: Consumer interface used here
someData.forEach(numberPrinter::doSomething);

System.out.println("\n--- Composing (combining) functions");
// Please note: you cannot use 'var' for first two lines because of 'compose'
Function<Integer, String> intToString = Object::toString;
Function<String, String> quoteString = strValue -> "'" + strValue + "'";
Function<Integer, String> quoteIntToString = quoteString.compose(intToString);
System.out.println(quoteIntToString.apply(1024));

//
// BiFunction's (Function with two arguments and two types)
System.out.println("\n--- Using function with two arguments (example: replaceAll)");
var someMapData = new TreeMap<>(Map.of("a", 10, "b", 20, "c", 30, "d", 40, "e", 50));
someMapData.replaceAll((key, value) -> key.equals("c")? value: value * 100);
System.out.println(someMapData);

// List.of provides immutable; that's why it is copied
// Please note: Not a BiFunction since: one type only.
someData = new ArrayList<>(List.of(9, 5, 2, 10, 4, 8, 3, 7, 6, 1, 16, 25, 36));

System.out.println("\n--- Given entries sorted using <<Lambda>>");
Collections.sort(someData, (a, b) -> a.compareTo(b));
System.out.println(someData);

// Using predicates
System.out.println("\n--- Filtering using predicate functions");
final Predicate<Integer> isEven = (n) -> n % 2 == 0
System.out.println(someData.stream().filter(isEven).collect(Collectors.toList()));

// Combine more predicates
System.out.println("\n--- Filtering using combines predicate functions");
final Predicate<Integer> isSquare = (n) -> Double.valueOf(Math.sqrt(n)).equals(Math.floor(Math.sqrt(n)))
System.out.println(someData.stream().filter(isEven.and(isSquare)).collect(Collectors.toList()));

// Using operators (replaceAll with UnaryOperator -> import java.util.function.UnaryOperator)
System.out.println("\n--- Modifying using (unary) operators");
final UnaryOperator<Integer> multiplicatedWith100 = (n) -> n * 100
someData.replaceAll(multiplicatedWith100)
System.out.println(someData)

// create list of 10 elements each initialized with 0
System.out.println("\n--- Generated list with supplier")
final Supplier<Integer> initWithZero = () -> 0
System.out.println(Stream.generate(initWithZero).limit(10).collect(Collectors.toList()))


/exit
