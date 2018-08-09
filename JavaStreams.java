import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class JavaStreams {
	public static void main(String[] args) throws IOException {
		// 1. Integer Stream
		System.out.print("1. ");
		IntStream
			.range(1,10)
			.forEach(System.out::print); // terminal operation
			//.forEach(x -> System.out.print(x)); normal lambda syntax
		System.out.println();

		// 2. Integer Stream with skip
		System.out.print("2. ");
		IntStream
			.range(1,10)
			.skip(5)
			.forEach(x -> System.out.print(x));
		System.out.println();

		// 3. Integer Stream with sum
		System.out.print("3. ");
		System.out.print(
			IntStream
				.range(1,5)
				.sum());
		System.out.println();

		//4. Stream.of, sorted and findFirst
		System.out.print("4. ");
		Stream.of("Carrie", "Bertha", "Arthur")
			.sorted()
			.findFirst()
			.ifPresent(System.out::print);
		System.out.println();

		//5. Stream from Array, sort, filter and print
		System.out.println("5. ");
		String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika"};
		Arrays.stream(names) // same as Stream.of(names)
			.filter(x -> x.startsWith("S"))
			.sorted()
			.forEach(System.out::println);

		//6. Average of squares of an int array
		System.out.print("6. ");
		Arrays.stream(new int[] {2,4,6,8,10})
			.map(x -> x*x)
			.average()
			.ifPresent(System.out::print);
		System.out.println();

		//7. Stream from List, filter and print
		System.out.println("7. ");
		List<String> people = Arrays.asList("Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika");
		people
			.stream()
			.map(String::toLowerCase)
			.filter(x -> x.startsWith("a"))
			.forEach(System.out::println);

		//8. Stream rows from text file, sort, filter, and print
		System.out.println("8. ");
		Stream<String> countries = Files.lines(Paths.get("countries.txt"));
		countries
			.sorted()
			.filter(x -> x.length() > 7)
			.forEach(x -> System.out.println(x));
		countries.close();

		//9. Stream rows from text file and save to List
		System.out.println("9. ");
		List<String> countries2 = Files.lines(Paths.get("countries.txt"))
			.filter(x -> x.contains("on"))
			.collect(Collectors.toList());
		countries2.forEach(x -> System.out.println(x));

		//10. Stream rows from CSV file and count
		System.out.println("10. ");
		Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
		int rowCount = (int)rows1
			.map(x -> x.split(","))
			.filter(x -> x.length == 3)
			.count();
		System.out.println(rowCount + " rows.");
		rows1.close();

		//11. Stream rows from CSV file, parse data from rows
		System.out.println("11. ");
		Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
		rows2
			.map(x -> x.split(","))
			.filter(x -> x.length == 3)
			.filter(x -> Integer.parseInt(x[1]) > 15)
			.forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
		rows2.close();

		//12. Stream rows from CSV file, store fields in HashMap
		System.out.println("12. ");
		Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
		Map<String, Integer> map = new HashMap<>();
		map = rows3
			.map(x -> x.split(","))
			.filter(x -> x.length == 3)
			.filter(x -> Integer.parseInt(x[1]) > 15)
			.collect(Collectors.toMap(
				x -> x[0],
				x -> Integer.parseInt(x[1])));
		rows3.close();
		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}

		//13. Reduction - sum (a is our running total, b is the new element)
		System.out.print("13. ");
		double total = Stream.of(7.3,1.5,4.8)
			.reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println("Total = " + total);

		//14. Reduction - summary statistics
		System.out.println("14. ");
		IntSummaryStatistics summary = IntStream.of(7,2,19,88,73,4,10)
			.summaryStatistics();
		System.out.println(summary);
	}
}
