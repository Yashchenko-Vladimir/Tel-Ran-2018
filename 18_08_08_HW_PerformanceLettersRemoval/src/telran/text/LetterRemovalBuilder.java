package telran.text;

public class LetterRemovalBuilder extends LettersRemoval {

	public LetterRemovalBuilder(String str) {
		super(str);
	}

	@Override
	public void remove(char letter) {
		
		StringBuilder builder = new StringBuilder(str); 
//		String lett = Character.toString(letter);
		
			for (int i = 0; i < builder.length(); i++) {
				if(builder.charAt(i) == letter) {
					builder.deleteCharAt(i);
					i--;
				}
			}
		
//			for (int i = 0; i < str.length(); i++) {
//				if(builder.ine(i) == letter) {
//					builder.deleteCharAt(i );
//				}
	
				
		str = builder.toString();

		}

}
