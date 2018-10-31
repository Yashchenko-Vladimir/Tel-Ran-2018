import java.io.*;

public class SaveIX {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		IX x1 = new X();
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Xdata"));
		
		output.writeObject(x1);
		output.close();

	}

}
