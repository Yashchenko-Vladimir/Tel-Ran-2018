package telran.text.coding;

public class Cipher {
	
	private static final int ASCII_SIZE = 127;
	private String secret;
	private int block;
	
	public Cipher(String secret) {
		this.secret = secret;
		this.block = getBlockSize();
	}
	
	public String getCipher(String plainText) {
		StringBuilder builder = new StringBuilder("");
		String cipherCod;
		for (int i = 0; i <plainText.length(); i++) {
			cipherCod = getLetterChiper(plainText.charAt(i));
			builder.append(cipherCod);
		}
		return builder.toString();
	}
	
	private String getLetterChiper(char ch) {
		int number = ch;
		StringBuilder builder = new StringBuilder("");
		for(int i = 0; i < block; i++) {
				builder.append(secret.charAt(number%secret.length()));
				number = number / secret.length();
		}
		return builder.toString();
	}

	private int getBlockSize() {
		int sizeBlock = 1;
		int number = ASCII_SIZE / secret.length();
		while(number > 0) {
			number = number / secret.length() ;
			sizeBlock++;
		}
		return sizeBlock;
	}

	public String getPlainText(String cipher) {
		String str = "";
		StringBuilder builder = new StringBuilder("");
		for(int i = 0; i < (cipher.length()); i = i + block){
			str = cipher.substring(i, i + block);
			builder.append(getLetterText(str));
		}
		return builder.toString();
	}

	private char getLetterText(String str) {
		int charNumberLetter = 0;
		char ch;
		for(int i = block -1; i >= 0; i--) {
			ch = str.charAt(i);
			charNumberLetter = charNumberLetter * secret.length() + secret.indexOf(ch);
		}
		return (char) charNumberLetter;
	}

}
