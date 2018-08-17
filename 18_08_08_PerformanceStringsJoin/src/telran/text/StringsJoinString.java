package telran.text;

import telran.text.StringsJoin;

public class StringsJoinString extends StringsJoin {

	public StringsJoinString(String[] strings, String delimeter) {
		super(strings, delimeter);
	}

	@Override
	public String join() {
		
		String res="";
		if (strings!=null&&strings.length!=0) {
			res = strings[0];
			for (int i = 1; i < strings.length; i++) {
				res += delimeter + strings[i];
			} 
		}
		return res;
	}

}
