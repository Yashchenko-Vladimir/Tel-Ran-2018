import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fileread {
	public static void main(String[] args) throws IOException {
		FileInputStream reader = new FileInputStream(new File("E:\\Java\\Tel-Ran\\srcFile.txt"));
		
		System.out.println(reader.available() + 1);
	}

}
