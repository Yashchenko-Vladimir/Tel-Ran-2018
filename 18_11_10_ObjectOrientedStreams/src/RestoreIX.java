import java.io.*;
public class RestoreIX {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("Xdata"));
		IX ix = (IX) input.readObject();
		ix.display();
		input.close();

	}

}
