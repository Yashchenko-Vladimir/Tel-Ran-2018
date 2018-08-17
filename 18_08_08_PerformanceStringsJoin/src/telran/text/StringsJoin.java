package telran.text;

public abstract class StringsJoin {
	protected String [] strings;
	protected String delimeter;

	public StringsJoin(String[] strings, String delimeter) {
		this.strings = strings;
		this.delimeter = delimeter;
		
	}

	abstract public String join();

	public void setStrings(String[] strings) {
		this.strings= strings;		
	}

	public void setDelimeter(String delimeter) {
		this.delimeter = delimeter;
		
	}

}
