import java.util.ArrayList;
import java.util.Arrays;
	import java.util.Comparator;
	import java.util.HashMap;
	import java.util.LinkedHashMap;
	import java.util.Map;
	import java.util.Map.Entry;
	import java.util.TreeMap;
	import java.util.TreeSet;
public class WordsOccurrenciesAppClass {
	

	public static void main(String[] args) {

			String text="abc ab lmn ab lmn abc, lmn, pp, pn, pp, ";
			displayWordOccurrencies(text);

		}

		private static void displayWordOccurrencies(String text) {

			
			//text: “abc ab lmn ab lmn abc, lmn”
			//output:
			// lmn->3
			// ab->2
			// abc->2
			String [] words = getWordsLowerCase(text);
			Map<String, Integer> mapOccurences = getMap(words); 
			ArrayList<Map.Entry<String, Integer>> listOccurences = new ArrayList<>(mapOccurences.entrySet());
			listOccurences.sort((e1, e2) -> e1.getValue() != e2.getValue() ? e2.getValue() - e1.getValue() : e1.getKey().compareTo(e2.getKey()));
			displayListOccurences(listOccurences);
		}

		private static void displayListOccurences(ArrayList<Entry<String, Integer>> listOccurences) {
			listOccurences.forEach(e-> System.out.printf("%s -> %d\n", e.getKey(), e.getValue()) );
			
		}

		private static Map<String, Integer> getMap(String[] words) {
			HashMap<String, Integer> res = new HashMap<>();
			for(String word: words) {
				int count = res.getOrDefault(word, 0);
				res.put(word, count+1);
			}
			return res;
		}

		private static String[] getWordsLowerCase(String text) {
			String [] res = text.toLowerCase().split("[^a-z]+");
			return res;
		}

		

	}


