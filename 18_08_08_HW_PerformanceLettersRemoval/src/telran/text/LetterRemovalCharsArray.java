package telran.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LetterRemovalCharsArray extends LettersRemoval {

	public LetterRemovalCharsArray(String str) {
		super(str);
	}

	@Override
	public void remove(char letter) {
		
		char[] ch = str.toCharArray();
		int size = ch.length;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == letter) {
				for (int j = i; j < size-1; j++) {
					ch[j] = ch[j+1];
				}
				i--;
				size--;
			}
		}
		str = "";
		for (int i = 0; i < size; i++) {
			str= str + ch[i];
		}
//		str = String.valueOf;
		
//		char [] ch = str.toCharArray();
//		ArrayList<Character> list = new ArrayList<Character>();
//		for (int i = 0; i < ch.length; i++) {
//			if (!(ch[i] == letter)) {
//				list.add(ch[i]);
//			}
//		}
//		str = list.toString();
//		char [] ch1 = new char[list.size()];
//		int a = 0;
//		for(Character i: list) {
//			ch1[a] = i;
//			a++;
//		}
//		
//		str = String.valueOf(ch1);
//
	}

}
