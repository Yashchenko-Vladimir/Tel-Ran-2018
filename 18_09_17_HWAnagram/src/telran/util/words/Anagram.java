package telran.util.words;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
	public static boolean isAnagramLetters(String word, String anagram) {
		if(anagram.isEmpty()) {
			return false;
		}
		HashMap<String, Integer> letter1 = getMapChar(word);
		HashMap<String, Integer> letter2 = getMapChar(anagram);
		
		return checkAnagram(letter1, letter2);
	}

	private static boolean checkAnagram(HashMap<String, Integer> let1, HashMap<String, Integer> let2) {
		for(Map.Entry<String, Integer> set : let2.entrySet()) {
			if (let1.containsKey(set.getKey())) {
				if(set.getValue() > let1.getOrDefault(set.getKey(), 0)) {
				return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private static HashMap<String, Integer> getMapChar(String word) {
		HashMap<String, Integer> map = new HashMap<>();
		char [] arr = word.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			map.put(String.valueOf(arr[i]), map.getOrDefault(String.valueOf(arr[i]), 0) + 1);
		}
		return map;
	}
}
