import java.io.*;
public class OutputStreamTestAppl {

	public static void main(String[] args) throws IOException {
		
		OutputStream output = new FileOutputStream("file", true);
		
		String str = " World";
		output.write(str.getBytes());
		
		output.close();

	}

}
