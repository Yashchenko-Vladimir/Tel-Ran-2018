package telran.text;

public class StringsJoinBuilder extends StringsJoin {

	public StringsJoinBuilder(String[] strings, String delimeter) {
		super(strings, delimeter);
	}

	@Override
	public String join() {
		StringBuilder builder= new StringBuilder("");
		if (strings!=null&&strings.length!=0) {
			builder = new StringBuilder(strings[0]);
			for (int i = 1; i < strings.length; i++) {
//				builder.append(delimeter + strings[i]);
				builder.append(delimeter).append(strings[i]);
//				System.out.println(builder + "11111");
			} 
		}
		return builder.toString();
	}

}
