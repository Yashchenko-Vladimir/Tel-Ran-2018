import java.io.*;

public class JavaFileTestAppl {

	public static void main(String[] args) throws IOException {
		File file = new File("name/name1/name2/f");
		System.out.println(file.exists());
		File dir = new File("name/name1/name2/name3");
		dir.mkdir();
		file.createNewFile();

	}

}
