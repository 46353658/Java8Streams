# Java8Streams
* Source // stream source
	Filter // intermediate operations
	Sort
	Map
  Collect // terminal operations

* Stream source
	Streams can be created from Collections, Lists, Sets, ints, longs, doubles, arrays, lines of a file
* Stream operations are either intermediate or terminal
	- Intermediate operations such as filter, map or sort return a stream so we can chain multiple intermediate
	operations
	- Terminal operations such as forEach, collect or reduce are either void or return a non-stream result

* For intermediate operations, order matters for large data sets:
	filter first, then sort or map.
  For very large datasets use ParellelStream to enable multiple threads.
  Intermediate operations include:

	anyMatch()	flatmap()
	distinct()	map()
	filter() 	skip()
	findFirst() 	sorted()

* Terminal Operations

	One terminal operation is allowed.

	forEach applies the same function to each element.

	collect saves the elements into a collection.

	other options reduce the stream to a single summary element. a, b, c, ... => Z

	count() 	min()
	max() 		reduce()
			summaryStatistics()
