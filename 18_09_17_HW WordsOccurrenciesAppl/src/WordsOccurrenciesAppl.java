import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordsOccurrenciesAppl {

	public static void main(String[] args) {

		String text="abc ab lmn ab lmn abc, lmn, pp, pn, pp, ";
		displayWordOccurrencies(text);

	}

	private static void displayWordOccurrencies(String text) {

		LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
		String [] allWords = getWordsArray(text);
		
		words = countAmountWords(allWords);
		words = sortMap(words);
		printMap(words);
		//text: “abc ab lmn ab lmn abc, lmn”
		//output:
		// lmn->3
		// ab->2
		// abc->2
		
	}

	private static LinkedHashMap<String, Integer> sortMap(LinkedHashMap<String, Integer> words) {
		Comparator<Entry<String, Integer>> comp = new MapValueComparator().
				thenComparing(new MapKeyComparator());
//		TreeMap<String, Integer> map = new TreeMap(comp);
//		TreeMap<String, Integer> map = new TreeMap<>();
//		Comparator<Entry<String, Integer>> comp = new MapValueComparator();
		
//		Comparator<Entry<String, Integer>> comp = Map.Entry.comparingByValue(MapValueComparator());
		
		TreeSet<Entry<String, Integer>> set = new TreeSet(comp);
		for(Map.Entry<String, Integer> elem : words.entrySet()) {
			set.add(elem);
		}
		
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		for(Map.Entry<String, Integer> elem : set) {
			map.put(elem.getKey(), elem.getValue());
		}
//		words.forEach((k,v) -> map.put(k, v));
	
				
				Map.Entry.comparingByKey();
				
//		map.entrySet().stream().sorted(Map.Entry.<String, Integer>.c)
		return map;
	}

	private static void printMap(Map<String, Integer> words) {
		words.forEach((k, v) -> System.out.printf("%s -> %d\n", k, v));
	}
	

	private static String[] getWordsArray(String text) {
		String [] arr = text.split(" ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].replaceAll("\\W", "");
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}

	private static LinkedHashMap<String, Integer> countAmountWords(String[] arr) {
		LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
		for (int i = 0; i < arr.length; i++) {
			words.put(arr[i], words.getOrDefault(arr[i], 0 ) + 1);
		}
		System.out.println(words);
		
		return words;
	}

}
