import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTestAppl {

	private static final long N_NUMBERS = 1000;
	private static final int MIN_NUMBER = 100;
	private static final int MAX_NUMBER = 30000;

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		fillRandomNumbers(list);
//		System.out.printf("minimal even value: %d\n, " + "sum of negative mumbers: %d\n, " + 
//		"sort numbers in the range[%d - %d]:\n%s", 
//		minEvenValue(list), sumNegetiv(list), MIN_NUMBER, MAX_NUMBER, sortNUmbersInRange(list, MIN_NUMBER, MAX_NUMBER));
		
//		displayDigitsLengthStatistics(list);
		// Display out the following
		// <digits amount> -> <count>
//		displayGetMinLengthNumber(list);
		
//		displayDigitsNumber(list);
		
//		displayArrayShuffled(new int[] {1,22,34,45,51,65,73,88});
		
//		displayMinMaxAvgSum(list);
		int [][] numbers = {{10, 20, 30, 40, 50}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5} };
		countSumArray(numbers);
		
		

	}

	
	private static void countSumArray(int[][] numbers) {
		System.out.println(Arrays.stream(numbers).flatMapToInt(x -> Arrays.stream(x)).sum());
	}


	private static void displayMinMaxAvgSum(List<Integer> list) {
		IntSummaryStatistics stats =  list.stream().mapToInt(x-> x).summaryStatistics();
		System.out.println(stats);
		System.out.printf("min = %d, max = %d, avg = %f, sum = %d", stats.getMin(), stats.getMax(), stats.getAverage(), stats.getSum() );
		
	}


	private static void displayArrayShuffled(int[] ar) {
		new Random().ints(0,ar.length)
			.distinct()
				.limit(ar.length)
					.forEach(x -> System.out.print(ar[x] + " "));
		
	}






	private static void displayDigitsNumber(List<Integer> list) {
		
//		System.out.println(list.toString());
//		list.stream()
//			.map(Object::toString)
//				.flatMap(x-> Arrays.stream(x.split(""))) // x -> x.toCharArray -  не подойдет так как из чаров  нельзя получить поток ч
//					.collect(Collectors.groupingBy(x->x,Collectors.counting()))
//					.entrySet()
//						.stream()
//							.sorted((x, y) -> y.getValue().compareTo(x.getValue()))
//								.forEach(System.out::println);

			list.stream()
				.map(Object::toString)
					.flatMapToInt(x -> x.chars())
						.boxed()//.mapToObj(x -> (char) x) // (x -> x - '0')
							.collect(Collectors.groupingBy(x->x,Collectors.counting()))
								.entrySet()
									.stream()
										.sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
											.forEach(x -> System.out.printf("%c = %d\n", x.getKey(), x.getValue()));//.forEach(System.out::println);
							
//			.map(x -> Arrays.toString(x))
//			.toArray(String[]::new);
				
//		 int sum = IntStream.rangeClosed(0, a[0].length() - 1).mapToObj(i -> a[i]).parallel().flatMapToInt(Arrays::stream).sum();
//		String ff = a[0];
//		System.out.println(a[0]);
//		System.out.println(ff.charAt(0));
		
	}



	private static void displayGetMinLengthNumber(List<Integer> list) {
		Map<Object, List<Integer>> map = list.stream()
				.parallel()
				.collect(Collectors.groupingBy(x -> Integer.toString(Math.abs(x)).length()));
		
		long min =  map.values().stream().mapToLong(a->a.size()).min().orElse(0);
		
		
		map.values().stream().filter(x-> x.size()== min).flatMap(x->x.stream()).forEach(System.out::println);
//		map.forEach((k, v) -> {
//			if(v.size() == min)
//				v.forEach(System.out::println);
//		});
//		
		
		
	// генерируются положительные числа   распечатать статистику виречаемости цифор	
	// массив отсортировнный   должны напечататься каждый раз в разном порядке
	// посчитать сумму в двух мерном массиве
			
			
		
	}

	private static void displayDigitsLengthStatistics(List<Integer> list) {
		list.stream()
			.parallel()
				.collect(Collectors.groupingBy(x -> Integer.toString(Math.abs(x)).length() , Collectors.counting()))
					.forEach((k,v)->System.out.printf("%d -> %d\n", k, v));
		
	}

	private static Object sortNUmbersInRange(List<Integer> list, int min, int max) {
		return list.stream()
				.filter(x-> x<=max && x>=min)
					.sorted()
						.map(x -> x.toString())
							.collect(Collectors.joining("\n"));
	}

	private static long sumNegetiv(List<Integer> list) {
		return  list.stream()
					.filter(x->x<0)
						.mapToLong(x->x)
							.sum(); // orElse  не работает
	}

	private static Object minEvenValue(List<Integer> list) {
		// получает потое объектов, преобразуем в числа, получаем четные, находим минимальное значение
		return list.stream()
				.mapToInt(x->x)
					.filter(x-> x%2==0)
						.min().orElse(Integer.MIN_VALUE);
	}

	private static void fillRandomNumbers(List<Integer> list) {
		new Random().ints(N_NUMBERS, 0, Integer.MAX_VALUE).forEach(list::add); 
		// metod referens  = вызывается у каждого обекта и делается над ним операцию (add)
		// list эта ссылка на объект
			}

}
