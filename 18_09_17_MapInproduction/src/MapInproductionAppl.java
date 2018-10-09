import java.util.*;
public class MapInproductionAppl {

	public static void main(String[] args) {
		Map<String,Integer> months = new TreeMap<>();
		months.put("Jan", 1);
		months.put("Feb", 2);
		months.put("Mar", 3);
		months.put("Apr", 4);
		months.put("May", 5);
		months.put("Jun", 6);
		months.put("Jul", 7);
		
		Map<Integer, String> monthIntStr = getMonthsIntegerString(months);
		displayMonthsIntegerString(monthIntStr);
		
//		System.out.println("iterating entries");
//		for(Map.Entry<String, Integer> entryMonth : months.entrySet()) {
//			System.out.printf("%s -> %d\n", entryMonth.getKey(), entryMonth.getValue());
//		}
//		
	}
		static Map<Integer, String> getMonthsIntegerString(Map<String, Integer> monthsStringInteger){
			TreeMap<Integer, String>  res = new TreeMap<>();
//			for(Map.Entry<String, Integer> entryMonth : monthsStringInteger.entrySet()) {
//				res.put(entryMonth.getValue(), entryMonth.getKey());
//			}
			
			monthsStringInteger.forEach((k,v) -> res.put(v,k));
			return res;
		}
		
		static void displayMonthsIntegerString(Map<Integer, String> months) {
			// print as follows
			// 1 -> Jan
			// 2 -> feb
			// .........
			// According to the calendar order
//			for(Map.Entry<Integer, String> entryMonth : months.entrySet()) {
//				System.out.printf("%d -> %s\n", entryMonth.getKey(), entryMonth.getValue());
//			}
//			marh  and compurt
			months.forEach((k,v)-> System.out.printf("%d -> %s\n", k, v));
			
		}
		
		
		
		
		

	

}
