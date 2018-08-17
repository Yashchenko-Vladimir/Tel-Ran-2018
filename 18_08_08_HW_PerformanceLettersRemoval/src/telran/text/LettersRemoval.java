package telran.text;

public abstract class LettersRemoval {
	

	protected String str;
//	protected char letter;
	
	public LettersRemoval(String str) {
		this.str = str;
//		this.letter = letter;
	}
	
	public abstract void remove(char letter);

	public String getString() {
		return str;
	}
	
	public void setStr(String str) {
		this.str = str;
	}

//	public void setLetter(char letter) {
//		this.letter = letter;
//	}
}
