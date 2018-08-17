package telran.text;

public class LetterRemovalReplaceAll extends LettersRemoval {
	
	public LetterRemovalReplaceAll (String str) {
		super(str);
	}

	@Override
	public void remove(char letter) {
		String lett = Character.toString(letter); 
		String str1 = str.replaceAll(lett, "");
		str = str1;

	}

}
