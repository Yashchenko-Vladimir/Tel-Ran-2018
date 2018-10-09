package telran.util.words;


import java.util.HashMap;
import java.util.Map;
public class AnagramClass {
	

	
		public static boolean isAnagramLetters(String word, String anagram) {
			if(anagram.isEmpty()) {
				return false;
			}
			Map<Character, Integer> wordChars = getMapChars(word);
			return isAnagram(wordChars, anagram);
		}

		private static boolean isAnagram(Map<Character, Integer> wordChars, String anagram) {
			for(char letter : anagram.toCharArray()) {
//				if(wordChars.compute(letter, (k, v) -> v == null ? -1 : v - 1)< 0) {
//					return false;
//				}
//				System.out.println(wordChars.merge(letter, 1, (v1, v2) -> v1 + v2));
				if(wordChars.merge(letter, -1, (v1, v2) -> v1 + v2) < 0) {
					return false;
				}
			}
			return true;
		}

		private static Map<Character, Integer> getMapChars(String word) {
			HashMap<Character, Integer> res = new HashMap<>();
			for(char letter : word.toCharArray()) {
				res.merge(letter, 1, (v1, v2) -> v1 + v2);
			}
			return res;
		}

		
	

}
