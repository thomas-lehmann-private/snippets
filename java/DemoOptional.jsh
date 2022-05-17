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
var someData = List.of(5, 2, 6, 3, 9, 6, 8, 7, 10)
var someMapData = Map.of("a", 10, "b", 20, "c", 30, "d", 40)

System.out.println("\n--- No result through streaming api find")
var result1 = someData.stream().filter(n -> n > 10).findAny()
System.out.println(result1.isPresent())

System.out.println("\n--- Minimum value through streaming api")
var result2 = someData.stream().mapToInt(Integer::valueOf).min();
result2.ifPresent(System.out::println)

System.out.println("\n--- any result found through streaming api filter")
var result3 = someData.stream().filter(n -> n <= 10).findAny()
result3.ifPresent(System.out::println);

System.out.println("\n--- no result through map get")
var result4 = Optional.ofNullable(someMapData.get(10))
System.out.println(result4.isPresent())

/exit
