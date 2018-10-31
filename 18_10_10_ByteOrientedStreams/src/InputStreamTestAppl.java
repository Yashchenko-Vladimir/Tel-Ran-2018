import java.io.*;
import java.util.Arrays;

public class InputStreamTestAppl {
		public static void main(String[] args) throws IOException {
			InputStream input = new FileInputStream("file");
			
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			System.out.println(new String(buffer));
			
			
		}
	}
